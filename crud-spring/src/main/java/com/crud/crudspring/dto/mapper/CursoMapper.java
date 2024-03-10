package com.crud.crudspring.dto.mapper;

import com.crud.crudspring.dto.AulaDTO;
import com.crud.crudspring.dto.CursoDTO;
import com.crud.crudspring.enums.Categoria;
import com.crud.crudspring.model.Curso;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapper {
    public CursoDTO toDTO(Curso curso){
        if(curso == null){
            return null;
        }

        List<AulaDTO> aulas = curso.getAulas()
                .stream()
                .map(aula -> new AulaDTO(aula.getId(), aula.getNome(), aula.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CursoDTO(curso.getId(), curso.getNome(), curso.getCategoria().getValue(), aulas);
    }

    public Curso toEntity(CursoDTO cursoDTO) {
        if(cursoDTO == null){
            return null;
        }

        Curso curso = new Curso();
        if(cursoDTO.id() != null){
            curso.setId(cursoDTO.id());
        }
        curso.setNome(cursoDTO.nome());
        curso.setCategoria(converteValorCategoria(cursoDTO.categoria()));
        return curso;
    }

    public Categoria converteValorCategoria(String valor){
        if (valor == null){
            return null;
        }

        return switch (valor){
            case "Front-end" -> Categoria.FRONT_END;
            case "Back-end" -> Categoria.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + valor);
        };
    }
}
