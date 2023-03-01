/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.models;

import com.google.gson.Gson;
import com.pato.anotacoes.query.ClassColunas;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Tabela;
import com.pato.anotacoes.util.Ignore;
import java.time.LocalDateTime;

/**
 *
 * @author patrick.oliveira
 */
@Tabela("TABELA")
public class Termo {

    @ClassColunas
    private Cliente cliente;
    
    @Coluna(nome = "DTNOW")
    private LocalDateTime dataHoje;
    
    @Ignore
    private String fielIgnorado;
    
    @Coluna(nome = "INTEGER")
    private Integer inteiro;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHoje() {
        return dataHoje;
    }

    public void setDataHoje(LocalDateTime dataHoje) {
        this.dataHoje = dataHoje;
    }

    public String getFielIgnorado() {
        return fielIgnorado;
    }

    public void setFielIgnorado(String fielIgnorado) {
        this.fielIgnorado = fielIgnorado;
    }

    public Integer getInteiro() {
        return inteiro;
    }

    public void setInteiro(Integer inteiro) {
        this.inteiro = inteiro;
    }
    

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
