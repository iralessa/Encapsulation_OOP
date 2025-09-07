package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.product.DiscountedProduct.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;


public class App {
    public static void main(String[] args) {

        // Создаем поисковую систему с емкостью 20 элементов
        SearchEngine searchEngine = new SearchEngine(20);

        // Создаем корзину с максимальным размером
        ProductBasket basket = new ProductBasket(10);
        // Создаем продукты
        Product product1 = new SimpleProduct("Яблоко", 50);
        Product product2 = new SimpleProduct("Банан", 70);
        Product product3 = new SimpleProduct("Апельсин", 100);
        Product product4 = new SimpleProduct("Гранат", 200);
        Product product5 = new SimpleProduct("Арбуз", 800);
        Product product6 = new SimpleProduct("Виноград", 120);
        Product product7 = new SimpleProduct("Дыня", 700);

        // Создаем discounted и fix price продукты
       Product discountedProduct1 = new DiscountedProduct("Кроссовки", 5000, 50); // 50% скидка
        Product discountedProduct2 = new DiscountedProduct("Туфли", 10000, 50); // 50% скидка
        Product fixPriceProduct = new FixPriceProduct("Книга"); // Используем фикс. цену из константы

        basket.addProduct(product1); // Яблоко
        basket.addProduct(product2); // Банан
        basket.addProduct(discountedProduct1); // Кроссовки (со скидкой)
        basket.addProduct(product4); // Гранат
        basket.addProduct(discountedProduct2);
        basket.addProduct(fixPriceProduct); // Книга (фиксированная цена)
        basket.addProduct(product7); // Дыня
        // Попытка добавить лишний товар
        basket.addProduct(product5); // Арбуз - Должно вывести "Корзина полна!"
        basket.addProduct(product6); // Виноград - Должно вывести "Корзина полна!"

        // Создаем статьи
        Article article1 = new Article("Как выбрать кроссовки", "Подробное руководство по выбору спортивной обуви...");
        Article article2 = new Article("Фрукты - наши друзья", "Обзор полезных свойств яблок, бананов и других фруктов...");
        Article article3 = new Article("Арбузная диета", "Вся польза арбузной диеты...");
        Article article4 = new Article("Ананасовый сок ", "Польза ананасового сока ...");
        // Добавляем статьи в поиск
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);
        searchEngine.add(article4);

        // Добавляем товары в поиск
        searchEngine.add(discountedProduct1);
        searchEngine.add(discountedProduct2);
        searchEngine.add(fixPriceProduct);
        searchEngine.add(product1);
        searchEngine.add(product2);
        searchEngine.add(product3);
        searchEngine.add(product4);
        searchEngine.add(product5);
        searchEngine.add(product6);

        // Выводим содержимое корзины
        basket.printBasket();

        // Демонстрация поиска
        System.out.println("\n Анализ поиска:");
        // Список поисковых запросов
        String[] searchQueries = {"кроссовки", "фрукт", "Книга", "Ананас", "Арбуз", "Туфли", "Цветы"};
        for (String query : searchQueries) {
            System.out.println("\nПоиск по запросу: '" + query + "'");
            Searchable[] results = searchEngine.search(query);
            boolean found = false;
            for (Searchable result : results) {
                if (result != null) {
                    found = true;
                    System.out.println("- Найден элемент: " + result.getName());
                    System.out.println("- Тип: " + result.getContentType());
                }
            }

            if (!found) {
                System.out.println("По запросу '" + query + "' ничего не найдено");
            }
        }

        basket.clear();
        System.out.println("______________________");
        System.out.println("Корзина очищена:");
        basket.printBasket(); //  "Корзина пуста."
    }
}
