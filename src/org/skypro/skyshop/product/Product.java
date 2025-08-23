package org.skypro.skyshop.product;

public abstract class Product {
    private final String name;
    //private final int price;
   // public Product(String name, int price) {
     //   this.name = name;
       // this.price = price;
         public Product(String name) {
            this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract int getPrice();

    public abstract boolean isSpecial();
}
