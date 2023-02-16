/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.queries;

import com.pato.factory.CreateQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateTable extends AbstractQuery{
    private static final Logger log = Logger.getLogger(CreateTable.class.getName());

    
    public CreateTable(Class<?> classTable, Connection conn) {
        super(null, conn);
        this.tableClass = classTable;
    }
    
    
    public boolean criar() throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "CreateTable ->> entrando no metodo criar");
        
        String query = new CreateQuery(tableClass, Boolean.TRUE).createCriar();
        
        
        log.log(Level.INFO, "=====================================QUERY CRIADA========================================");
        log.log(Level.INFO, "Query: {0}", new Object[]{!query.isEmpty()? query : "QUERY ESTÁ VAZIA!"});
        log.log(Level.INFO, "=========================================================================================");
        
        
        boolean retorno = false;
        try(Statement stmt = this.connection.createStatement()){
        
            retorno = stmt.execute(query);
            
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Erro ao tentar fazer o Statement: \n\t\tErro: {0}", ex);
        }
        return retorno;
    }
}
