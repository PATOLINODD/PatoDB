/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.models;

import com.google.gson.Gson;
import com.pato.anotacoes.query.ChaveEstrangeira;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Tabela;
import com.pato.enuns.TipoDeDado;

/**
 *
 * @author PATOLINODD
 */
@Tabela("TELEFONES")
public class Telefone {

    @Coluna(nome = "ID_CLNT", tipoDado = TipoDeDado.NUMBER, tamanho = 5, nulo = false)
    @ChaveEstrangeira(referenciaTabela = "CLIENTES", nomeCampo = "ID")
    private long id_cliente;

    @Coluna(nome = "TELEFONE", tamanho = 18)
    private String telefone;

    public long getId_cliente() {
        return this.id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
