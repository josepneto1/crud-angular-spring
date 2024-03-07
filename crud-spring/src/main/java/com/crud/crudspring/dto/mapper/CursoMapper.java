package com.crud.crudspring.dto.mapper;

import com.crud.crudspring.dto.CursoDTO;
import com.crud.crudspring.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoDTO toDTO(Curso curso){
        if(curso == null){
            return null;
        }
        return new CursoDTO(curso.getId(), curso.getNome(), curso.getCategoria());
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
        curso.setCategoria(cursoDTO.categoria());
        return curso;
    }
}
