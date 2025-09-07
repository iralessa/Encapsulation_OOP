package org.skypro.skyshop.search;

public interface Searchable {
    // Метод для получения поискового термина
    // Должен возвращать текст для поиска
    String getSearchTerm();
    // Метод для получения типа контента
    // Должен возвращать строку с названием типа контента
    String getContentType();
    // Метод для получения имени объекта
    // Возвращает имя Searchable-объекта
    String getName();
    // метод для строкового представления
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}
