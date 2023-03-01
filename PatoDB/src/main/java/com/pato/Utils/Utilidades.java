/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.Utils;

import com.pato.anotacoes.query.ClassColunas;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.util.Ignore;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick.oliveira
 */
public class Utilidades {

    private static Logger log = Logger.getLogger(Utilidades.class.getName());

    /**
     *
     * @param table
     * @return List referente as colunas da tabela
     */
    public static List<String> getColumns(Class<?> table) {
        log.log(Level.INFO, "Entering in method getColumns(Class<?> table: {0})", table);
        List<String> columns = new ArrayList<>();

        Arrays.asList(table.getDeclaredFields()).forEach(currentField -> {
            if (currentField.isAnnotationPresent(ClassColunas.class)) {
                List<String> classColunas = getColumns(currentField.getType());
                classColunas.forEach(columns::add);
            } else if (currentField.isAnnotationPresent(Coluna.class)) {
                columns.add(currentField.getAnnotation(Coluna.class).nome());
            } else if (!currentField.isAnnotationPresent(Ignore.class)) {
                columns.add(currentField.getName());
            }
        });

        return columns;
    }

    /**
     * Method to get values in the java class. If not find methods getters and
     * setters this throws an exception
     *
     * @param query
     * @param table
     * @param field
     * @param obj
     */
    public static void getValuesFields(StringBuilder query, Class<?> table, Field field, Object obj) {
        log.log(Level.INFO, "Entering in method getValuesFields(Class<?> table: {0}, Field field: {1}, Object obj: {2})", new Object[]{table, field, obj});

        Method m = null;
        Object objReturn = null;
        try {
            m = table.getDeclaredMethod("get".concat(NovaString.getNome(field.getName())));
            m.setAccessible(true);
        } catch (NoSuchMethodException | SecurityException ex) {
            log.log(Level.INFO, "Error in method getValuesFields -->> methods getters and setters aren't find to field: " + field.getName(), ex);
        }
        try {
            objReturn = m.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            log.log(Level.INFO, "Error in method getValuesFields(Class<?> table, Field field, Object obj)", ex);
        }
        getObjectType(query, objReturn);
    }

    private static void getObjectType(StringBuilder query, Object obj) {
        String fieldName = obj.getClass().getSimpleName();
        switch (fieldName.toLowerCase()) {
            case "string":
                query.append("'").append(NovaString.limpaAspasSimples(String.valueOf(obj))).append("'").append(", ");
                break;
            case "char":
                query.append("'").append(obj).append("'").append(", ");
                break;
            case "localdate":
                query.append("TO_CHAR('").append(obj).append("'), ");
                break;
            case "localdatetime":
                query.append("TO_CHAR('").append(obj).append("'), ");
                break;
            case "date":
                query.append("TO_CHAR('").append(obj).append("'), ");
                break;
            case "datetime":
                query.append("TO_CHAR('").append(obj).append("'), ");
                break;
            case "byte":
                query.append(obj).append(", ");
                break;
            case "double":
                query.append(obj).append(", ");
                break;
            case "int":
                query.append(obj).append(", ");
                break;
            case "integer":
                query.append(obj).append(", ");
                break;
            case "boolean":
                query.append(obj).append(", ");
                break;
            default:
                query.append(obj).append(", ");
                break;
        }
    }

    /**
     *
     * @param query
     * @param table
     * @param field
     * @param obj
     */
    public static void getValuesClassColumns(StringBuilder query, Class<?> table, Field field, Object obj) {
        log.log(Level.INFO, "Entering in method getValuesClassColumns(Class<?> table: {0}, Field field: {1}, Object obj: {2})", new Object[]{table, field, obj});
        Method m = null;
        Object objClass = null;
        try {
            m = table.getDeclaredMethod("get".concat(NovaString.getNome(field.getName())));
            m.setAccessible(true);
        } catch (NoSuchMethodException | SecurityException ex) {
            log.log(Level.INFO, "Erro in method getValuesClassColumns(Class<?> table, Field field, Object obj) ->> Field: "+ field.getName() +" isn't method getter and setter", ex);
        }
        try {
            objClass = m.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            log.log(Level.INFO, "Error in method getValuesClassColumns(Class<?> table, Field field, Object obj)", ex);
        }
        for (Field currentField : field.getType().getDeclaredFields()) {
            currentField.setAccessible(true);
            getValuesFields(query, field.getType(), currentField, objClass);
        }
    }
}
