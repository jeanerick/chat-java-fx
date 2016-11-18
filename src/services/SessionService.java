/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author jeana
 */
public class SessionService {
    private String nome;
    private String ip;
    private String serverIp;
    private boolean isServer;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public boolean isIsServer() {
        return isServer;
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    @Override
    public String toString() {
        return "SessionService{" + "nome=" + nome + ", ip=" + ip + ", serverIp=" + serverIp + ", isServer=" + isServer + '}';
    }

   
    
    
    
    
}
