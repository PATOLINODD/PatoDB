/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.models;

import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Tabela;
import com.pato.enuns.TipoDeDado;

/**
 *
 * @author PATOLINODD
 */
@Tabela(value = "TELEFONES")
public class Telefone {
    
//    @Coluna(nome = "ID_CLNT", tipoDado = TipoDeDado.NUMBER, tamanho = 5, nulo = false)
//    @ChaveEstrangeira(referenciaTabela = "CLIENTES", nomeCampo = "ID")
//    private int id_cliente;
    
    @Coluna(nome = "TELEFONE")
    private String telefone;

//    public long getId_cliente() {
//        return this.id_cliente;
//    }
//
//    public void setId_cliente(int id_cliente) {
//        this.id_cliente = id_cliente;
//    }

    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Telefone{" + "telefone=" + telefone + '}';
    }

}
