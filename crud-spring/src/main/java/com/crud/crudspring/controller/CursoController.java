package com.crud.crudspring.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
// import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crud.crudspring.model.Curso;
import com.crud.crudspring.repository.CursoRepository;

@Validated
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

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable @NotNull @Positive Long id){
        return cursoRepository.findById(id)
                .map(registro -> ResponseEntity.ok().body(registro))
                .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso criar(@RequestBody @Valid Curso curso){
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable @NotNull @Positive Long id,
                                           @RequestBody @Valid Curso curso){
        return cursoRepository.findById(id)
                .map(registro -> {
                    registro.setNome(curso.getNome());
                    registro.setCategoria(curso.getCategoria());
                    Curso atualizado = cursoRepository.save(registro);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @NotNull @Positive Long id){
        return cursoRepository.findById(id)
                .map(registro -> {
                    cursoRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}










// o response entity é mais útil quando há necessidade de manipular a resposta
// @PostMapping
// public ResponseEntity<Curso> criar(@RequestBody Curso curso){
//     return ResponseEntity.status(HttpStatus.CREATED)
//         .body(cursoRepository.save(curso));
// }
