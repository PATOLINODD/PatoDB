/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotacao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class Anotacao {

    Object[] fieldN = {"LocalDateTime"};
    Object[] field = {LocalDateTime.class};
    public Anotacao(){
        String num = "1";
        int n = 1;
        System.out.println(num + n);
    }
    public static void main(String[] args) {
        new Anotacao();
        System.out.println(LocalDateTime.now());
    }
    
}
