package br.com.beani.currencyConverter.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ShoppingCart
{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private BigDecimal value;

   public Integer getId()
   {
      return this.id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public BigDecimal getValue()
   {
      return this.value;
   }

   public void setValue(BigDecimal value)
   {
      this.value = value;
   }
}
