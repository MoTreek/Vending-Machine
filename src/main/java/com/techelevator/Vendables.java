package com.techelevator;

import java.math.BigDecimal;

public interface  Vendables {

   int getQuantity();

   BigDecimal getPrice();

   String getSound();

   String getLocation();

   String getName();

   void purchaseSnack();
   boolean isAvailable();

}
