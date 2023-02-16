/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.anotacoes.query;

import com.pato.enuns.Gerar;
import com.pato.enuns.TipoDeDado;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author PATOLINODD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Coluna {
    String nome();
    TipoDeDado tipoDado() default TipoDeDado.VARCHAR2;
    double tamanho() default 0;
    boolean nulo() default true;
}
