/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.models;

import com.google.gson.Gson;
import com.pato.anotacoes.query.Tabela;

/**
 *
 * @author PATRICK
 */
public class ClientesETelefones {
    
    @Tabela
    Cliente clientes;
    
    @Tabela
    Telefone telfones;

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public Telefone getTelfones() {
        return telfones;
    }

    public void setTelfones(Telefone telfones) {
        this.telfones = telfones;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
