package org.skypro.skyshop.search;

import java.util.Comparator;

class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable s1, Searchable s2) {
        // Сначала сравниваем по типу (статьи перед товарами)
        int typeCompare = s1.getContentType().compareTo(s2.getContentType());

        if (typeCompare != 0) {
            return typeCompare;
        }

        // Затем сравниваем по длине названия (от большего к меньшему)
        int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());

        if (lengthCompare != 0) {
            return lengthCompare;
        }

        // И наконец, сравниваем по алфавиту
        return s1.getName().compareTo(s2.getName());
    }
}

