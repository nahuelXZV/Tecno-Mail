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
        System.out.println("Cantidad de mails: " + cantMails);
        // try {
        // pop.getMail();
        // } catch (Exception e) {
        // System.out.println("Error al obtener mails");
        // }
        while (true) {
            int newCantsMails = pop.getCantidadEmails();
            System.out.println("Cantidad de mails: " + newCantsMails);
            if (cantMails != newCantsMails) {
                cantMails = newCantsMails;
                try {
                    pop.getMail();
                } catch (Exception e) {
                    System.out.println("Error al obtener mails");
                }
            }
            try {
                Thread.sleep(6000);
            } catch (InterruptedException ex) {
                // Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
