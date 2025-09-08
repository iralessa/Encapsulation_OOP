package org.skypro.skyshop.search;

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