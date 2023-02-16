/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.Utils.NovaString;
import com.pato.anotacoes.query.Coluna;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateQueryUpdate extends AbstractCreateQuery{
    private static final Logger log = Logger.getLogger(CreateQueryUpdate.class.getName());
    String[] valores;
    
    public CreateQueryUpdate(StringBuilder query, Object objTable) {
        super(query, objTable.getClass());
        this.valores = getValores(objTable);
    }

    @Override
    public StringBuilder getQuery() throws InstantiationException, IllegalAccessException {
        log.log(Level.INFO, "CreateQueryUpdate ->> Entrando no metodo getQuery()");
        
        
        this.query.append("UPDATE ")
                .append(alias.concat(" "))
                .append("SET ");
        Field[] fields = table.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            createUpdate(this.query, fields[i], valores[i]);
        }
        NovaString.removeString(query, ", ");
        return this.query;
    }
    
    private void createUpdate(StringBuilder query, Field field, String valor){
        if(field.isAnnotationPresent(Coluna.class)){
            query.append(field.getAnnotation(Coluna.class).nome()).append(" = ");
        } else {
            query.append(field.getName().concat(" = "));
        }
        
        query.append("'")
                .append(getLocaDateTime(valor))
                .append("'");
        query.append(", ");
    }
    
    private String getLocaDateTime(String valor){
        String retorno = "";
        LocalDateTime date = LocalDateTime.parse(valor, DateTimeFormatter.ISO_DATE_TIME);
        int dia = 0, mes = 0, ano = 0, hora = 0, minuto = 0, segundo = 0, nanoS = 0;
        if(date != null){
            dia = date.getDayOfMonth();
            mes = date.getMonthValue();
            ano = date.getYear();
            hora = date.getHour();
            minuto = date.getMinute();
            segundo = date.getSecond();
            nanoS = date.getNano();
            retorno = "".concat(String.valueOf(dia+"/"+mes+"/"+ano+" "+hora+":"+minuto+":"+segundo+","+nanoS));
            return retorno;
        }
//        dd/mm/ano 12:45:41.658
        return valor;
    }
}
