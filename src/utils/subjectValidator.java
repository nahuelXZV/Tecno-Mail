package Utils;

import java.util.LinkedList;

import Controllers.calendarioAcademicoController;
import Controllers.contratoController;
import Controllers.docenteController;
import Controllers.estudianteController;
import Controllers.moduloController;
import Controllers.programaController;
import Controllers.prospectoController;
import Controllers.pruebaController;
import Controllers.rolController;
import Controllers.usuarioController;
import Services.smtpService;

public class subjectValidator {

    private String subject;
    private String emailEmisor;
    private smtpService smtp;

    public subjectValidator(String subject, String emailEmisor) {
        this.subject = subject;
        this.emailEmisor = emailEmisor;
        smtp = new smtpService();
    }

    // LIST-PRUEBA [,,,,,]
    public void ValidateSuject() {
        String subjectAux = subject.toLowerCase();
        subjectAux = subjectAux.trim();
        if (subjectAux.equals("help"))
            smtp.sendEmail(emailEmisor, AllComand());
        int parentesis1 = subject.indexOf("[");
        int parentesis2 = subject.indexOf("]");
        int espacio = subject.indexOf(" ");
        if (parentesis1 == -1 && parentesis2 == -1) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que está utilizando los corchetes([]) para realizar la petición.");
            return;
        }
        if (parentesis1 > parentesis2) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que está utilizando los corchetes([]) de forma ordenada.");
            return;
        }
        if (parentesis1 < 0) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que está enviando los datos dentro de un encabezado.");
            return;
        }
        if (espacio == -1) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que exista un espacio entre el comando y los parametros.");
            return;
        }
        subject = subject.trim();
        int firstSpace = subject.indexOf(" ");
        int parentesis1Aux = subject.indexOf("[");
        if (firstSpace == -1 || parentesis1Aux == -1) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que exista un espacio entre el comando y los parametros.");
            return;
        }
        if (parentesis1Aux < firstSpace) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que exista un espacio entre el comando y los parametros.");
            return;
        }
        String command = subject.substring(0, firstSpace);

        String[] opcionArray = command.split("-"); // [LIST, PRUEBA]
        if (opcionArray.length != 2) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que exista un '-' en el comando.");
            return;
        }

        OpcionList();
    }

    private void OpcionList() {
        String response = "";
        int firstSpace = subject.indexOf(" ");
        String command = subject.substring(0, firstSpace);
        String params = subject.substring(firstSpace + 1, subject.length());
        String[] opcionArray = command.split("-"); // [LIST, PRUEBA]

        System.out.println("command: " + command); // LIST-PRUEBA
        System.out.println("params: " + params); // [,,,,,]

        params = params.replace("[", "");
        params = params.replace("]", "");

        String opcion = opcionArray[1];
        System.out.println("opcionArray[0]: " + opcionArray[0]); // LIST
        System.out.println("opcionArray[1]: " + opcionArray[1]); // PRUEBA

        String[] parametros;
        if (params.length() >= 1)
            parametros = params.split(","); // [, , , , ,]
        else
            parametros = new String[0];

        if (opcion.toLowerCase().equals("prueba")) {
            pruebaController prueba = new pruebaController();
            LinkedList<String> paramsList = prueba.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = prueba.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    response = prueba.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = prueba.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = prueba.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = prueba.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("usuario")) {
            usuarioController usuario = new usuarioController();
            LinkedList<String> paramsList = usuario.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = usuario.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    response = usuario.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = usuario.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = usuario.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = usuario.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("rol")) {
            rolController rol = new rolController();
            LinkedList<String> paramsList = rol.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = rol.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    response = rol.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = rol.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = rol.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = rol.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("estudiante")) {
            estudianteController estudiante = new estudianteController();
            LinkedList<String> paramsList = estudiante.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = estudiante.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    response = estudiante.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = estudiante.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = estudiante.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = estudiante.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("prospecto")) {
            prospectoController prospecto = new prospectoController();
            LinkedList<String> paramsList = prospecto.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = prospecto.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    response = prospecto.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = prospecto.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = prospecto.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = prospecto.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("programa")) {
            programaController programa = new programaController();
            LinkedList<String> paramsList = programa.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = programa.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    // response = programa.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = programa.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = programa.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = programa.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("modulo")) {
            moduloController modulo = new moduloController();
            LinkedList<String> paramsList = modulo.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = modulo.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    // response = modulo.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = modulo.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = modulo.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = modulo.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("docente")) {
            docenteController docente = new docenteController();
            LinkedList<String> paramsList = docente.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = docente.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    // response = docente.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = docente.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = docente.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = docente.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("calendario")) {
            calendarioAcademicoController calendarioAcademico = new calendarioAcademicoController();
            LinkedList<String> paramsList = calendarioAcademico.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = calendarioAcademico.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    // response = calendarioAcademico.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = calendarioAcademico.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = calendarioAcademico.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = calendarioAcademico.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }

        if (opcion.toLowerCase().equals("contrato")) {
            contratoController contrato = new contratoController();
            LinkedList<String> paramsList = contrato.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = contrato.getAll(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    // response = contrato.get(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = contrato.create(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = contrato.update(paramsList);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = contrato.delete(Integer.parseInt(paramsList.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }
        smtp.sendEmail(emailEmisor, "Comando incorrecto, Verifique que este enviando bien los comandos");
        System.out.println("NO ENTRO ");
        // ....
    }

    private String AllComand() {
        return "Content-Type: text/html; charset=\"UTF-8\" \n \n"
                + "<h1>HAPPY KIDS - HELP</h1>"
                + "<table style=\" border-collapse: collapse; width: 100%; border: 1px solid red;\"> \n \n"
                + "<tr> \n \n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #f28080; color: white; border: 1px solid red;\"> Caso de Uso </th> \n \n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #f28080; color: white; border: 1px solid red;\"> Metodo </th> \n \n"
                + "<th style=\"text-align: center; padding: 8px; background-color: #f28080; color: white; border: 1px solid red;\"> Comando </th> \n \n"
                + "</tr> \n \n"
                + "<tr> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">CU1. Gestionar Usuarios (administrador)</td> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">Registrar Administrador</td> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">REGADMIN[id, nombre, password];</td> \n \n"
                + "</tr> \n \n"
                + "<tr> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">CU1. Gestionar Usuarios (administrador)</td> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">Eliminar Administrador</td> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">DELADMIN[id];</td> \n \n"
                + "</tr> \n \n"
                + "<tr> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">CU1. Gestionar Usuarios (administrador)</td> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">Listar Administradores</td> \n \n"
                + "<td style=\"text-align: left; padding: 8px; border: 1px solid red;\">LISTADMIN[];</td> \n \n"
                + "</tr> \n \n"

                + "</table>";

    }
}
