package org.skypro.skyshop.search;
import java.util.ArrayList;
import java.util.List;

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

    // Измененный метод поиска
    public List<Searchable> search(String query) {
        String lowerCaseQuery = query.toLowerCase();
        List<Searchable> results = new ArrayList<>();

        for (Searchable item : searchableItems) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();
                if (searchTerm != null && searchTerm.contains(lowerCaseQuery)) {
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
        System.out.println("\nПоиск по запросу: " + query);
        List<Searchable> results = searchEngine.search(query);
        boolean found = false;

        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". Найден элемент: " + results.get(i).getName());
            found = true;
        }

        if (!found) {
            System.out.println("Ничего не найдено");
        }
    }
}