package com.superdev.store.controller;

import com.superdev.store.model.Categoria;
import com.superdev.store.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> buscarTodos() {
        return categoriaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable int id) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(id);
        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaEncontrada);
    }

    @PostMapping
    public Categoria adicionar(@RequestBody Categoria categoria) {
        return categoriaService.salvar(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> alterar (@RequestBody Categoria categoria, @PathVariable int id) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(id);
        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaService.salvar(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> excluir(@PathVariable int id) {
        Categoria categoriaEncontrada = categoriaService.buscarPorId(id);
        if (categoriaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.excluir(categoriaEncontrada);
        return ResponseEntity.ok(categoriaEncontrada);
    }
}
