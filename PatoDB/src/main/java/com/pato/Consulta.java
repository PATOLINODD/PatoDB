/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato;

import com.pato.enuns.Ignore;
import com.pato.queries.CreateDelete;
import com.pato.queries.CreateInsertInto;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.pato.queries.CreateSelect;
import com.pato.queries.CreateTable;
import com.pato.queries.CreateTruncate;
import com.pato.queries.CreateUpdate;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author PATOLINODD
 */
public class Consulta {
    private static final Logger log = Logger.getLogger(Consulta.class.getName());
    private Connection conn = null;
    
    
    private final Class<?> table = null;
    private Object obj;
    private List<Object> objs = new ArrayList<>();
    
    public Consulta(Connection connection){
        log.log(Level.INFO, "Iniciando conexão com a base de dados");
        this.conn = connection;
    }

    public Consulta() {
    }
    
    public CreateSelect select(Object obj){
        try {
            return new CreateSelect(obj, this.conn).select();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Erro ao tentar consultar: \n\t\tErro: {0}", ex);
        }
        return null;
    }
    
    public int update(Object obj){
        try {
            return new CreateUpdate(obj, this.conn).update();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, "Erro ao tentar atualizar: \n\t\tErro: {0}", ex);
        }
        return 0;
    }
    
    public boolean create(Class<?> classObj){
        try {
            return new CreateTable(classObj, conn).criar();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, "Erro ao tentar criar tabela: \n\t\tErro: {0}", ex);
        }
        return false;
    }
    
    public int insertInto(Object obj){
        try {
            return new CreateInsertInto(obj, conn).inserir();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, "Erro ao tentar inserir: \n\t\tErro: {0}", ex);
        }
        return 0;
    }
    
    public int delete(Object obj){
        try {
            return new CreateDelete(obj, this.conn).delete();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, "Erro ao tentar deletar: \n\t\tErro: {0}", ex);
        }
        return 0;
    }
    
    public boolean truncate(Object obj){
        try {
            return new CreateTruncate(obj, this.conn).truncate();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, "Erro ao tentar truncar: \n\t\tErro: {0}", ex);
        }
        return false;
    }

}
