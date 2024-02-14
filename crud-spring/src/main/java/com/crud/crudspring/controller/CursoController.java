package com.crud.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
// import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudspring.model.Curso;
import com.crud.crudspring.repository.CursoRepository;

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
    
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso criar(@RequestBody Curso curso){
        return cursoRepository.save(curso);
  
    }
}

// o response entity é mais útil quando há necessidade de manipular a respoosta
// @PostMapping
// public ResponseEntity<Curso> criar(@RequestBody Curso curso){
//     return ResponseEntity.status(HttpStatus.CREATED)
//         .body(cursoRepository.save(curso));
// }
