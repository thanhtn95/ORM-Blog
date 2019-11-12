package blog.resository.Impl;

import blog.model.Blog;
import blog.resository.BlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepositoryImpl implements BlogRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b where b.id=:id",Blog.class);
        query.setParameter("id",id);
        try{
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Blog model) {
        if(model.getId() != 0){
            em.merge(model);
        }else{
            em.persist(model);
        }
    }

    @Override
    public void delete(int id) {
        Blog blog = findById(id);
        if(blog != null){
            em.remove(blog);
        }
    }
}
