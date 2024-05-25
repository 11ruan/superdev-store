package com.superdev.store.controller;


import com.superdev.store.model.Caixa;
import com.superdev.store.model.Categoria;
import com.superdev.store.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @GetMapping
    public List<Caixa> buscarTodos() {
        return caixaService.buscarTodos();
    }


    @PostMapping
    public Caixa adicionar(@RequestBody Caixa caixa) {
        return caixaService.salvar(caixa);
    }

    @PatchMapping("/abrir/{id}")
    public ResponseEntity<Caixa> abrir(@PathVariable int id) {
        Caixa caixa = caixaService.abrir(id);
        if (caixa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caixa);
    }

    @PatchMapping("/fechar/{id}")
    public ResponseEntity<Caixa> fechar(@PathVariable int id) {
        Caixa caixa = caixaService.fechar(id);
        if (caixa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caixa);
    }

    @PatchMapping("/sacar/{id}")
    public ResponseEntity<Caixa> sacar(@PathVariable int id,@RequestBody Double saldo) {
        Caixa caixa = caixaService.sacar(id, saldo);
        if (caixa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caixa);
    }
}
