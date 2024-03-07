package com.crud.crudspring.controller;

import java.util.List;

import com.crud.crudspring.dto.CursoDTO;
import com.crud.crudspring.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<CursoDTO> lista(){
        return cursoService.lista();
    }

    @GetMapping("/{id}")
    public CursoDTO buscarPorId(@PathVariable @NotNull @Positive Long id){
        return cursoService.buscarPorId(id);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO criar(@RequestBody @Valid @NotNull CursoDTO curso){
        return cursoService.criar(curso);
    }

    @PutMapping("/{id}")
    public CursoDTO atualizar(@PathVariable @NotNull @Positive Long id,
                              @RequestBody @Valid @NotNull CursoDTO curso){
        return cursoService.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable @NotNull @Positive Long id){
        cursoService.deletar(id);
    }
}










// o response entity é mais útil quando há necessidade de manipular a resposta
// @PostMapping
// public ResponseEntity<Curso> criar(@RequestBody Curso curso){
//     return ResponseEntity.status(HttpStatus.CREATED)
//         .body(cursoRepository.save(curso));
// }
