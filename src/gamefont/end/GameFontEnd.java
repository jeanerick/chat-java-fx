/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefont.end;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jeana
 */
public class GameFontEnd extends Application {   
    
  @Override public void start(Stage stage) throws IOException {
    Scene scene = new Scene(new StackPane(),1024,768);
    
    ControlerMaster loginManager = new ControlerMaster(scene);
    loginManager.showLoginScreen();

    stage.setScene(scene);
    stage.show();
  }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
