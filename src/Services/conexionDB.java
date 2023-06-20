/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.*;

/**
 *
 * @author Nahuel
 */
public class conexionDB {

    private final String DB = "db_grupo06sa";
    private final String USER = "grupo06sa";
    private final String PASSWORD = "grup006grup006";
    private final String URL = "jdbc:postgresql://tecnoweb.org.bo:5432/" + DB;
    private Connection conn;

    // private Statement statement;
    public conexionDB() {
    }

    public Connection getConexion() {
        return conn;
    }

    public void close() {
        try {
            conn.close();
            System.out.println("Cerrando conexion");
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexion");
        }
    }

    public Connection connect() {
        try {
            System.out.println("conectando....");
            Class.forName("org.postgresql.Driver");
            conn = (Connection) DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error al conectar" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al conectar" + ex);
        }
        return conn;
    }
}