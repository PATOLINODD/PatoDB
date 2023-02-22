/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato;

import com.pato.enuns.Ignore;
import com.pato.models.Cliente;
import com.pato.models.Termo;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class Main {
    
    public Main(){
//        try(Connection conn = new Conexao().getConn()){
//            Consulta query = new Consulta(conn);
            Consulta query = new Consulta();
            
            Cliente patrick = new Cliente();
            
            patrick.setId(2);
            patrick.setNome("'BARBARA' B OLIVEIRA");
            Termo termo = new Termo();
            
            termo.setCliente(patrick);
            termo.setDataHoje(LocalDateTime.now());
            termo.setFielIgnorado("ignorado!");//just to show that attribute's name and value exist.
            termo.setInteiro(45);
            
            query.insertInto(termo);
            
            
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    public static void main(String[] args){
        new Main();
    }
}
