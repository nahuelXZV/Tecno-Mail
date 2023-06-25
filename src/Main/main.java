/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Services.popService;

/**
 *
 * @author Nahuel
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start");
        popService pop = new popService();
        int cantMails = pop.getCantidadEmails();
        while (true) {
            int newCantsMails = pop.getCantidadEmails();
            System.out.println("Escuchando EMAILS...");
            if (cantMails != newCantsMails) {
                cantMails = newCantsMails;
                try {
                    System.out.println("********NEW EMAIL*****************");
                    pop.getMail();
                    System.out.println("*********************************");
                } catch (Exception e) {
                    System.out.println("Error al obtener emails");
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("Error en el servidor.");
            }
        }
    }

}
