package org.skypro.skyshop.product.DiscountedProduct;

import org.skypro.skyshop.product.Product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercentage;

    public DiscountedProduct(String name, int basePrice, int discountPercentage) {
        super(name);
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public int getPrice() {
        int discountAmount = (int) (this.basePrice * (this.discountPercentage / 100.0));
        return this.basePrice - discountAmount;
    }
    @Override
    public boolean isSpecial() {
        return true;
    }
    @Override
    public String toString() {
        return "Товар со скидкой " + getName() + ": цена " + getPrice() + " руб. ( скидка: " + this.discountPercentage + "%)";
    }
}
