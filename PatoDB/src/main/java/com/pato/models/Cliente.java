/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.models;

import com.pato.anotacoes.query.ChavePrimaria;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Juntar;
import com.pato.anotacoes.query.Tabela;
import com.pato.enuns.TipoDeDado;

/**
 *
 * @author PATOLINODD
 */
@Tabela(value = "Clientes")
public class Cliente {
    
    @Coluna(nome = "ID", nulo = false, tipoDado = TipoDeDado.NUMBER)
    @ChavePrimaria
    private long id;
    
    @Coluna(nome = "NOME", nulo = false, tamanho = 50)
    private String nome;
    
//    @Juntar(chavePrimaria = "ID", chaveEstrangeira = "ID_CLNT")
//    Telefone telefones;
//
//    public Telefone getTelefones() {
//        return telefones;
//    }
//
//    public void setTelefones(Telefone telefones) {
//        this.telefones = telefones;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + '}';
    }


}
