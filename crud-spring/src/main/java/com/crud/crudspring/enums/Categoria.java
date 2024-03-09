package com.crud.crudspring.enums;

public enum Categoria {
    BACK_END("Back-end"), FRONT_END("Front-end");

    private String value;

    Categoria(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
