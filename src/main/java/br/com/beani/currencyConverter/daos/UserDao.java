package br.com.beani.currencyConverter.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.com.beani.currencyConverter.models.PaginatedList;
import br.com.beani.currencyConverter.models.User;

@Repository
public class UserDao
{

   @PersistenceContext
   private EntityManager manager;

   public List<User> all()
   {
      return manager.createQuery("select u from User u", User.class).getResultList();
   }

   public void save(User user)
   {
      manager.persist(user);
   }

   public User findById(Integer id)
   {
      return manager.find(User.class, id);
   }

   public void remove(User user)
   {
      manager.remove(user);
   }

   public void update(User user)
   {
      manager.merge(user);
   }

   public PaginatedList paginated(int page, int max)
   {
      return new PaginatorQueryHelper().list(manager, User.class, page, max);
   }

}
