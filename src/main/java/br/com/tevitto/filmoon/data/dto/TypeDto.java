package br.com.tevitto.filmoon.data.dto;

import br.com.tevitto.filmoon.data.types.Category;

public class TypeDto {

    private Long id;

    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
