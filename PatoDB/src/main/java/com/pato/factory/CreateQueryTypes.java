/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PATOLINODD
 */
public class CreateQueryTypes {
    
    protected Object getInt(String value){
        return value;
    }
    
    protected Object getString(String value){
        return value;
    }
    
    protected Object getShort(String value){
        return value;
    }
    protected Object getFloat(String value){
        return value;
    }
    protected Object getByte(String value){
        return value;
    }
    protected Object getDouble(String value){
        return value;
    }
    protected Object getChar(String value){
        return value;
    }
    protected Object getLong(String value){
        return value;
    }
    protected Object getLocalDateTime(String value){
//        dd/mm/yy hh:mm:ss,000000000
        LocalDateTime dateTime = LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
        if(dateTime != null){
            String retorno = "'"
                .concat(String.valueOf(dateTime.getDayOfMonth())).concat("/")
                .concat(String.valueOf(dateTime.getMonthValue())).concat("/")
                .concat(String.valueOf(dateTime.getYear()).concat(" "))
                .concat(String.valueOf(dateTime.getHour())).concat(":")
                .concat(String.valueOf(dateTime.getMinute())).concat(":")
                .concat(String.valueOf(dateTime.getSecond())).concat(",")
                .concat(String.valueOf(dateTime.getNano())).concat("'");
            return retorno;
        }
        return "";
    }
    protected Object getLocalDate(String value){
        LocalDate data = LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
        if(data != null){
            String retorno = "'".concat(String.valueOf(data.getDayOfMonth()+"/"
                    .concat(String.valueOf(data.getMonthValue())).concat("/").concat(String.valueOf(data.getYear()))
            )).concat("'");
            return retorno;
        }
        return "";
    }
}
