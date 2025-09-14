package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercentage;
    public DiscountedProduct(String name, int basePrice, int discountPercentage) {
        super(name);

        // Проверка базовой цены
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена продукта " + name + " должна быть строго больше 0");
        }

        // Проверка процента скидки
        if (discountPercentage < 0 || discountPercentage >= 100) {
            throw new IllegalArgumentException("Процент скидки для продукта " + name + " должен быть в диапазоне от 0 до 100 включительно");
        }
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
