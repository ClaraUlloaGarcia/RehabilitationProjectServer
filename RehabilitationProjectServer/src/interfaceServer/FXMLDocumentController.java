
package interfaceServer;

import server.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private PasswordField passwords;
    
    
    private Server server = new Server();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        final String PASSWORD = "1234"; //Fixed password to shut down the server
        if(passwords.getText().equals(PASSWORD)) {
            server.shutdownServer();
            System.exit(0);
        } else {
            //Incorrect password
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Wrong Password."); 
            alert.show(); 
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        server.start();
    }    
}
