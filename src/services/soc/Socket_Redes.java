/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.soc;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 *
 * @author jean
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Socket_Redes implements Runnable{

    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    public Socket_Redes(Socket con) {
        this.con = con;
        try {
            in = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {

            String msg;
            OutputStream ou = this.con.getOutputStream();
            Writer ouw = new OutputStreamWriter(ou);
            BufferedWriter bfw = new BufferedWriter(ouw);
            clientes.add(bfw);
            nome = msg = bfr.readLine();

            while (!"Sair".equalsIgnoreCase(msg) && msg != null) {
                msg = bfr.readLine();
                sendToAll(bfw, msg);
                System.out.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendToAll(BufferedWriter bwSaida, String msg) throws IOException {
        BufferedWriter bwS;

        for (BufferedWriter bw : clientes) {
            bwS = (BufferedWriter) bw;
            if (!(bwSaida == bwS)) {
                bw.write(nome + " -> " + msg + "\r\n");
                bw.flush();
            }
        }
    }
    public static void main(String []args) {
   
  try{
    //Cria os objetos necessário para instânciar o servidor
//    JLabel lblMessage = new JLabel("Porta do Servidor:");
//    JTextField txtPorta = new JTextField("8888");
//    Object[] texts = {lblMessage, txtPorta };
//    JOptionPane.showMessageDialog(null, texts);
    //JOptionPane.showMessageDialog(null, "8888");
    //server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
    server = new ServerSocket(8080);
    clientes = new ArrayList<BufferedWriter>();
    JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+         
    "8080");
   
     while(true){
       System.out.println("Aguardando conexão...");
       Socket con = server.accept();
       System.out.println("Cliente conectado...");
       Socket_Redes myRunnable = new Socket_Redes(con);
      Thread t = new Thread(myRunnable);
       t.start();   
    }
                             
  }catch (Exception e) {
   
    e.printStackTrace();
  }
  
 }
     public static void createMyServer(){
      try{
    //Cria os objetos necessário para instânciar o servidor
//    JLabel lblMessage = new JLabel("Porta do Servidor:");
//    JTextField txtPorta = new JTextField("8888");
//    Object[] texts = {lblMessage, txtPorta };
//    JOptionPane.showMessageDialog(null, texts);
    //JOptionPane.showMessageDialog(null, "8888");
    //server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
    server = new ServerSocket(8888);
    clientes = new ArrayList<BufferedWriter>();
    JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+         
    "8888");
   
     while(true){
       System.out.println("Aguardando conexão...");
       Socket con = server.accept();
       System.out.println("Cliente conectado...");
       Socket_Redes myRunnable = new Socket_Redes(con);
      Thread t = new Thread(myRunnable);
       t.start();   
    }
                             
  }catch (Exception e) {
   
    e.printStackTrace();
  }
     
     
     
     }
}
