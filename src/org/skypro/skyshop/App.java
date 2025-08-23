package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct.SimpleProduct;

public class App {
    public static void main(String[] args) {
        // Создаем корзину с максимальным размером
        ProductBasket basket = new ProductBasket(7);

        Product product1 = new SimpleProduct("Яблоко", 50);
        Product product2 = new SimpleProduct("Банан", 70);
        Product product3 = new SimpleProduct("Апельсин", 100);
        Product product4 = new SimpleProduct("Гранат", 200);
        Product product5 = new SimpleProduct("Арбуз", 800);
        Product product6 = new SimpleProduct("Виноград", 120);


        // Создаем discounted и fix price продукты
       Product discountedProduct1 = new DiscountedProduct("Кроссовки", 5000, 50); // 50% скидка
        Product discountedProduct2 = new DiscountedProduct("Туфли", 10000, 50); // 50% скидка
        Product fixPriceProduct = new FixPriceProduct("Книга"); // Используем фикс. цену из константы

        // Добавляем товары в корзину
        basket.addProduct(product1); // Яблоко
        basket.addProduct(product2); // Банан
        basket.addProduct(discountedProduct1); // Кроссовки (со скидкой)
        basket.addProduct(product4); // Гранат
        basket.addProduct(discountedProduct2);
        basket.addProduct(fixPriceProduct); // Книга (фиксированная цена)
        basket.addProduct(product3); // Апельсин
        // Попытка добавить лишний товар
        basket.addProduct(product5); // Арбуз - Должно вывести "Корзина полна!"
        basket.addProduct(product6); // Виноград - Должно вывести "Корзина полна!"
        basket.addProduct(discountedProduct1);

        // Выводим содержимое корзины в новом формате
        basket.printBasket();


      //  System.out.println("Есть ли яблоко в корзине? " + basket.containsProduct("Яблоко"));
        //System.out.println("Есть ли груша в корзине? " + basket.containsProduct("Груша"));

        basket.clear();
        System.out.println("Корзина очищена:");
        basket.printBasket(); // Теперь должно вывести "Корзина пуста."
    }
}