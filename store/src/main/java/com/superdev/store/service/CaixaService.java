package com.superdev.store.service;


import com.superdev.store.model.Caixa;
import com.superdev.store.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    public List<Caixa> buscarTodos() {
        return caixaRepository.findAll();
    }

    public Caixa buscarPorId(int id) {
        return caixaRepository.findById(id).orElse(null);
    }

    public Caixa salvar(Caixa caixa) {
        return caixaRepository.save(caixa);
    }

    public void excluir(Caixa caixa) {
        caixaRepository.delete(caixa);
    }

    public Caixa abrir(int id) {
        Caixa caixaValido = this.buscarPorId(id);
        if (caixaValido == null) {
            return null;
        }
        caixaValido.setStatus(true);
        this.salvar(caixaValido);
        return caixaValido;
    }

    public Caixa fechar(int id) {
        Caixa caixaValido = this.buscarPorId(id);
        if (caixaValido == null) {
            return null;
        }
        caixaValido.setStatus(false);
        this.salvar(caixaValido);
        return caixaValido;
    }

    public Caixa sacar(int id, Double saldo) {
        Caixa caixaValido = this.buscarPorId(id);
        if (caixaValido == null || caixaValido.getSaldo() < saldo) {
            return null;
        }
        caixaValido.setSaldo(caixaValido.getSaldo() - saldo);
        this.salvar(caixaValido);
        return caixaValido;

    }
}
