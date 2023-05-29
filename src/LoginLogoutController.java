
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


import javax.swing.Action;
public class LoginLogoutController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void loginbutton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("AboutUS.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtocategories(ActionEvent event)throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Categories.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtologin(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("user_login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}







