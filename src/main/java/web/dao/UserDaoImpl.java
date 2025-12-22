package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id));
    }
}

