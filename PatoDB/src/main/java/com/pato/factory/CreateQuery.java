/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.factory;

import com.pato.anotacoes.query.Juntar;
import java.lang.reflect.Field;
import com.pato.anotacoes.query.Onde;
import com.pato.enuns.Ignore;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class CreateQuery {

    private static final Logger log = Logger.getLogger(CreateQuery.class.getName());

    StringBuilder query;
    private Class<?> table;
    private String alias;
    private Object objTable;

    public CreateQuery() {
        query = new StringBuilder();
    }

    public CreateQuery(Object objTable) {
        this.objTable = objTable;
        this.table = objTable.getClass();
        query = new StringBuilder();
        this.alias = table.getSimpleName();
    }

    public CreateQuery(Class<?> classTable, boolean create) {
        this.table = classTable;
        query = new StringBuilder();
        this.alias = table.getSimpleName();
    }

    boolean temWhere = false, temJoin = false;

    public String createSelect() throws InstantiationException, IllegalAccessException {
        query = new CreateQuerySelect(query, this.table).getQuery();

        for (Field field : this.table.getDeclaredFields()) {
            if (field.isAnnotationPresent(Onde.class)) {
                temWhere = true;
            }
            if (field.isAnnotationPresent(Juntar.class)) {
                temJoin = true;
            }
        }
        if (temJoin) {
            query = new QueryJoin(this.table).join(query).getQuery();
        }
        if (temWhere) {
            query = new QueryWhere(this.table).where(query).getQuery();
        }
        return query.toString();
    }

    public String createUpdate() throws InstantiationException, IllegalAccessException {
        query = new CreateQueryUpdate(query, objTable).getQuery();
        for (Field field : this.table.getDeclaredFields()) {
            if (field.isAnnotationPresent(Onde.class)) {
                temWhere = true;
            }
        }
        if (temWhere) {
            query = new QueryWhere(this.table).where(query).getQuery();
        }
        return query.toString();
    }

    public String createDelete() throws InstantiationException, IllegalAccessException {
        query = new CreateQueryDelete(query, objTable).getQuery();
        for (Field field : this.table.getDeclaredFields()) {
            if (field.isAnnotationPresent(Onde.class)) {
                temWhere = true;
            }
        }
        if (temWhere) {
            query = new QueryWhere(this.table).where(query).getQuery();
        }
        return query.toString();
    }

    public String createCriar() throws InstantiationException, IllegalAccessException {
        return new CreateQueryCreate(query, table).getQuery().toString();
    }

    public String createInsetInto() throws InstantiationException, IllegalAccessException {
        return new CreateQueryInsertInto(query, objTable).getQuery().toString();
    }

    public String createTruncate() throws InstantiationException, IllegalAccessException {
        return new CreateQueryTruncate(query, objTable).getQuery().toString();
    }
}
