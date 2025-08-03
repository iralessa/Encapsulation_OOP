package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        // Создаем корзину с максимальным размером 3 товара
        ProductBasket basket = new ProductBasket(5);

        Product product1 = new Product("Яблоко", 50);
        Product product2 = new Product("Банан", 70);
        Product product3 = new Product("Апельсин", 100);
        Product product4 = new Product("Гранат", 200);
        Product product5 = new Product("Арбуз", 800);
        Product product6 = new Product("Виноград", 120);  // Попытка добавить лишний товар

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);
        basket.addProduct(product6); // Должно вывести "Корзина полна!"

        basket.printBasket();

        System.out.println("Есть ли яблоко в корзине? " + basket.containsProduct("Яблоко"));
        System.out.println("Есть ли груша в корзине? " + basket.containsProduct("Груша"));
        basket.clear();
        System.out.println("Корзина очищена:");
        basket.printBasket();
    }
}