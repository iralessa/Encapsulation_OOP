package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        // Добавляем проверку на корректность названия
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым, null или состоять только из пробелов"+ "цена продукта"+ getPrice());
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String getSearchTerm() {
        return name.toLowerCase(); // Правильно возвращаем имя в нижнем регистре
    }
    @Override
    public String getContentType() {
        return "ТОВАР"; // Правильно возвращаем тип контента
    }

    @Override
    public String getName() {
        return name; // Правильно возвращаем имя
    }


    // Абстрактный метод для получения цены
    public abstract int getPrice();

    // Абстрактный метод для проверки на специальный товар
    public abstract boolean isSpecial();
}


