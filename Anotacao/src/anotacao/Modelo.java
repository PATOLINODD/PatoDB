/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotacao;

import java.time.LocalDateTime;

/**
 *
 * @author PATOLINODD
 */
public class Modelo {
    
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    private LocalDateTime getLocalDateTime(String str){
        return LocalDateTime.now();
    }
}
