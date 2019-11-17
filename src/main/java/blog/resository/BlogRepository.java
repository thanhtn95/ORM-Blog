package blog.resository;

import blog.model.Blog;
import blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Integer> {
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByNameContaining(String word,Pageable pageable);
    Page<Blog> findAllByOrderByNameDesc(Pageable pageable);
}
