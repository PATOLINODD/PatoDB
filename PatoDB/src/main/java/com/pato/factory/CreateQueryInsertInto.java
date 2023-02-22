/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.Utils.NovaString;
import com.pato.Utils.Utilidades;
import com.pato.anotacoes.query.ClassColunas;
import com.pato.anotacoes.query.Coluna;
import com.pato.anotacoes.util.Ignore;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateQueryInsertInto extends AbstractCreateQuery {

    private static final Logger log = Logger.getLogger(CreateQueryInsertInto.class.getName());

    String[] valores;

    public CreateQueryInsertInto(StringBuilder query, Object objTable) {
        super(query, objTable.getClass());
        this.objTable = objTable;
    }


    @Override
    public StringBuilder getQuery() throws InstantiationException, IllegalAccessException {
        log.log(Level.INFO, "CreateQueryInsertInto ->> entrando no metodo getQuery()");

        this.query.append("INSERT INTO ")
                .append(alias);
        this.query.append("( ");

        List<String> columns = Utilidades.getColumns(this.table);

        columns.forEach(currentColumn -> {
            this.query.append(currentColumn).append(", ");
        });

        NovaString.removeString(query, ", ");

        query.append(" ) VALUES ( ");

        for (Field currentField : this.table.getDeclaredFields()) {
            currentField.setAccessible(true);
            if (currentField.isAnnotationPresent(ClassColunas.class)) {
                Utilidades.getValuesClassColumns(this.query, this.table, currentField, this.objTable);
            } else if (currentField.isAnnotationPresent(Coluna.class)) {
                Utilidades.getValuesFields(this.query, this.table, currentField, this.objTable);
            } else if(!currentField.isAnnotationPresent(Ignore.class)){
                Utilidades.getValuesFields(this.query, this.table, currentField, this.objTable);
            }
            Arrays.asList(currentField.getDeclaredAnnotations()).forEach(System.out::println);
        }
        NovaString.removeString(query, ", ");
        query.append(" )");
        return this.query;
    }

}
