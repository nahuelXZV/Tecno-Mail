package Services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class smtpService {
    final String servidor = "mail.tecnoweb.org.bo";
    String user_emisor = "grupo06sa@tecnoweb.org.bo";
    String line;
    String comando = "";
    String receptor = "";
    String mensaje = "";
    int puerto = 25;
    private Socket socket;
    BufferedReader entrada;
    DataOutputStream salida;

    public smtpService() {
    }

    public void sendEmail(String receptor, String mensaje) {
        try {
            this.receptor = receptor;
            this.mensaje = mensaje;
            socket = new Socket(servidor, puerto);
            Socket socket = new Socket(servidor, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new DataOutputStream(socket.getOutputStream());
            if (socket != null && entrada != null && salida != null) {
                System.out.println(" S : Conectado al servidor");
                login();
                send();
                logout();
            }
            disconnect();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : No se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void disconnect() {
        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() throws IOException {
        comando = "EHLO " + servidor + "\r\n";
        salida.writeBytes(comando);
    }

    private void send() throws IOException {
        comando = "MAIL FROM :" + user_emisor + "\r\n";
        salida.writeBytes(comando);

        comando = "RCPT TO :" + receptor + "\r\n";
        salida.writeBytes(comando);

        comando = "DATA\r\n";
        salida.writeBytes(comando);

        comando = "SUBJECT :" + "Respuesta" + "\r\n";
        comando += mensaje + "\r\n";
        comando += ".\r\n";
        salida.writeBytes(comando);
        System.out.println(" S : " + entrada.readLine());
    }

    private void logout() throws IOException {
        comando = "QUIT\r\n";
        salida.writeBytes(comando);
    }
}
