package com.acm.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    protected static final String DRIVER = "org.h2.Driver";
    protected static final String URL = "jdbc:h2:" + System.getProperty("user.dir") + "\\biblioteca"; // archivo servidor almacenado en el usuario: "jdbc:h2:tcp://localhost/~/test"
    protected static final String USER = "sa";
    protected static final String PASS = "";

    protected Connection con;

    public Conexion() {
        // System.out.println("Current path: " + URL);
        try {
            getConexion();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Connection getConexion() {
        try {
            if(con == null){
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, USER, PASS);
            }
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return con;
        }
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void cerrarConexion(){
        if(con != null){
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
