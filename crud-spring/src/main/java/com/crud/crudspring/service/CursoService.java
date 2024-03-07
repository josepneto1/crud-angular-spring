package com.crud.crudspring.service;

import com.crud.crudspring.model.Curso;
import com.crud.crudspring.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> lista(){
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(@PathVariable @NotNull @Positive Long id){
        return cursoRepository.findById(id);
    }

    public Curso criar(@Valid Curso curso){
        return cursoRepository.save(curso);
    }

    public Optional<Curso> atualizar(@NotNull @Positive Long id, @Valid Curso curso){
        return cursoRepository.findById(id)
                .map(registro -> {
                    registro.setNome(curso.getNome());
                    registro.setCategoria(curso.getCategoria());
                    return cursoRepository.save(registro);
                });
    }

    public boolean deletar(@NotNull @Positive Long id){
        return cursoRepository.findById(id)
                .map(registro -> {
                    cursoRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
