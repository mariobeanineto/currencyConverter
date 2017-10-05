package br.com.beani.currencyConverter.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.com.beani.currencyConverter.models.PaginatedList;
import br.com.beani.currencyConverter.models.ShoppingCart;

@Repository
public class ShoppingCartDao
{

   @PersistenceContext
   private EntityManager manager;

   public List<ShoppingCart> all()
   {
      return manager.createQuery("select s from ShoppingCart s", ShoppingCart.class).getResultList();
   }

   public void save(ShoppingCart shoppingCart)
   {
      manager.persist(shoppingCart);
   }

   public ShoppingCart findById(Integer id)
   {
      return manager.find(ShoppingCart.class, id);
   }

   public void remove(ShoppingCart shoppingCart)
   {
      manager.remove(shoppingCart);
   }

   public void update(ShoppingCart shoppingCart)
   {
      manager.merge(shoppingCart);
   }

   public PaginatedList paginated(int page, int max)
   {
      return new PaginatorQueryHelper().list(manager, ShoppingCart.class, page, max);
   }

}
