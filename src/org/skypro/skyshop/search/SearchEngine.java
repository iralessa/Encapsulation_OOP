package org.skypro.skyshop.search;
import org.skypro.skyshop.product.Article;

import java.util.*;

public class SearchEngine {
    private final Set<Searchable> searchableItems;

    // конструктор
    public SearchEngine(int capacity) {
        // Используем TreeSet с нашим компаратором
        this.searchableItems = new TreeSet<>(new SearchableComparator());
    }

    public void add(Searchable item) {
        searchableItems.add(item);
    }

    // ИЗМЕНЕННЫЙ МЕТОД ПОИСКА
    public Set<Searchable> search(String query){
        String lowerCaseQuery = query.toLowerCase();
        Set<Searchable> results = new TreeSet<>(new SearchableComparator()); // Используем TreeSet для автоматической сортировки

        for (Searchable item : searchableItems) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                if (searchTerm != null && searchTerm.contains(lowerCaseQuery)) {
                    // Используем имя Searchable-объекта как ключ
                    results.add(item);
                }
            }
        }
        return results;
    }
    // Новый метод для поиска лучшего совпадения
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        String lowerCaseQuery = search.toLowerCase();
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : searchableItems) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                int count = countOccurrences(searchTerm, lowerCaseQuery);

                if (count > maxCount) {
                    maxCount = count;
                    bestMatch = item;
                }
            }
        }

        // Проверка на отсутствие результата
        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }


    // Вспомогательный метод для подсчета вхождений подстроки
    private int countOccurrences(String text, String query) {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(query, index)) != -1) {
            count++;
            index += query.length();
        }

        return count;
    }

    // Обновленный метод вывода результатов
    public static void printSearchResults(SearchEngine searchEngine, String query) {
        System.out.println("\nПоиск по запросу: '" + query + "'");
        Set<Searchable> results = searchEngine.search(query);
        boolean found = !results.isEmpty();

        if (found) {
            int i = 1;
            for (Searchable result : results) {
                System.out.println(i + ". Найден элемент: " + result.getName());
                if (result instanceof Article) {
                    System.out.println("- Тип: СТАТЬЯ");
                    System.out.println("- Длина названия: " + result.getName().length() + " символов");
                } else {
                    System.out.println("- Тип: ТОВАР");
                }
                i++;
            }
        } else {
            System.out.println("По запросу '" + query + "' ничего не найдено");
        }
    }
}