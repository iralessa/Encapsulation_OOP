package org.skypro.skyshop.search;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> searchableItems;

    // конструктор
    public SearchEngine(int capacity) {
        // Используем список вместо массива
        this.searchableItems = new ArrayList<>(capacity);
    }

    public void add(Searchable item) {
        searchableItems.add(item);
    }

    // Модифицированный метод поиска, возвращающий отсортированную Map
    public Map<String, Searchable> search(String query) {
        String lowerCaseQuery = query.toLowerCase();
        Map<String, Searchable> results = new TreeMap<>(); // Используем TreeMap для автоматической сортировки

        for (Searchable item : searchableItems) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                if (searchTerm != null && searchTerm.contains(lowerCaseQuery)) {
                    // Используем имя Searchable-объекта как ключ
                    results.put(item.getName(), item);
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
        System.out.println("\nПоиск по запросу: " + query);
        Map<String, Searchable> results = searchEngine.search(query);
        boolean found = !results.isEmpty();

        if (found) {
            int i = 1;
            for (Map.Entry<String, Searchable> entry : results.entrySet()) {
                System.out.println(i + ". Найден элемент: " + entry.getValue().getName());
                i++;
            }
        } else {
            System.out.println("Ничего не найдено");
        }
    }
}