/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.soc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author jeana
 */
public class Socket_Client {

    public static Socket socket;
    private OutputStream ou;
    private Writer ouw;
    private BufferedWriter bfw;

    public void conectar(String ip, String porta, String username) throws IOException {
        try {
            socket = new Socket(ip, Integer.parseInt(porta));
            //System.out.println("Conectado em " + server + ":" + porta);

            ou = socket.getOutputStream();
            ouw = new OutputStreamWriter(ou);
            bfw = new BufferedWriter(ouw);
            bfw.write(username);
            bfw.flush();
            enviarMensagem(username+" Conectou-se");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

    }

    public void enviarMensagem(String msg) throws IOException {
        if (msg.equals("Sair")) {
            bfw.write("Desconectado \r\n");
            //saida.append("Desconectado \r\n");
        } else {
            bfw.write(msg + "\r\n");
            //saida.append(entrada.getText() + "\r\n");
        }
        bfw.flush();
        //entrada.setText("");
    }

    public void createSocketListner(String ip, String porta, String username) {
        try {
            this.conectar(ip, porta, username);
            Listner listner = new Listner();
            Thread t = new Thread(listner);
            t.setDaemon(true);

            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sair() throws IOException {

        enviarMensagem("Sair");
        bfw.close();
        ouw.close();
        ou.close();
        socket.close();
    }

    public class Listner implements Runnable {

        @Override
        public void run() {
            try {
                InputStream in = socket.getInputStream();
                InputStreamReader inr = new InputStreamReader(in);
                BufferedReader bfr = new BufferedReader(inr);
                String msg = "";
                while (!"Sair".equalsIgnoreCase(msg)) {
                    try {
                        if (bfr.ready()) {
                            msg = bfr.readLine();
                            if (msg.equals("Sair")) {
                                // saida.append("Servidor caiu! \r\n");
                            } else {
                                // saida.append(msg + "\r\n");
                            }
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.toString());
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                e.printStackTrace();
            }

        }

    }

}
