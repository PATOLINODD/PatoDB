/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.queries;

import com.pato.factory.CreateQuery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PATOLINODD
 */
public abstract class AbstractQuery {
    
    protected Connection connection;
    protected Object objTable;
    protected Class<?> tableClass;
    protected CreateQuery createQuery;
    
    public AbstractQuery(Object objTable, Connection conn){
        this.objTable = objTable != null ? objTable : "";
        this.connection = conn;
        this.tableClass = objTable != null ? objTable.getClass() : String.class;
        this.createQuery = objTable != null ? new CreateQuery(objTable) : null;
    }
    
    Integer getInt(ResultSet rs, int field) throws SQLException{
        int value = rs.getInt(field);
        if(rs.wasNull()){
            return 0;
        }
        return value;
    }
}
