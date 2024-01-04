package com.fase4FIAP.streaming.domain.enums;

public enum Category {

    MUSIC("Music"),
    EDUCATION("Education"),

    ENTERTAINMENT("Entertainment"),
    SPORTS("Sports"),
    NOTICES("Notices"),
    OTHERS("Others");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category fromString(String text) {
        String textFormat = text.trim();
        for (Category category : Category.values()) {
            if (category.getCategoryName().equalsIgnoreCase(textFormat)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Categoria não reconhecida: " + textFormat);
    }

    public String getCategoryName() {
        return categoryName;
    }
}
