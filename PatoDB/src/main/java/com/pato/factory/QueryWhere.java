/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Onde;
import com.pato.anotacoes.query.Tabela;
import com.pato.anotacoes.query.SubConsulta;

/**
 *
 * @author PATOLINODD
 */
public class QueryWhere {
    private static final Logger log = Logger.getLogger(QueryWhere.class.getName());
    
    String alias;
    Class<?> table;
    StringBuilder query;
    
    public QueryWhere(Class<?> table){
        this.table=table;
        alias = table.getSimpleName();
    }
    
    public QueryWhere where(StringBuilder query) throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "QueryWhere ->> Entrando no metodo where(StringBuilder query: {0})", new Object[]{query});
        
        this.query = query;
        this.query.append("\nWHERE ");
        for(Field field : table.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Onde.class)){
                createWhere(this.query, table.getSimpleName(), field);
            } else {
                if(field.getType().isAnnotationPresent(Tabela.class)){
                    for(Field fieldSubTabela : field.getType().getDeclaredFields()){
                        createWhere(this.query, field.getType().getSimpleName(), fieldSubTabela);
                    }
                }
            }
        }
        removeString(this.query, "AND");
        return this;
    }
      
    private String createWhere(StringBuilder query, String alias, Field field) throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "QueryWhere ->> Entrando no metodo createWhere(StringBuilder {0}, String {1}, Field {2})", new Object[]{query, alias, field});
        
        if(field.isAnnotationPresent(Onde.class)){
            if(field.isAnnotationPresent(Coluna.class)){
                this.query.append(alias.concat(".").concat(field.getAnnotation(Coluna.class).nome()).concat(" = "));
            } else {
                this.query.append(alias.concat(".").concat(field.getName()).concat(" = "));
            }
            //criação da subquery
            if(field.isAnnotationPresent(SubConsulta.class)){
                    query.append(" ( ");
                    query.append(new CreateQuery(field.getType()).createSelect());
                    query.append(" ) ");
            } else {
                String value = field.getAnnotation(Onde.class).value();
                this.query.append(value);
            }
            this.query.append(" AND ");
        }
        return query.toString();
    }
    
    private void removeString(StringBuilder query, String str){
        query.replace(query.lastIndexOf(str), query.lastIndexOf(str)+str.length(), "");
    }
    
    public StringBuilder getQuery(){
        log.log(Level.INFO, "Retornando resultado da query: {0}", new Object[]{this.query});
        return this.query;
    }
}
