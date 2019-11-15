package blog.controller;

import blog.model.Blog;
import blog.model.Category;
import blog.service.BlogService;
import blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/listCategory")
    public ModelAndView getCategory(){
        ModelAndView modelAndView = new ModelAndView("/categories/categoryList");
        modelAndView.addObject("categories",categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/newCate")
    public ModelAndView getNewCategoryForm(){
        ModelAndView modelAndView = new ModelAndView("/categories/newCategoryForm","category",new Category());
        return modelAndView;
    }

    @PostMapping("/addCate")
    public ModelAndView doAddNewCate(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/categories/newCategoryForm");
        modelAndView.addObject("message","Added A New Category ^-^");
        modelAndView.addObject("category",category);
        return modelAndView;
    }
    @GetMapping("/{id}/viewCate")
    public ModelAndView viewCategory(@PathVariable int id){
        Category selectedCategory = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/categories/viewCategoryForm");
        modelAndView.addObject("category",selectedCategory);
        return modelAndView;
    }

    @GetMapping("/{id}/editCate")
    public ModelAndView getEditCategoryForm(@PathVariable int id){
        Category selectedCategory = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/categories/editCategoryForm");
        modelAndView.addObject("category",selectedCategory);
        return modelAndView;
    }

    @PostMapping("/doEditCate")
    public ModelAndView doEditCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/categories/editCategoryForm");
        modelAndView.addObject("message","Updated A New Category :)");
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @GetMapping("/{id}/deleteCate")
    public ModelAndView getDeleteCategoryForm(@PathVariable int id){
        Category selectedCategory = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/categories/deleteCategoryForm");
        modelAndView.addObject("category",selectedCategory);
        return modelAndView;
    }
    @GetMapping("/{id}/doDeleteCate")
    public ModelAndView doDeleteCategory(@PathVariable int id){
        categoryService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCategory");
        modelAndView.addObject("message","Deleted A Category :(");
        return modelAndView;
    }

    @GetMapping("/{id}/categoryBlogList")
    public ModelAndView getCategoryBlogList(@PathVariable int id){
        Category category = categoryService.findById(id);
        Iterable<Blog> blogs = blogService.findAllByCategory(category);
        ModelAndView modelAndView = new ModelAndView("/blogList");
        modelAndView.addObject("blogs",blogs);
        modelAndView.addObject("categoryName","Category: "+category.getName());
        return modelAndView;
    }
}
