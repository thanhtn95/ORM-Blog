package blog.service;

import blog.model.Blog;
import blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    Blog findbyId(int id);

    void save(Blog blog);

    void delete(int id);

    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByTitleContaining(String word, Pageable pageable);
    Page<Blog> findAllByOrderByNameDesc(Pageable pageable);
}
