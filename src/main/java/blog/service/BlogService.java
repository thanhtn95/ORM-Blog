package blog.service;

import blog.model.Blog;

import java.util.List;

public interface BlogService {
    Iterable<Blog> findAll();

    Blog findbyId(int id);

    void save(Blog blog);

    void delete(int id);
}
