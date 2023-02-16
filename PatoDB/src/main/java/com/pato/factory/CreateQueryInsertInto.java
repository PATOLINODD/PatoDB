/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.Utils.NovaString;
import com.pato.anotacoes.query.Coluna;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateQueryInsertInto extends AbstractCreateQuery{
    private static final Logger log = Logger.getLogger(CreateQueryInsertInto.class.getName());
    
    String[] valores;
    
    public CreateQueryInsertInto(StringBuilder query, Object objTable) {
        super(query, objTable.getClass());
        valores = getValores(objTable);
        System.out.println(Arrays.toString(valores));
    }
    
    @Override
    public StringBuilder getQuery()throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "CreateQueryInsertInto ->> entrando no metodo getQuery()");
        
        this.query.append("INSERT INTO ")
        .append(alias);
            this.query.append("( ");
        for(Field field : this.table.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Coluna.class)){
                this.query.append(field.getAnnotation(Coluna.class).nome()).append(", ");
            } else {
                this.query.append(field.getName()).append(", ");
            }
        }
        NovaString.removeString(query, ", ");
        query.append(" ) VALUES ( ");
        Field[] fields = this.table.getDeclaredFields();
        Class<?> queryTypes = CreateQueryTypes.class;
        for(int i = 0; i < fields.length; i++){
            try {
                Method m = queryTypes.getDeclaredMethod("get"+NovaString.getNome(fields[i].getType().getSimpleName()), String.class);
                m.setAccessible(true);
                query.append("'");
                query.append(m.invoke(queryTypes.newInstance(), valores[i]));
                query.append("'");
            } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
                log.log(Level.SEVERE, "Erro ao tentar criar query: \n\t\tErro: ", ex);
            }
            query.append(", ");
        }
        NovaString.removeString(query, ", ");
        query.append(" )");
        return this.query;
    }
    
}
