/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.anotacoes.query.ChaveEstrangeira;
import com.pato.anotacoes.query.ChavePrimaria;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Juntar;
import com.pato.anotacoes.query.Tabela;
import java.lang.reflect.Field;

/**
 *
 * @author PATOLINODD
 */
public class QueryJoin {
    
    Class<?> table;
    StringBuilder query;
    
    public QueryJoin(Class<?> table){
        this.table = table;
    }
    public QueryJoin join(StringBuilder query){
        
        
        for(Field field : table.getDeclaredFields()){
            if(field.isAnnotationPresent(Juntar.class)){
                Juntar join = field.getAnnotation(Juntar.class);
                query.append(" JOIN ").append(getAlias(field.getType()));
                query.append(" ON ").append(getAlias(table).concat(".").concat(join.chavePrimaria())).append(" = ");
                query.append(getAlias(field.getType()).concat(".")).append(join.chaveEstrangeira());
            }
            
        }
        this.query = query;
        return this;
    }
    
    public StringBuilder getQuery(){
        return this.query;
    }
    private String getChavePrimariaJoinTable(Class<?> table){
        String retorno = getAlias(table);
        for(Field field : table.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(ChaveEstrangeira.class)){
                retorno += ".".concat(getAlias(field));
            }
        }
        return retorno;
    }
    private String getAlias(Class<?> fieldTable){
        return fieldTable.isAnnotationPresent(Tabela.class) ? fieldTable.getAnnotation(Tabela.class).value() : fieldTable.getSimpleName();
    }
    private String getAlias(Field field){
        return field.isAnnotationPresent(Coluna.class) ? field.getAnnotation(Coluna.class).nome() : field.getName();
    }
    
}
