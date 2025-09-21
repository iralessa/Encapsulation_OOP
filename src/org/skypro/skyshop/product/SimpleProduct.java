package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price) {
        // Вызываем конструктор родителя (Product)
        super(name);
        // Добавляем проверку цены
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта " + name + " должна быть строго больше 0");
        }
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

