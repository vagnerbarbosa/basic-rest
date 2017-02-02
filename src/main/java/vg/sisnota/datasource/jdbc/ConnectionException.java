/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vg.sisnota.datasource.jdbc;

/**
 *
 * @author vagner
 */
public class ConnectionException extends Exception {
    public ConnectionException(String msg) {
        super(msg);
    }
}