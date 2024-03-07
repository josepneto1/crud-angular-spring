package com.crud.crudspring.service;

import com.crud.crudspring.exception.RegistroNaoEncontradoException;
import com.crud.crudspring.model.Curso;
import com.crud.crudspring.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    public Curso buscarPorId(@PathVariable @NotNull @Positive Long id){
        return cursoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public Curso criar(@Valid Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso atualizar(@NotNull @Positive Long id, @Valid Curso curso){
        return cursoRepository.findById(id)
                .map(registro -> {
                    registro.setNome(curso.getNome());
                    registro.setCategoria(curso.getCategoria());
                    return cursoRepository.save(registro);
                }).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public void deletar(@NotNull @Positive Long id){
        cursoRepository.delete(cursoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id)));
    }
}
