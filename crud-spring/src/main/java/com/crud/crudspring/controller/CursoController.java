package com.crud.crudspring.controller;

import com.crud.crudspring.model.Curso;
import com.crud.crudspring.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
//@AllArgsConstructor se usar não precisa se construtor
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) { // pode ser substituído por @AllArgsConstrutor
        this.cursoRepository = cursoRepository;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Curso> lista(){
        return cursoRepository.findAll();
    }
}
