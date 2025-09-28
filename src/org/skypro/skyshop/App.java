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

import java.util.*;


public class App {
    public static void main(String[] args) {

        // Создаем поисковую систему с емкостью 20 элементов
        SearchEngine searchEngine = new SearchEngine(20);

        // Создаем список для хранения всех удаленных товаров
        List<Product> allRemovedProducts = new ArrayList<>();
        // Создаем корзину с максимальным размером
        ProductBasket basket = new ProductBasket();
        // Создаем продукты
        System.out.println("************ Создаем товары ***************************");
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
            Product product7 = new SimpleProduct("Виноград", 125);
            System.out.println(product7 +"создан успешно");

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
            basket.addProduct(product7); // Виноград - одинаковое название. Проверка на дубль

        // Создаем статьи
        Article article1 = new Article("Как выбрать кроссовки", "Подробное руководство по выбору спортивной обуви...");
        Article article2 = new Article("Фрукты - наши друзья", "Обзор полезных свойств яблок, бананов и других фруктов...");
        Article article3 = new Article("Арбузная диета", "Вся польза арбузной диеты...");
        Article article4 = new Article("Ананасовый сок! Самая полезная статься про тропические фрукты", "Польза ананасового сока ...");
        Article article5 = new Article("Выбор правильной обуви для бега", "Кроссовки беговые ...");
        Article article6 = new Article("Книга - лучший друг", "Польза от раннего чтения ...");
        Article article7 = new Article("Цветы для жизни и ради жизни", "Цветная жизнь ...");
        Article article8 = new Article("Цветы для жизни и ради жизни", "Цветная жизнь ...");
        Article article9 = new Article("Для жизни и ради жизни цветы", "Цветная жизнь ...");

        // Добавляем статьи в поиск
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);
        searchEngine.add(article4);
        searchEngine.add(article5);
        searchEngine.add(article6);
        searchEngine.add(article7);
        searchEngine.add(article8);
        searchEngine.add(article9);

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
            // Создаем Set вместо массива
            Set<String> searchQueries = new HashSet<>();
            searchQueries.add("кроссовки".toLowerCase());
            searchQueries.add("фрукт".toLowerCase());
            searchQueries.add("Книга".toLowerCase());
            searchQueries.add("Ананас".toLowerCase());
            searchQueries.add("Арбуз".toLowerCase());
            searchQueries.add("Кроссовки".toLowerCase());
            searchQueries.add("Туфли".toLowerCase());
            searchQueries.add("Зерно".toLowerCase());
            searchQueries.add("Цветы".toLowerCase());
            searchQueries.add("книга".toLowerCase());  // не добавится

            for (String query : searchQueries) {
                System.out.println("\nПоиск по запросу: '" + query + "'");
                Set<Searchable> results = searchEngine.search(query); // Изменили тип
                if (results.isEmpty()) {
                    System.out.println("По запросу не найдено ни одной статьи или товара");
                    continue;
                }

                // Изменили способ перебора результатов
                int i = 1;
                for (Searchable result : results) {
                    if (result instanceof Article) { // Проверяем, является ли элемент статьей
                        System.out.println("- Тип: СТАТЬЯ");
                        System.out.println("- Название статьи: " + result.getName());
                        System.out.println("- Длина названия: " + result.getName().length() + " символов");
                    } else {
                        System.out.println("- Тип: ТОВАР");
                    }
                    i++;
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

        // Вставляем новую демонстрацию здесь
        System.out.println("\n************ Демонстрация удаления продуктов из корзины **************");

        // Получаем количество продуктов ДО удаления
        int countBefore = basket.getSize();
        System.out.println("\nКоличество продуктов в корзине ДО всех операций удаления: " + countBefore);
// Сохраняем название удаляемого товара
        String productName = "Тыква";
        // Удаляем существующий продукт
        List<Product> removedProducts1 = basket.removeProductByName("Банан");
        allRemovedProducts.addAll(removedProducts1); // Добавляем в общий список
        // Пытаемся удалить несуществующий продукт
        List<Product> removedProducts2 = basket.removeProductByName("Гранат");
        allRemovedProducts.addAll(removedProducts2); // Добавляем в общий список
        List<Product> removedProducts3 = basket.removeProductByName("Тыква");
// Проверяем, были ли удалены товары
        if (removedProducts3.isEmpty()) {
            System.out.println("!!! Предупреждение: Вы пытаетесь удалить несуществующий продукт! Продукта '" + productName + "' в корзине не было!!!");
        } else {
            allRemovedProducts.addAll(removedProducts3); // Добавляем в общий список
        }

        // Выводим общий список удаленных продуктов
        System.out.println("\n************ Список удаленных продуктов **************");
        if (!allRemovedProducts.isEmpty()) {
            System.out.println("Всего удалено товаров: " + allRemovedProducts.size());
            for (Product product : allRemovedProducts) {
                System.out.println("- " + product.getName() + " Цена: " + product.getPrice() + " руб.");
            }
        } else {
            System.out.println("Список удаленных продуктов пуст");
        }
        // Выводим содержимое корзины
        System.out.println("\n");
        basket.printBasket();



        // Получаем итоговое количество продуктов
        int countAfter = basket.getSize();
        System.out.println("\nИтоговое количество продуктов в корзине: " + countAfter);
        System.out.println("Всего удалено продуктов: " + (countBefore - countAfter));
        basket.clear();
        System.out.println("______________________");
        System.out.println("Корзина очищена:");
        basket.printBasket(); //  "Корзина пуста."



    }
}
