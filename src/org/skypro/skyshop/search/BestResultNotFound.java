package org.skypro.skyshop.search;


public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не нашлось подходящей статьи по запросу: " + query);
    }
}
