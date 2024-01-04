package com.fase4FIAP.streaming.domain.enums;

public enum Category {

    MUSICA("Musica"),
    EDUCACAO("Educacao"),
    ENTRETENIMENTO("Entretenimento"),
    ESPORTES("Esportes"),
    NOTICIAS("Noticias"),
    OUTROS("Outros");

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
