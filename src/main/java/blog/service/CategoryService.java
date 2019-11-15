package blog.service;

import blog.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();
    Category findById(int id);
    void save(Category category);
    void remove(int id);

}
