/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefont.end;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import services.SessionService;

/**
 * FXML Controller class
 *
 * @author jeana
 */
public class EntranceSelectorController implements Initializable {

    private SessionService GAME_SESSION = new SessionService();

    @FXML
    private Button loginButton;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField serverIp;
    @FXML
    private CheckBox isServer;
    
    public void connect() {
        GAME_SESSION.setNome(this.nomeField.getText());

        if (this.isServer.isSelected()) {
            GAME_SESSION.setIsServer(true);
            try {
                GAME_SESSION.setIp(InetAddress.getLocalHost().getHostAddress());
                GAME_SESSION.setServerIp(InetAddress.getLocalHost().getHostAddress());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            GAME_SESSION.setServerIp(this.serverIp.getText());
            try {
                GAME_SESSION.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void startAsServer() {
        if (this.isServer.isSelected()) {
            this.serverIp.setText("");
            this.serverIp.setDisable(true);
        } else {
            this.serverIp.setDisable(false);
        }
    }

    public void initManager(ControlerMaster loginManager) {       
        loginButton.setOnAction(new EventHandler<ActionEvent>( ) {
             EntranceSelectorController instance = getInstance();
            @Override
            public void handle(ActionEvent event) {
                instance.connect();
                System.out.println(GAME_SESSION.toString());                
                loginManager.showMainView(GAME_SESSION);
            }

           
        });
    }
    public EntranceSelectorController getInstance(){return this;}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
