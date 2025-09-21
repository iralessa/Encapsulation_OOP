package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;


public class App {
    public static void main(String[] args) {

        // Создаем поисковую систему с емкостью 20 элементов
        SearchEngine searchEngine = new SearchEngine(20);

        // Создаем корзину с максимальным размером
        ProductBasket basket = new ProductBasket(10);
        // Создаем продукты
        System.out.println("************ Создаем продукты ***************************");
        try {
            // Корректные продукты
            Product product1 = new SimpleProduct("Яблоко", 50);
            System.out.println(product1 +"создан успешно");
            Product product2 = new SimpleProduct("Банан", 70);
            System.out.println(product2 +"создан успешно");
            Product product4 = new SimpleProduct("Гранат", 200);
            System.out.println(product4 +"создан успешно");
            Product product5 = new SimpleProduct("Арбуз", 800);
            System.out.println(product5 +"создан успешно");
            Product product6 = new SimpleProduct("Виноград", 120);
            System.out.println(product6 +"создан успешно");

            // Продукты с намеренными ошибками для демонстрации
            // необходимо поочереди закоментировать продукты, после чего запустить выполнение программы.
//            Product product3 = new SimpleProduct("Апельсин", -100);    // Некорректная цена
//            System.out.println(product3 +"создан успешно");
//            Product product7 = new SimpleProduct("", 700);
//            System.out.println(product7 +"создан успешно");// Пустое название
//            Product product8 = new SimpleProduct(null, 50);
//            System.out.println(product8 +"создан успешно");// Null в названии
//            Product product9 = new SimpleProduct("Вишня", 0);
//            System.out.println( product9 +"создан успешно"); // Цена равна нулю

            // Создаем discounted и fix price продукты
            Product discountedProduct1 = new DiscountedProduct("Кроссовки", 5000, 50); // 50% скидка
            System.out.println("Продукт"+ discountedProduct1 +"создан успешно");
           // Product discountedProduct2 = new DiscountedProduct("Туфли", 10000, 101); // Некорректный процент скидки
          //  System.out.println("Продукт"+ discountedProduct2 +"создан успешно");
            Product fixPriceProduct = new FixPriceProduct("Книга"); // Используем фикс. цену из константы
            System.out.println("Продукт"+ fixPriceProduct +"создан успешно");

        // Демонстрация проверки данных
        // Проверка корректности созданных продуктов

        // Добавляем товары в корзину
        basket.addProduct(product1); // Яблоко
        basket.addProduct(product2); // Банан
        basket.addProduct(discountedProduct1); // Кроссовки (со скидкой)
        basket.addProduct(product4); // Гранат
       // basket.addProduct(discountedProduct2);
        basket.addProduct(fixPriceProduct); // Книга (фиксированная цена)
//        basket.addProduct(product7); // Дыня
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
       // searchEngine.add(discountedProduct2);
        searchEngine.add(fixPriceProduct);
        searchEngine.add(product1);
        searchEngine.add(product2);
       // searchEngine.add(product3);
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

            // Демонстрация поиска лучшего совпадения
            System.out.println("\n  _______________Демонстрация поиска лучшего совпадения:___________");
            try {
                // Демонстрация успешного поиска
                System.out.println("\nПоиск лучшего совпадения:");
                Searchable bestResult = searchEngine.findBestMatch("Кроссовки");
                System.out.println("Лучший результат найден: " + bestResult.getName());

                // Демонстрация обработки исключения
                searchEngine.findBestMatch("Ролики");

        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }


            System.out.println("Все продукты созданы успешно");
        } catch (IllegalArgumentException e) {
            System.out.println("!!! Ошибка при создании продукта: " + e.getMessage());
            e.printStackTrace();
        }
        basket.clear();
        System.out.println("______________________");
        System.out.println("Корзина очищена:");
        basket.printBasket(); //  "Корзина пуста."
    }
}
