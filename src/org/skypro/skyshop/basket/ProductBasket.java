package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new LinkedList<>();
    private int totalCost;
    private int specialProductCount;

    // Убрали параметры, связанные с размером корзины, т.к. LinkedList динамически расширяется
    public ProductBasket() {
        this.totalCost = 0;
        this.specialProductCount = 0;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
        if (product.isSpecial()) {
            specialProductCount++;
        }
    }

    public int getSize() {
        return products.size();
    }
    // Новый метод удаления по имени
    public List<Product> removeProductByName(String name) {
            List<Product> removedProducts = new LinkedList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                iterator.remove();
                removedProducts.add(product);
                totalCost -= product.getPrice();
                if (product.isSpecial()) {
                    specialProductCount--;
                }
            }
        }
        return removedProducts;
    }

    public int getTotalPrice() {
        return totalCost;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста.");
            return;
        }

        System.out.println("--- Содержимое корзины ---");
        System.out.println("Количество товаров: " + products.size());
        for (Product product : products) {
            System.out.println(product.toString());
        }
        System.out.println("Итого: " + getTotalPrice() + " рублей");
        System.out.println("Специальных товаров: " + specialProductCount);
        System.out.println("---------------------------");
    }

    public void clear() {
        products.clear();
        totalCost = 0;
        specialProductCount = 0;
    }
}