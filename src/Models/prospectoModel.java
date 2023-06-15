package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Services.conexionDB;

public class prospectoModel {

    private int id;
    private String nombre;
    private String telefono;
    private String correo;
    private String interes;
    private String carrera;
    private String estado;
    private String detalles;
    private conexionDB conexion;

    public prospectoModel() {
        conexion = new conexionDB();
    }

    public prospectoModel(int id, String nombre, String telefono, String correo, String interes, String carrera,
            String estado, String detalles) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.interes = interes;
        this.carrera = carrera;
        this.estado = estado;
        this.detalles = detalles;
        conexion = new conexionDB();
    }

    // Funciones
    public boolean create() {
        String sql = "INSERT INTO prospecto (nombre, telefono, correo, interes, carrera, estado, detalles) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, this.nombre);
            ps.setString(2, this.telefono);
            ps.setString(3, this.correo);
            ps.setString(4, this.interes);
            ps.setString(5, this.carrera);
            ps.setString(6, this.estado);
            ps.setString(7, this.detalles);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar la inserción", e);
        }
    }

    public boolean update() {
        String sql = "UPDATE prospecto SET nombre = ?, telefono = ?, correo = ?, interes = ?, carrera = ?, estado = ?, detalles = ? WHERE id = ?";
        try (Connection con = conexion.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, this.nombre);
            ps.setString(2, this.telefono);
            ps.setString(3, this.correo);
            ps.setString(4, this.interes);
            ps.setString(5, this.carrera);
            ps.setString(6, this.estado);
            ps.setString(7, this.detalles);
            ps.setInt(8, this.id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar la actualización", e);
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM prospecto WHERE id = ?";
        try (Connection con = conexion.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar el borrado", e);
        }
    }

    public boolean exist(int id) {
        String sql = "SELECT * FROM prospecto WHERE id = ?";
        try (Connection con = conexion.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultado = ps.executeQuery()) {
                return resultado.next(); // Devuelve true si hay un registro, false si no
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getAll(LinkedList<String> params) {
        String tabla = "";
        Statement consulta;
        ResultSet resultado = null;
        tabla = "Content-Type: text/html; charset=\"UTF-8\"\n"
                + "\n"
                + "<h1>Lista de roles</h1>"
                + "<table style=\"border-collapse: collapse; width: 100%; border: 1px solid black;\">\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">ID</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">NOMBRE</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">TELEFONO</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">CORREO</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">INTERES</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">CARRERA</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">ESTADO</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #3c4f76; color: white; border: 1px solid black;\">DETALLES</th>\n"
                + "\n";

        try {
            String query;
            if (params.size() == 0)
                query = "SELECT id, nombre, telefono, correo, interes, carrera, estado, detalles FROM prospecto";
            else
                query = "SELECT id, nombre, telefono, correo, interes, carrera, estado, detalles FROM prospecto WHERE "
                        + params.get(0) + " LIKE '%" + params.get(1) + "%'";

            Connection con = conexion.connect();
            consulta = con.createStatement();
            resultado = consulta.executeQuery(query);
            ResultSetMetaData rsmd = resultado.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();
            while (resultado.next()) {
                tabla = tabla
                        + "  <tr>\n"
                        + "\n";
                for (int i = 0; i < cantidadColumnas; i++) {
                    tabla = tabla
                            + "    <td style = \"text-align: left; padding: 8px; border: 1px solid black;\">"
                            + resultado.getString(i + 1) + "</td>\n"
                            + "\n";
                }
                tabla = tabla
                        + "  </tr>\n"
                        + "\n";
            }
            tabla = tabla
                    + "\n"
                    + "</table>";
            consulta.close();
            con.close();
        } catch (SQLException e) {
            tabla = "No se pudieron listar los datos.";
        }
        return tabla;
    }

    public String getOne(int id) {
        String sql = "SELECT * FROM prospecto WHERE id = ?";
        try (Connection con = conexion.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    return "ID: " + resultado.getInt("id") + "<br>"
                            + "Nombre: " + resultado.getString("nombre") + "<br>"
                            + "Correo: " + resultado.getString("correo") + "<br>"
                            + "Contraseña: " + resultado.getString("contraseña") + "<br>"
                            + "Area: " + resultado.getString("area") + "<br>"
                            + "Rol: " + resultado.getString("rol_id") + "<br>";
                } else {
                    return "No se encontró el registro.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "No se encontró el registro.";
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}