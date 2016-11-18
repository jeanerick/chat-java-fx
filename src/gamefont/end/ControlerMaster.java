/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefont.end;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.SessionService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author jeana
 */
public class ControlerMaster {

    private SessionService GAME_SESSION;
    private Scene scene;

   

    public ControlerMaster(Scene scene) {
        this.scene = scene;
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("EntranceSelector.fxml")
            );
            scene.setRoot((Parent) loader.load());
            EntranceSelectorController controller
                    = loader.<EntranceSelectorController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(ControlerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showMainView(SessionService GAME_SESSION) {
        this.setGAME_SESSION(GAME_SESSION);
        System.out.println(GAME_SESSION.toString());
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("FXMLDocument.fxml")
            );
            scene.setRoot((Parent) loader.load());
            FXMLDocumentController controller
                    = loader.<FXMLDocumentController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(ControlerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public SessionService getGAME_SESSION() {
        return GAME_SESSION;
    }

    public void setGAME_SESSION(SessionService GAME_SESSION) {
        this.GAME_SESSION = GAME_SESSION;
    }

}
