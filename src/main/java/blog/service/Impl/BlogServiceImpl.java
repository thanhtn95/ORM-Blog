package blog.service.Impl;

import blog.model.Blog;
import blog.model.Category;
import blog.resository.BlogRepository;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog findbyId(int id) {
        return blogRepository.findOne(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void delete(int id) {
        blogRepository.delete(id);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepository.findAllByCategory(category);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(String word, Pageable pageable) {
        return blogRepository.findAllByNameContaining(word,pageable);
    }


    @Override
    public Page<Blog> findAllByOrderByNameDesc(Pageable pageable) {
        return blogRepository.findAllByOrderByNameDesc(pageable);
    }
}
