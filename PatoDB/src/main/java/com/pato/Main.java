/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato;

import com.pato.models.Cliente;
import com.pato.models.SelectClientes;
import com.pato.models.Telefone;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class Main {
    
    public Main(){
        try(Connection conn = new Conexao().getConn()){
            Consulta query = new Consulta(conn);
            
            Cliente patrick = new Cliente();
            
            patrick.setId(2);
            patrick.setNome("BARBARA B OLIVEIRA");
            
            Telefone telefone = new Telefone();
//            telefone.setId_cliente((int) patrick.getId());
//            telefone.setTelefone("+5511912346547");
//            System.out.println(query.insertInto(patrick));
//            System.out.println(query.insertInto(telefone));
            

            SelectClientes CLIENTE = new SelectClientes();
            List<Object> resultList = query.select(CLIENTE).getResultList();
            
            resultList.forEach(System.out::println);
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args){
        new Main();
    }
}
