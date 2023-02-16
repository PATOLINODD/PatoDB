/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateDelete extends AbstractQuery{
    private static final Logger log = Logger.getLogger(CreateDelete.class.getName());
    
    public CreateDelete(Object objTable, Connection conn) {
        super(objTable, conn);
    }
    
    public int delete() throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "Entrando no metodo getQuery()");
        
        String query = this.createQuery.createDelete();
        
        log.log(Level.INFO, "=====================================QUERY CRIADA========================================");
        log.log(Level.INFO, "Query: {0}", new Object[]{!query.isEmpty()? query : "QUERY ESTÁ VAZIA!"});
        log.log(Level.INFO, "=========================================================================================");
        
        
        try(Statement stmt = this.connection.createStatement()){
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Erro ao tentar pegar Statement, \n\t\tErro: {0}", ex);
        }
        return 0;
    }
}
