package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    // Геттеры для доступа к полям
    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        // Форматируем вывод без лишних слов "Название статьи" и "Текст статьи"
        return title + "\n" + text;
    }

    @Override
    public String getSearchTerm() {
        // Объединяем заголовок и текст для поиска в нижнем регистре
        return (title + " " + text).toLowerCase();
    }

    @Override
    public String getContentType() {
        return "СТАТЬЯ";
    }

    @Override
    public String getName() {
        // Имя статьи - это её заголовок
        return title;
    }
}