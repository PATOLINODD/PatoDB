/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.anotacoes.query.ChaveEstrangeira;
import com.pato.anotacoes.query.ChavePrimaria;
import com.pato.anotacoes.query.Coluna;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateQueryCreate extends AbstractCreateQuery{
    private static final Logger log = Logger.getLogger(CreateQueryCreate.class.getName());
    
    public CreateQueryCreate(StringBuilder query, Class<?> classTable) {
        super(query, classTable);
    }

    @Override
    public StringBuilder getQuery() throws InstantiationException, IllegalAccessException {
        log.log(Level.INFO, "CreateQueryCreate ->> entrando no metodo getQuery()");
        System.out.println(this.table);
        
        String chavePrimaria = "", chaveEstrangeira = "";
        boolean PK = false, FK = false;
        
        this.query.append("CREATE TABLE ")
        .append(alias)
        .append(" (");
        for(Field field : table.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Coluna.class)){
                Coluna coluna = field.getAnnotation(Coluna.class);
                query.append(" ").append(coluna.nome());
                query.append(" ");
                switch(coluna.tipoDado()){
                    case VARCHAR2:
                        query.append(" VARCHAR2");
                        if(coluna.tamanho() > 0){
                            query.append("(")
                            .append(coluna.tamanho())
                            .append(")");
                        }
                        break;
                    case VARCHAR:
                        query.append(" VARCHAR");
                        if(coluna.tamanho() > 0){
                            query.append("(")
                            .append(coluna.tamanho())
                            .append(")");
                        }
                        break;
                    case CHAR:
                        query.append(" CHAR");
                        if(coluna.tamanho() > 0){
                            query.append("(")
                            .append(coluna.tamanho())
                            .append(")");
                        }
                        break;
                    case NVARCHAR:
                        query.append(" NVARCHAR");
                        if(coluna.tamanho() > 0){
                            query.append("(")
                            .append(coluna.tamanho())
                            .append(")");
                        }
                        break;
                    case NCHAR:
                        query.append(" NCHAR");
                        if(coluna.tamanho() > 0){
                            query.append("(")
                            .append(coluna.tamanho())
                            .append(")");
                        }
                        break;
                    case NUMBER:
                        query.append(" NUMBER");
                        if(coluna.tamanho() > 0){
                            query.append("(")
                            .append(coluna.tamanho())
                            .append(")");
                        }
                        if(field.isAnnotationPresent(ChavePrimaria.class)){
                            PK = true;
                            chavePrimaria = coluna.nome();
                            if(field.getAnnotation(ChavePrimaria.class).gerar().equals(field.getAnnotation(ChavePrimaria.class).gerar().GENERATED_BY_DEFAULT_AS_IDENTITY)){
                                query.append(field.getAnnotation(ChavePrimaria.class).gerar().GENERATED_BY_DEFAULT_AS_IDENTITY);
                            }
                        } else if(field.isAnnotationPresent(ChaveEstrangeira.class)){
                            FK = true;
                            chaveEstrangeira = "FOREIGN KEY(".concat(coluna.nome()).concat(") ")
                                    .concat("REFERENCES ")
                                    .concat(field.getAnnotation(ChaveEstrangeira.class).referenciaTabela()).concat("( ")
                                    .concat(field.getAnnotation(ChaveEstrangeira.class).nomeCampo()).concat(")");
                        }
                        break;
                    case INTEGER:
                        query.append(" INTEGER");
                        if(field.isAnnotationPresent(ChavePrimaria.class)){
                            PK = true;
                            chavePrimaria = field.getAnnotation(Coluna.class).nome();
                            if(field.getAnnotation(ChavePrimaria.class).gerar().equals(field.getAnnotation(ChavePrimaria.class).gerar().GENERATED_BY_DEFAULT_AS_IDENTITY)){
                                query.append(field.getAnnotation(ChavePrimaria.class).gerar().GENERATED_BY_DEFAULT_AS_IDENTITY);
                            }
                        } else if(field.isAnnotationPresent(ChaveEstrangeira.class)){
                            FK = true;
                        }
                        break;
                    case TIMESTAMP:
                        query.append(" TIMESTAMP");
                        break;
                }
                if(!coluna.nulo()){
                    query.append(" ")
                    .append(" NOT NULL")
                    .append(", ");
                } else {
                    query.append(", ");
                }
            } else {
                throw new RuntimeException("É necessario definir as regras da coluna.");
            }
        }
        if(PK){
            query.append(" PRIMARY KEY(").append(chavePrimaria).append(")");
        }
        if(FK){
            query.append(chaveEstrangeira);
        }
        query.append(" )");
        return super.getQuery(); 
    }
    
    
}
