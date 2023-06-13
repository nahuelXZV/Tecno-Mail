/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.LinkedList;

import Models.pruebaModel;
import utils.validatorUtils;

/**
 *
 * @author Nahuel
 */
public class pruebaController {
    private pruebaModel prueba;
    private String respuesta;

    public pruebaController() {
        prueba = new pruebaModel();
    }

    public String create(LinkedList<String> params) {
        this.validate(params);
        if (this.respuesta != null)
            return this.respuesta;
        prueba.setId(Integer.parseInt(params.get(0)));
        prueba.setNombre(params.get(1));
        if (prueba.create()) {
            respuesta = "Creado exitosamente.";
        } else {
            respuesta = "No se pudo crear.";
        }
        return respuesta;
    }

    public String update(LinkedList<String> params) {
        validate(params);
        if (this.respuesta != null)
            return this.respuesta;
        prueba.setId(Integer.parseInt(params.get(0)));
        prueba.setNombre(params.get(1));
        if (prueba.update()) {
            respuesta = "Actualizado exitosamente.";
        } else {
            respuesta = "No se pudo actualizar.";
        }
        return respuesta;
    }

    public String delete(int id) {
        if (!validatorUtils.validateNumber(String.valueOf(id)))
            return "El id debe ser un numero";
        prueba.setId(id);
        if (prueba.delete()) {
            respuesta = "Eliminado exitosamente.";
        } else {
            respuesta = "No se pudo eliminar.";
        }
        return respuesta;
    }

    public String getAll(LinkedList<String> params) {
        return prueba.getAll(params);
    }

    public String get(int id) {
        if (!validatorUtils.validateNumber(String.valueOf(id)))
            return "El id debe ser un numero";
        prueba.setId(id);
        return prueba.getOne(id);
    }

    private void validate(LinkedList<String> params) {
        if (params.size() != 2) {
            this.respuesta = "La cantidad de parametros es incorrecta";
            return;
        }
        if (!validatorUtils.validateString(params.get(1))) {
            this.respuesta = "El nombre no puede ser vacio";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(0))) {
            this.respuesta = "El id debe ser un numero";
            return;
        }
        if (prueba.exist(Integer.parseInt(params.get(0)))) {
            this.respuesta = "El id ya existe";
            return;
        }
    }

    public LinkedList<String> createList(String[] params) {
        LinkedList<String> list = new LinkedList<>();
        for (String param : params) {
            list.add(param);
        }
        return list;
    }
}
