package blog.controller;

import blog.model.Blog;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/newBlog")
    public ModelAndView goToNewBlogForm() {
        ModelAndView modelAndView = new ModelAndView("/newBlogForm");
        modelAndView.addObject("blog", new Blog());
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
    public ModelAndView viewBlogList() {
        List<Blog> blogs = blogService.findAll();
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
