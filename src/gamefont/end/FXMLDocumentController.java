/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefont.end;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import services.soc.Socket_Client;

/**
 *
 * @author jeana
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    Label info;
    @FXML
    private TextArea outputText;
    @FXML
    private TextField inputText;

    public static Socket socket;
    private OutputStream ou;
    private Writer ouw;
    private BufferedWriter bfw;

    @FXML
    private void onTextSend(KeyEvent e) {
        System.out.println();
        if (e.getCode().getName().equals("Enter")) {
            try {

               enviarMensagem(this.inputText.getText());
                
            } catch (Exception err) {
                err.printStackTrace();
            }

        }

    }   
    

    public void initManager(final ControlerMaster loginManager) {
        info.setText(loginManager.getGAME_SESSION().toString());
        if (loginManager.getGAME_SESSION().isIsServer()) {

        } else {
            
            try {
                conectar(loginManager);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    
       public void conectar(ControlerMaster loginManager) throws IOException {
        try {
        socket = new Socket(loginManager.getGAME_SESSION().getIp(), Integer.parseInt("8080"));
//        System.out.println("Conectado em " + server + ":" + porta);
//        JOptionPane.showMessageDialog(null,"Conectado ao servidor: "+ip+":"+porta+'\n'+
//                "seu IP é: "+ciente+":"+porta);
        ou = socket.getOutputStream();
        ouw = new OutputStreamWriter(ou);
        bfw = new BufferedWriter(ouw);
        bfw.write(loginManager.getGAME_SESSION().getNome());
        bfw.flush();
        enviarMensagem(" ");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            e.printStackTrace();
        }
       
    }

    public void enviarMensagem(String msg) throws IOException {

        if (msg.equals("Sair")) {
            bfw.write("Desconectado \r\n");
            outputText.appendText("Desconectado \r\n");
        } else {
            bfw.write(msg + "\r\n");
            outputText.appendText(inputText.getText() + "\r\n");
        }
        bfw.flush();
        inputText.setText("");
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
                                outputText.appendText("Servidor caiu! \r\n");
                            } else {
                                outputText.appendText(msg + "\r\n");
                            }
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,e.toString());
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString());
                e.printStackTrace();
            }

        }

    }

}
