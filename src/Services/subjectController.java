package Services;

import java.util.LinkedList;

import Controllers.pruebaController;

public class subjectController {

    private String subject;
    private String emailEmisor;
    private smtpController smtp;

    public subjectController(String subject, String emailEmisor) {
        this.subject = subject;
        this.emailEmisor = emailEmisor;
        smtp = new smtpController();
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
        OpcionList();
    }

    private void OpcionList() {
        String response = "";
        subject = subject.trim();

        String[] parts = subject.split(" ");
        if (parts.length != 2) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que exista un espacio entre el comando y los parametros.");
            return;
        }
        String[] opcionArray = parts[0].split("-"); // [LIST, PRUEBA]
        if (opcionArray.length != 2) {
            smtp.sendEmail(emailEmisor,
                    "No se reconoce el formato indicado. Verifique que exista un '-' en el comando.");
            return;
        }

        System.out.println("parts[0]: " + parts[0]); // LIST-PRUEBA
        System.out.println("parts[1]: " + parts[1]); // [,,,,,]
        parts[1] = parts[1].replace("[", "");
        parts[1] = parts[1].replace("]", "");

        String opcion = opcionArray[1];
        System.out.println("opcionArray[0]: " + opcionArray[0]); // LIST
        System.out.println("opcionArray[1]: " + opcionArray[1]); // PRUEBA

        String[] parametros;
        if (parts[1].length() > 2)
            parametros = parts[1].split(","); // [, , , , ,]
        else
            parametros = new String[0];

        if (opcion.toLowerCase().equals("prueba")) {
            pruebaController prueba = new pruebaController();
            LinkedList<String> params = prueba.createList(parametros);
            switch (opcionArray[0].toLowerCase()) {
                case "list":
                    response = prueba.getAll(params);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "get":
                    response = prueba.get(Integer.parseInt(params.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "create":
                    response = prueba.create(params);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "update":
                    response = prueba.update(params);
                    smtp.sendEmail(emailEmisor, response);
                    break;
                case "delete":
                    response = prueba.delete(Integer.parseInt(params.get(0)));
                    smtp.sendEmail(emailEmisor, response);
                    break;
                default:
                    smtp.sendEmail(emailEmisor,
                            "No se reconoce el formato indicado. Verifique que sea una de estas opciones List, Get, Create, Update, Delete.");
                    break;
            }
            return;
        }
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
