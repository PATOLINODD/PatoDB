/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.anotacoes.query.Tabela;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public abstract class AbstractCreateQuery {
    private static final Logger log = Logger.getLogger(AbstractCreateQuery.class.getName());
    
    protected StringBuilder query;
    protected Class<?> table;
    protected String alias;
    protected Object objTable;
    
    public AbstractCreateQuery (StringBuilder query, Class<?> table){
        this.query = query;
        this.table = table;
        this.alias = table.isAnnotationPresent(Tabela.class) ? table.getAnnotation(Tabela.class).value() : table.getSimpleName();
    }
    
    public StringBuilder getQuery()throws InstantiationException, IllegalAccessException{
        return this.query;
    }
    
    public Object where(Object obj){
        log.log(Level.INFO, "AbstractCreateQuery ->> entrando no metodo where(Object {0})", new Object[]{obj});
        
        Class<?> classTable = obj.getClass();
        for(Field field : classTable.getDeclaredFields()){
            System.out.println("estou iterando os fields");
        }
        return this;
    }
    
    protected String[] getValores(Object obj){
        System.out.println(obj);
        String[] str = obj.toString().split("[{]")[1].split("[,]");
        String[] retorno = new String[str.length];
        for(int i = 0; i < str.length; i++){
            if(str[i].contains("}")){
                retorno[i] = str[i].replace("}", "").split("=")[1];
            } else {
                retorno[i] = str[i].split("=")[1];
            }
        }
        return retorno;
    }
}
