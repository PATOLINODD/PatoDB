/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.Utils;

/**
 *
 * @author PATOLINODD
 */
public class NovaString {
    
    public static void removeString(StringBuilder query, String str){
        query.replace(query.lastIndexOf(str), query.lastIndexOf(str)+str.length(), "");
    }
    public static String getNome(String field){
        String result = "";
        String []str = field.split("");
        for(int i = 0; i< str.length; i++){
            if(i == 0){
                result += str[i].toUpperCase();
            }else{
                result += str[i];
            }
        }
        return result;
    }
    
}
