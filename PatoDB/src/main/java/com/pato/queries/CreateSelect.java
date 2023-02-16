/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.queries;

import com.pato.Utils.NovaString;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateSelect extends AbstractQuery{
    private static final Logger log = Logger.getLogger(CreateSelect.class.getName());
    
    
    List<Object> objs = new ArrayList<>();
    
    public CreateSelect(Object tableObj,  Connection conn){
        super(tableObj, conn);
    }
    
    public CreateSelect select() throws SQLException{
        log.log(Level.INFO, "CreateSelect ->> Entrando no metodo Select.select");
        try{
            String query = createQuery.createSelect();
            
            log.log(Level.INFO, "=====================================QUERY CRIADA========================================");
            log.log(Level.INFO, "Query: {0}", new Object[]{!query.isEmpty()? query : "QUERY ESTÁ VAZIA!"});
            log.log(Level.INFO, "=========================================================================================");
            
            
            executaQuery(this.tableClass, query);
            
        }catch(SQLException ex){
            log.log(Level.SEVERE, "Não foi possivel fazer a consulta. \n\t\tErro: {0}", ex);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            log.log(Level.SEVERE, "Erro: {0}", ex);
        }
        return this;
    }
     
    private void executaQuery(Class<?> table, String query) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException{
        log.log(Level.INFO, "Entrando no metodo Select.executaQuery(Class<?> table: {0}, String query: {1})", new Object[]{table, query});
        try(Statement stmt = this.connection.createStatement()){
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            int stmtField = 1;
            
            while(rs.next()){
                this.objTable = table.newInstance();
                stmtField = 1;
                for(Field field : table.getDeclaredFields()){
                    field.setAccessible(true);
                    if(field.getType().getName().contains("java.lang") ){
                        setField(table, objTable, field, rs, stmtField++);
                    } else if(field.getType().getName().matches("\\w+")){
                        setField(table, objTable, field, rs, stmtField++);
                    } else {
                        setValuesSubquery(objTable, field, rs, stmtField);
                    }
                }
                objs.add(objTable);
            }
        } catch(Exception ex){
            log.log(Level.SEVERE, "\n\t\tErro dentro do metodo Select.executaQuery(Class<?> table: {0}, String query: {1})", new Object[]{table, query});
            log.log(Level.SEVERE, "\n\t\tNão foi possivel fazer o statement: {0}", ex);
        }
    }
    private void setValuesSubquery(Object table, Field fieldAtual, ResultSet rs, int stmtField) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException{
        log.log(Level.INFO, "Entrando no metodo Select.setValuesSubquery(Object table: {0}, Field field: {1}, ResultSet rs: {2}, int StmtField: {3})", new Object[]{table, fieldAtual, rs, stmtField});
        log.log(Level.INFO, "Iniciando tratamento com a subquery");
        System.out.println(fieldAtual.getGenericType().getTypeName());
        
        
        Class<?> fieldClass = fieldAtual.getType();// fieldAtual -->>> Classe dentro de outra Classe
        Object nObj = fieldClass.newInstance();
        
        for(Field field : fieldClass.getDeclaredFields()){
            field.setAccessible(true);
            
            if(field.getType().getName().contains("java.lang")){
                setField(fieldClass, nObj, field, rs, stmtField++);
            } else if(field.getType().getName().matches("\\w+")){
                setField(fieldClass, nObj, field, rs, stmtField++);
            } else {
                setValuesSubquery(nObj, field, rs, stmtField);
            }
        }
        fieldAtual.set(objTable, nObj);
    }
    
    public List<Object> getResultList(){
        log.log(Level.INFO, "Resultado da consulta: {0}", new Object[]{objs});
        log.log(Level.INFO, "Quantidade de linhas retornadas ->> {0}", objs.size());
        return objs;
    }
    
    public Object getSingleResult(){
        try{
            if(objs.size() > 1){
                throw new Exception("Tem mais de um resultado na consulta");
            } else {
                log.log(Level.INFO, "Resultado da consulta: {0}", new Object[]{objTable});
                return objTable;
            }
        }catch(Exception ex){
            log.log(Level.WARNING, "Erro ao tentar pegar resultado. \n\t\tResultado: {0}\n\t\tErro: {1}", new Object[]{objs, ex});
        }
        return null;
    }
    
    private void setField(Class<?> table, Object obj, Field fieldAtual, ResultSet rs, int stmtField) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException{
        log.log(Level.INFO, "Entrando no metodo setField(Class<?> {0}, Object {1}, fieldAtual {2}, ResultSet {3}, int {4})", new Object[]{table, obj, fieldAtual, rs, stmtField});
        
        switch(fieldAtual.getType().getSimpleName()){
            case "String":
//                setFieldValue(table, obj, fieldAtual, rs, stmtField);
                fieldAtual.set(obj, rs.getString(stmtField));
                break;
            case "int":
//                setFieldValue(table, obj, fieldAtual, rs, stmtField);
                fieldAtual.set(obj, rs.getInt(stmtField));
                break;
            case "Integer":
//                fieldAtual.set(obj, rs.getInt(stmtField));
                setFieldValue(table, obj, fieldAtual, rs, stmtField);
                break;
            case "byte":
                fieldAtual.set(obj, rs.getByte(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "short":
                fieldAtual.set(obj, rs.getShort(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "float":
                fieldAtual.set(obj, rs.getFloat(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "long":
                fieldAtual.set(obj, rs.getLong(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "double":
                fieldAtual.set(obj, rs.getDouble(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "Date":
                fieldAtual.set(obj, rs.getDate(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "LocalDate":
                fieldAtual.set(obj, rs.getDate(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
            case "LocalDateTime":
                fieldAtual.set(obj, rs.getDate(stmtField));
//                setField(table, obj, fieldAtual, rs, stmtField);
                break;
        }
    }
    
    private void setFieldValue(Class<?> table, Object obj, Field field, ResultSet rs, int stmtField) throws NoSuchMethodException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        log.log(Level.INFO, "Entrando no metodo setField(Class<?> {0}, Object {1}, Field {2}, ResultSet {3}, int {4})", new Object[]{table, obj, field, rs, stmtField});
        
        Method m = table.getDeclaredMethod("set"+NovaString.getNome(field.getName()), field.getType());
        m.setAccessible(true);
        m.invoke(obj, rs.getInt(stmtField));
    }
    private LocalDateTime getLocalDateTime(String date){
        return LocalDateTime.parse(date.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);
    }
}
