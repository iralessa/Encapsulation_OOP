package org.skypro.skyshop.product.FixPriceProduct;

import org.skypro.skyshop.product.Product;

public class FixPriceProduct extends Product {
    // Приватная статическая финальная константа для фиксированной цены
    private static final int FIXED_PRICE = 55; // Установим какую-то примерную фиксированную цену

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    @Override
    public String toString() {
        return "Товар с фиксированной ценой -  " + getName() + ": Фиксированная цена " + getPrice()+ " руб.";
    }
}
