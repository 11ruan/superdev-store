package com.superdev.store.service;

import com.superdev.store.model.Produto;
import com.superdev.store.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(int id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return  produtoRepository.save(produto);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }

    public boolean comprar(int id, int quantidade) {
        Produto produtoEncontrado = findById(id);
        if (produtoEncontrado == null) {
            return false;
        }

        produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() + quantidade);
        save(produtoEncontrado);
        return true;
    }

    public boolean vender(int id, int quantidade) {
        Produto produtoEncontrado = findById(id);
        if (produtoEncontrado == null || produtoEncontrado.getQuantidade() < quantidade) {
            return false;
        }

        produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() - quantidade);
        save(produtoEncontrado);
        return true;
    }
}
