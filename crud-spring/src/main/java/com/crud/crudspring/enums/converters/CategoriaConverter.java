package com.crud.crudspring.enums.converters;

import com.crud.crudspring.enums.Categoria;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoriaConverter implements AttributeConverter<Categoria, String> {
    @Override
    public String convertToDatabaseColumn(Categoria categoria) {
        if (categoria == null){
            return null;
        }
        return categoria.getValue();
    }

    @Override
    public Categoria convertToEntityAttribute(String value) {
        if (value == null){
            return null;
        }
        return Stream.of(Categoria.values())
                .filter(cat -> cat.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
