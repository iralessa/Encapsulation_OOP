package org.skypro.skyshop.product.SimpleProduct;


import org.skypro.skyshop.product.Product;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price) {
        // Вызываем конструктор родителя (Product)
        super(name);
        // Присваиваем цену нашему экземпляру
        this.price = price;
    }
    // Переопределяем абстрактный метод getPrice()
    @Override
    public int getPrice() {
        return this.price; // Возвращаем цену, которая хранится в этом объекте
    }
    @Override
    public boolean isSpecial() {
        return false;
    }
    @Override
    public String toString() {
        return "Товар " + getName() + ": " + getPrice() + " руб.";
    }
}

