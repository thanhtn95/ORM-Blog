package blog.controller;

import blog.model.Blog;
import blog.model.Category;
import blog.service.BlogService;
import blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/newBlog")
    public ModelAndView goToNewBlogForm() {
        ModelAndView modelAndView = new ModelAndView("/newBlogForm");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("categories",categories());
        return modelAndView;
    }

    @PostMapping("/addBlog")
    public ModelAndView doAddBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/newBlogForm");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New Blog Addded");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView viewBlogList(@PageableDefault(size = 2) Pageable pageable, @RequestParam("s") Optional <String> word) {
        Page<Blog> blogs;
        if(word.isPresent()){
            blogs= blogService.findAllByTitleContaining(word.get(),pageable);
        }else{
            blogs=blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blogList");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewBlog(@PathVariable int id) {
        Blog selectedBlog = blogService.findbyId(id);
        ModelAndView modelAndView = new ModelAndView("/viewBlogForm");
        modelAndView.addObject("blog", selectedBlog);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView goToBlogEditForm(@PathVariable int id) {
        Blog selectedBlog = blogService.findbyId(id);
        ModelAndView modelAndView = new ModelAndView("/editBlogForm");
        modelAndView.addObject("blog", selectedBlog);
        modelAndView.addObject("categories",categories());
        return modelAndView;
    }

    @PostMapping("/doEdit")
    public ModelAndView doDeleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/editBlogForm");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "New Blog Updated");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView goToBlogDeleteForm(@PathVariable int id) {
        Blog selectedBlog = blogService.findbyId(id);
        ModelAndView modelAndView = new ModelAndView("/deleteBlogForm");
        modelAndView.addObject("blog", selectedBlog);
        return modelAndView;
    }

    @GetMapping("/{id}/doDelete")
    public String doDeleteBlog(@PathVariable int id) {
        blogService.delete(id);
        return "redirect:/";
    }
}
