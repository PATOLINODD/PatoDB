/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author PATOLINODD
 */
public class Conexao {

    Connection conn;
    public Conexao() throws SQLException {
        ComboPooledDataSource combop = new ComboPooledDataSource();
        combop.setJdbcUrl("jdbc:oracle:thin:rcvry/rcvrywillbank@172.31.20.116:1521/ODV25");
        
        this.conn = combop.getConnection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
    
    
    
}
