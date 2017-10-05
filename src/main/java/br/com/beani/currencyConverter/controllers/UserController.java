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

import br.com.beani.currencyConverter.daos.UserDao;
import br.com.beani.currencyConverter.models.User;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController
{

   @Autowired
   private UserDao userDao;

   @GetMapping("/form")
   public ModelAndView form(User user)
   {
      ModelAndView modelAndView = new ModelAndView("user/form-add");
      return modelAndView;

   }

   @PostMapping
   public ModelAndView save(@Valid User user, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(user);
      }
      userDao.save(user);
      return new ModelAndView("redirect:/user");
   }

   @GetMapping("/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("user/form-update");
      modelAndView.addObject("user", userDao.findById(id));
      return modelAndView;
   }

   @GetMapping
   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
   {
      ModelAndView modelAndView = new ModelAndView("user/list");
      modelAndView.addObject("paginatedList", userDao.paginated(page, 10));
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @GetMapping("/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      User user = userDao.findById(id);
      userDao.remove(user);
      return "redirect:/user";
   }

   @PostMapping("/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid User user, BindingResult bindingResult)
   {
      user.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("user/form-update");
      }
      userDao.update(user);
      return new ModelAndView("redirect:/user");
   }
}
