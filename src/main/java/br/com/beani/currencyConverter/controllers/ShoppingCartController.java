package br.com.beani.currencyConverter.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.beani.currencyConverter.daos.ShoppingCartDao;
import br.com.beani.currencyConverter.models.ShoppingCart;

@Controller
@RequestMapping("/shoppingCart")
@Transactional
public class ShoppingCartController
{

   @Autowired
   private ShoppingCartDao shoppingCartDao;

   @GetMapping("/form")
   public ModelAndView form(ShoppingCart shoppingCart)
   {
      ModelAndView modelAndView = new ModelAndView("shoppingCart/form-add");
      return modelAndView;

   }

   @PostMapping
   public ModelAndView save(@Valid ShoppingCart shoppingCart, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(shoppingCart);
      }
      shoppingCartDao.save(shoppingCart);
      return new ModelAndView("redirect:/shoppingCart");
   }

   @GetMapping("/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("shoppingCart/form-update");
      modelAndView.addObject("shoppingCart", shoppingCartDao.findById(id));
      return modelAndView;
   }

   @GetMapping
   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
   {
      ModelAndView modelAndView = new ModelAndView("shoppingCart/list");
      modelAndView.addObject("paginatedList", shoppingCartDao.paginated(page, 10));
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @GetMapping("/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      ShoppingCart shoppingCart = shoppingCartDao.findById(id);
      shoppingCartDao.remove(shoppingCart);
      return "redirect:/shoppingCart";
   }

   @PostMapping("/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid ShoppingCart shoppingCart, BindingResult bindingResult)
   {
      shoppingCart.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("shoppingCart/form-update");
      }
      shoppingCartDao.update(shoppingCart);
      return new ModelAndView("redirect:/shoppingCart");
   }
}
