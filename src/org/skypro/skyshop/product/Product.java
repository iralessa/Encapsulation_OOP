package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
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