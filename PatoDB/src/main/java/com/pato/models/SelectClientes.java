/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.models;

import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Juntar;
import com.pato.anotacoes.query.Tabela;
import com.pato.anotacoes.funcao.CEIL;
import com.pato.anotacoes.query.Onde;

/**
 *
 * @author PATOLINODD
 */
@Tabela("CLIENTES")
public class SelectClientes {
    
    @CEIL
    @Coluna(nome = "nome")
    String nome;
    
    @Juntar(chavePrimaria = "ID",chaveEstrangeira = "ID_CLNT")
    Telefone telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "SelectClientes{" + "nome=" + nome + ", telefone=" + telefone + '}';
    }
    
}
