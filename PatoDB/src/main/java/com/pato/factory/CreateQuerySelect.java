/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import static com.pato.Utils.NovaString.removeString;
import com.pato.anotacoes.funcao.ABS;
import java.lang.reflect.Field;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.query.Juntar;
import com.pato.anotacoes.query.Selecionar;
import com.pato.anotacoes.query.Tabela;
import com.pato.anotacoes.query.SubConsulta;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.pato.anotacoes.funcao.CEIL;

/**
 *
 * @author PATOLINODD
 */
public class CreateQuerySelect extends AbstractCreateQuery{
    private static final Logger log = Logger.getLogger(CreateQuerySelect.class.getName());
    
    public CreateQuerySelect(StringBuilder query, Class<?> table) {
        super(query, table);
    }
    
    @Override
    public StringBuilder getQuery() throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "CreateQuerySelect ->> entrando no metodo getQuery()");
        
        
        query.append("SELECT ");
        
        for(Field field : table.getDeclaredFields()){
            //caso tenha a anotação SubConsulta
            addFuncao(query, field);
            if(field.isAnnotationPresent(SubConsulta.class)){
                //caso tenha a anotação Coluna
                //omite a criação da subquery
                if(field.isAnnotationPresent(Coluna.class)){
                    query.append(alias.concat(".").concat(field.getAnnotation(Coluna.class).nome()));
                } else {
                    //caso tenha somente a anotação SubConsulta
                    //cria a subquery
                    query.append(" ( ");
                    query.append(new CreateQuery(field).createSelect());
                    query.append(" ) ");
                }
            } else {
                //caso não tenha a anotação SubConsulta
                //mesma coisa de cima para a criação do select ...
                //caso tenha a anotação Coluna
                if(field.isAnnotationPresent(Coluna.class)){
                    //caso tenha a anotação Tabela
                    //Entra na classe e insere os campos que vao ser selecionados na query
                    if(field.isAnnotationPresent(Tabela.class)){
                        addFieldSubTabela(query, field.getType());
                    } else {
                        //caso não tenha a anotação Tabela cria o Select => TabelaPai.column
                        query.append(alias.concat(".").concat(field.getAnnotation(Coluna.class).nome()));
                    }
                } else {
                    if(field.getType().isAnnotationPresent(Tabela.class)){
                        addFieldSubTabela(query, field.getType());
                    } else {
                        query.append(alias.concat(".").concat(field.getName()));
                    }
                }
            }
            query.append(", ");
        }
        removeString(query, ",");
        createFrom(query, this.table);
        return query;
    }
    
    private void createFrom(StringBuilder query, Class<?> table) throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "CreateQuerySelect ->> Entrando no metodo createFrom(StringBuilder {0}, Class<?> {1})", new Object[]{query, table});
        
        boolean eSelecionar = false;
        query.append("FROM ");
        if(!table.isAnnotationPresent(Selecionar.class)){
            query.append(alias);
        } else {
            eSelecionar = true;
        }
        
        for(Field field : table.getDeclaredFields()){
            field.setAccessible(true);
            if(field.getType().isAnnotationPresent(Tabela.class) && !field.isAnnotationPresent(Juntar.class)){
                if(!eSelecionar){
                    query.append(", ");
                    query.append(field.getType().isAnnotationPresent(Tabela.class) ? field.getType().getAnnotation(Tabela.class).value() : field.getType().getSimpleName());
                } else {
                    query.append(field.getType().isAnnotationPresent(Tabela.class) ? field.getType().getAnnotation(Tabela.class).value() : field.getType().getSimpleName());
                    query.append(", ");
                }
            }
        }
        if(eSelecionar){
            removeString(query, ", ");
        }
    }
    
    private void addFieldSubTabela(StringBuilder query, Class<?> subTabela) throws InstantiationException, IllegalAccessException{
        log.log(Level.INFO, "Entrando no metodo addFieldSubTabela(StringBuilder {0}, Class<?> {1}", new Object[]{query, subTabela});
        //pego o nome da classe
        String nAlias = subTabela.isAnnotationPresent(Tabela.class)? subTabela.getAnnotation(Tabela.class).value() : subTabela.getSimpleName();
        //itero sobre todos os campos/atributos dentro da classe
        for(Field field : subTabela.getDeclaredFields()){
                //caso tenha a anotação SubConsulta
            if(field.isAnnotationPresent(SubConsulta.class)){
                //caso tenha a anotação Coluna
                //omite a criação da subquery
                if(field.isAnnotationPresent(Coluna.class)){
                    query.append(nAlias.concat(".").concat(field.getAnnotation(Coluna.class).nome()));
                } else {
                    //caso tenha somente a anotação SubConsulta
                    //cria a subquery
                    query.append(" ( ");
                    query.append(new CreateQuery(field).createSelect());
                    query.append(" ) ");
                }
            } else {
                //caso não tenha a anotação SubConsulta
                //mesma coisa de cima para a criação do select ...
                //caso tenha a anotação Coluna
                if(field.isAnnotationPresent(Coluna.class)){
                    //caso tenha a anotação Tabela
                    //Entra na classe e insere os campos que vao ser selecionados na query
                    if(field.isAnnotationPresent(Tabela.class)){
                        addFieldSubTabela(query, field.getType());
                    } else {
                        //caso não tenha a anotação Tabela cria o Select => TabelaPai.column
                        query.append(nAlias.concat(".").concat(field.getAnnotation(Coluna.class).nome()));
                    }
                } else {
                    if(field.getType().isAnnotationPresent(Tabela.class)){
                        addFieldSubTabela(query, field.getType());
                    } else {
                        query.append(nAlias.concat(".").concat(field.getName()));
                    }
                }
            }
            query.append(", ");
        }
        removeString(query, ",");
    }
    
    private void addFuncao(StringBuilder query, Field field){
        if(field.isAnnotationPresent(CEIL.class)){
            query.append(" LENGTH (").append(getAlias(field)).append(")");
        }
        if(field.isAnnotationPresent(ABS.class)){}
    }
    
    private String getAlias(Class<?> fieldTable){
        return fieldTable.isAnnotationPresent(Tabela.class) ? fieldTable.getAnnotation(Tabela.class).value() : fieldTable.getSimpleName();
    }
    private String getAlias(Field field){
        return field.isAnnotationPresent(Coluna.class) ? field.getAnnotation(Coluna.class).nome() : field.getName();
    }
    
}
