package blog.resository;

import blog.model.Blog;
import blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Integer> {
    Iterable<Blog> findAllByCategory(Category category);
    Page<Blog> findAllByNameContaining(String word,Pageable pageable);
}
