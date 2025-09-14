package org.skypro.skyshop.search;
import org.skypro.skyshop.search.BestResultNotFound; // Добавляем импорт

public class SearchEngine {
    private final Searchable[] searchableItems;
    private int currentSize;

    // конструктор
    public SearchEngine(int capacity) {
        this.searchableItems = new Searchable[capacity];
        this.currentSize = 0;
    }

    public void add(Searchable item) {
        if (currentSize < searchableItems.length) {
            searchableItems[currentSize] = item;
            currentSize++;
        }
    }

    public Searchable[] search(String query) {
        String lowerCaseQuery = query.toLowerCase();
        Searchable[] results = new Searchable[5];
        int resultIndex = 0;

        for (Searchable item : searchableItems) {
            if (item != null) {
                String searchTerm = item.getSearchTerm();

                if (searchTerm != null && searchTerm.contains(lowerCaseQuery)) {
                    results[resultIndex] = item;
                    resultIndex++;
                    if (resultIndex == 5) {
                        break;
                    }
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

    public static void printSearchResults(SearchEngine searchEngine, String query) {
        System.out.println("\nПоиск по запросу: " + query);
        Searchable[] results = searchEngine.search(query);
        boolean found = false;

        for (int i = 0; i < results.length; i++) {
            if (results[i] != null) {
                System.out.println((i + 1) + ". Найден элемент: " + results[i].getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Ничего не найдено");
        }
    }
}