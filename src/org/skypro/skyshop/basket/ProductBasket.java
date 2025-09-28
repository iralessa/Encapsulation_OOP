package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    // Меняем структуру данных на Map
    private final Map<String, List<Product>> products = new TreeMap<>(); // Используем TreeMap для сортировки по имени
    private int totalCost;
    private int specialProductCount;

    public ProductBasket() {
        this.totalCost = 0;
        this.specialProductCount = 0;
    }

    // Переписываем метод добавления продукта
    public void addProduct(Product product) {
        String name = product.getName();
        if (!products.containsKey(name)) {
            products.put(name, new ArrayList<>());
        }
        products.get(name).add(product);
        totalCost += product.getPrice();
        if (product.isSpecial()) {
            specialProductCount++;
        }
    }

    // Переписываем метод удаления
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = new ArrayList<>();

        if (products.containsKey(name)) {
            List<Product> productList = products.get(name);
            for (Product product : productList) {
                removedProducts.add(product);
                totalCost -= product.getPrice();
                if (product.isSpecial()) {
                    specialProductCount--;
                }
            }
            products.remove(name);
        }
        return removedProducts;
    }

    // Переписываем метод подсчета общего количества товаров
    public int getSize() {
        int size = 0;
        for (List<Product> productList : products.values()) {
            size += productList.size();
        }
        return size;
    }

    // Переписываем метод вывода корзины
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста.");
            return;
        }

        System.out.println("--- Содержимое корзины ---");
        System.out.println("Количество товаров: " + getSize());

        // Используем вложенный цикл для перебора всех продуктов
        for (Map.Entry<String, List<Product>> entry : products.entrySet()) {
            for (Product product : entry.getValue()) {
                System.out.println(product.toString());
            }
        }

        System.out.println("Итого: " + getTotalPrice() + " рублей");
        System.out.println("Специальных товаров: " + specialProductCount);
        System.out.println("---------------------------");
    }

    public int getTotalPrice() {
        return totalCost;
    }

    public void clear() {
        products.clear();
        totalCost = 0;
        specialProductCount = 0;
    }
}