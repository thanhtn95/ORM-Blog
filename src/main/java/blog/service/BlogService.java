package blog.service;

import blog.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    Blog findbyId(int id);

    void save(Blog blog);

    void delete(int id);
}
