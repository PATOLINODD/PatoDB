/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.Utils;

import com.mchange.v2.lang.StringUtils;

/**
 *
 * @author PATOLINODD
 */
public class NovaString {
    
    public static void removeString(StringBuilder query, String str){
        query.replace(query.lastIndexOf(str), query.lastIndexOf(str)+str.length(), "");
    }
    public static String getNome(String field){
        String firstLetter = String.valueOf(field.charAt(0));
        field = field.replaceFirst(firstLetter, firstLetter.toUpperCase());
        return field;
    }
    
    public static String limpaAspasSimples(String str){
        return str.replaceAll("'", "");
    }
    
}
