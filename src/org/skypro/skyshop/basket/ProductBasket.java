package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import javax.swing.*;

public class ProductBasket {
    private final Product[] products;
    private int productCount; // Количество добавленных продуктов в корзину
    private final int MAX_SIZE; // Максимальный размер корзины

    public ProductBasket(int maxSize) {
        this.MAX_SIZE = 5;
        this.products = new Product[maxSize];
        this.productCount = 0;
    }

    public void addProduct(Product product) {
        if (productCount < MAX_SIZE) {
            products[productCount] = product;
            productCount++;

        } else {
            System.out.println("ВНИМАНИЕ! Корзина полна!");
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printBasket() {
        if (productCount == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].getName() + ": " + products[i].getPrice());
        }
        System.out.println("Общая стоимость корзины: " + getTotalPrice());
    }

    public boolean containsProduct(String productName) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < productCount; i++) {
            products[i] = null; // Чтобы сборщик мусора мог удалить объекты
        }
        productCount = 0;
    }
}