package blog.controller;

import blog.model.Blog;
import blog.model.Category;
import blog.service.BlogService;
import blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompositeRestController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories = (List<Category>) categoryService.findAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogList", method = RequestMethod.GET)
    public ResponseEntity<Page<Blog>> getAllBlog(Pageable pageable){
        Page<Blog> blogs = blogService.findAll(pageable);
        if(!blogs.hasContent()){
            return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/getBlogByCate")
    public ResponseEntity<Page<Blog>> getBlogByCategory(Pageable pageable, @PathVariable int id){
        Category category = categoryService.findById(id);
        Page<Blog> blogs = blogService.findAllByCategory(category,pageable);
        if(!blogs.hasContent()){
            return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/getBlog")
    public ResponseEntity<Blog> getBlog(Pageable pageable, @PathVariable int id){
        Blog blog = blogService.findbyId(id);
        if(blog == null){
            return new ResponseEntity<Blog>(blog, HttpStatus.OK);
        }else{
            return new ResponseEntity<Blog>(blog, HttpStatus.OK);
        }
    }
}
