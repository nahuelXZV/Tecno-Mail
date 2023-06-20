package Controllers;

import java.util.LinkedList;

import Models.estudianteNotaModel;
import Utils.validatorUtils;

public class estudianteNotaController {
    private estudianteNotaModel nota;
    private String respuesta;

    public estudianteNotaController() {
        nota = new estudianteNotaModel();
    }

    public String create(LinkedList<String> params) {
        this.validateCreate(params);
        if (this.respuesta != null)
            return this.respuesta;
        nota = new estudianteNotaModel(0, params.get(0), params.get(1), Integer.parseInt(params.get(2)),
                Integer.parseInt(params.get(3)), Integer.parseInt(params.get(4)));
        if (nota.create()) {
            respuesta = "Creado exitosamente.";
        } else {
            respuesta = "No se pudo crear.";
        }
        return respuesta;
    }

    public String update(LinkedList<String> params) {
        validateUpdate(params);
        if (this.respuesta != null)
            return this.respuesta;
        nota = new estudianteNotaModel(Integer.parseInt(params.get(0)), params.get(1), params.get(2),
                Integer.parseInt(params.get(3)),
                Integer.parseInt(params.get(4)), Integer.parseInt(params.get(5)));
        if (nota.update()) {
            respuesta = "Actualizado exitosamente.";
        } else {
            respuesta = "No se pudo actualizar.";
        }
        return respuesta;
    }

    public String delete(int id) {
        if (!validatorUtils.validateNumber(String.valueOf(id)))
            return "El id debe ser un numero";
        nota.setId(id);
        if (nota.delete()) {
            respuesta = "Eliminado exitosamente.";
        } else {
            respuesta = "No se pudo eliminar.";
        }
        return respuesta;
    }

    public String getAll(LinkedList<String> params) {
        return nota.getAll(params);
    }

    private void validateCreate(LinkedList<String> params) {
        if (params.size() != 5) {
            this.respuesta = "La cantidad de parametros es incorrecta";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(0))) {
            this.respuesta = "La nota debe ser un numero";
            return;
        }
        if (!validatorUtils.validateString(params.get(1))) {
            this.respuesta = "El detalle no puede ser vacio";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(2))) {
            this.respuesta = "El estudiante_id debe ser un numero";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(3))) {
            this.respuesta = "El programa_id debe ser un numero";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(4))) {
            this.respuesta = "El modulo_id debe ser un numero";
            return;
        }
    }

    private void validateUpdate(LinkedList<String> params) {
        if (params.size() != 6) {
            this.respuesta = "La cantidad de parametros es incorrecta";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(0))) {
            this.respuesta = "El id debe ser un numero";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(1))) {
            this.respuesta = "La nota debe ser un numero";
            return;
        }
        if (!validatorUtils.validateString(params.get(2))) {
            this.respuesta = "El detalle no puede ser vacio";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(3))) {
            this.respuesta = "El estudiante_id debe ser un numero";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(4))) {
            this.respuesta = "El programa_id debe ser un numero";
            return;
        }
        if (!validatorUtils.validateNumber(params.get(5))) {
            this.respuesta = "El modulo_id debe ser un numero";
            return;
        }
    }

    public LinkedList<String> createList(String[] params) {
        LinkedList<String> list = new LinkedList<>();
        for (String param : params) {
            param = param.trim();
            list.add(param);
        }
        return list;
    }
}
