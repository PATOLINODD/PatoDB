/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateQueryDelete extends AbstractCreateQuery {
    private static final Logger log = Logger.getLogger(CreateQueryDelete.class.getName());
    
    public CreateQueryDelete(StringBuilder query, Object objTable) {
        super(query, objTable.getClass());
    }

    @Override
    public StringBuilder getQuery() throws InstantiationException, IllegalAccessException {
        log.log(Level.INFO, "CreateQueryDelete ->> Entrando no metodo getQuery()");
        
        this.query.append("DELETE FROM ")
                .append(alias);
        return this.query;
    }
    
    
    
}
