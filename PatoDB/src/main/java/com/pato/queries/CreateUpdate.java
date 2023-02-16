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
public class CreateUpdate extends AbstractQuery{
    private static final Logger log = Logger.getLogger(CreateUpdate.class.getName());

    public CreateUpdate(Object objTable, Connection conn) {
        super(objTable, conn);
    }

    
    public int update() throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "CreateUpdate ->> Entrando no metodo update()");
        String query = createQuery.createUpdate();
        
        
        log.log(Level.INFO, "=====================================QUERY CRIADA========================================");
        log.log(Level.INFO, "Query: {0}", new Object[]{!query.isEmpty()? query : "QUERY ESTÁ VAZIA!"});
        log.log(Level.INFO, "=========================================================================================");
        
        
        try(Statement stmt = this.connection.createStatement()){
            log.log(Level.INFO, "Criando statement, {0}", stmt);
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Não foi possivel criar o statement \n\t\tErro: {0}", ex);
        }
        return 0;
    }
}
