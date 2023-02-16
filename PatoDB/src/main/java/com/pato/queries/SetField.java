/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pato.queries;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PATOLINODD
 */
public class SetField {
    private static final Logger log = Logger.getLogger(SetField.class.getName());
    
    protected void setString(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setString(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getString(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setInt(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setInt(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getInt(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setByte(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setByte(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getByte(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setShort(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setShort(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getShort(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setFloat(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setFloat(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getFloat(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setDouble(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setDouble(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getDouble(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setLong(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setLong(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getLong(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setChar(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setChar(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            char charValue = rs.getString(stmtField).charAt(0);
            field.set(obj, charValue);
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setLocalDate(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setLocalDate(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            field.set(obj, rs.getDate(stmtField));
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void setLocalDateTime(Object obj, Field field, ResultSet rs, int stmtField){
        log.log(Level.INFO, "Entrando no metodo setLocalDateTime(Object {0}, Field {1}, ResultSet {2}, int {3})", new Object[]{obj, field, rs, stmtField});
        try {
            LocalDateTime data = LocalDateTime.parse(rs.getString(stmtField).replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);
            field.set(obj, data);
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(SetField.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
