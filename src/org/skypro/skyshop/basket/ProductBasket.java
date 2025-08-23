package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;
public class ProductBasket {
    private final Product[] products;
    private int productCount; // Количество добавленных продуктов в корзину
    private final int maxSize; // Максимальный размер корзины
    private int totalCost;
    private int specialProductCount;
    private boolean isFullMessageShown = false;
    public ProductBasket(int maxSize) {
        this.products = new Product[maxSize];
        this.maxSize = maxSize;
        this.productCount = 0;
        this.totalCost = 0;//  новые поле
        this.specialProductCount = 0;//  новые поле
    }
    public void addProduct(Product product) {
        if (productCount < maxSize) {
            products[productCount] = product;
            totalCost += product.getPrice();
            if (product.isSpecial()) { // Здесь используется метод isSpecial() из SimpleProduct
                specialProductCount++;
            }
            productCount++; // Увеличиваем счетчик добавленных товаров
            // System.out.println("Товар: " + product.getName() + " добавлен в корзину "+ "стоимость " + product.getPrice());
        } else {
            if (!isFullMessageShown) {
                System.out.println("ВНИМАНИЕ! Корзина полна!");
                isFullMessageShown = true; // Помечаем, что сообщение уже выведено

            }
        }
    }
    public int getTotalPrice() {
        return totalCost; // Просто возвращаем накопленную сумму
    }

    public void printBasket() {
        if (productCount == 0) {
            System.out.println("Корзина пуста.");
            return;
        }
        System.out.println("--- Содержимое корзины ---");
        // только реально добавленные товары
        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].toString());
        }
        // Выводим итоговую информацию
        System.out.println("Итого: " + getTotalPrice()+" рублей");
        System.out.println("Специальных товаров: " + specialProductCount);
        System.out.println("---------------------------");
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
            products[i] = null;
        }
        productCount = 0;
        // Сбрасываем и новые поля
        totalCost = 0;
        specialProductCount = 0;
    }
}