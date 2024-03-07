package com.crud.crudspring.service;

import com.crud.crudspring.dto.CursoDTO;
import com.crud.crudspring.dto.mapper.CursoMapper;
import com.crud.crudspring.exception.RegistroNaoEncontradoException;
import com.crud.crudspring.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> lista(){
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDTO) //curso -> cursoMapper.toDTO(curso)
                .collect(Collectors.toList());
    }

    public CursoDTO buscarPorId(@PathVariable @NotNull @Positive Long id){
        return cursoRepository.findById(id)
                .map(cursoMapper::toDTO) //curso -> cursoMapper.toDTO(curso)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public CursoDTO criar(@Valid @NotNull CursoDTO curso){
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO atualizar(@NotNull @Positive Long id, @Valid @NotNull CursoDTO curso){
        return cursoRepository.findById(id)
                .map(registro -> {
                    registro.setNome(curso.nome());
                    registro.setCategoria(curso.categoria());
                    return cursoMapper.toDTO(cursoRepository.save(registro));
                }).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public void deletar(@NotNull @Positive Long id){
        cursoRepository.delete(cursoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id)));
    }
}
