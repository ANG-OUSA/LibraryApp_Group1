import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.*;

public class StudentDashboardController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    private TextField bcategoryTxt;

    @FXML
    private TextField bquantityTxt;

    @FXML
    private TextField btitleTxt;

    @FXML
    private TextField searchBookCode;

    @FXML
    private TextField searchStudentID;

    @FXML
    private TextField stNameTxt;

    @FXML
    private TextField stUserNameTxt;

    @FXML
    private Label welcomeLabel;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginForStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // public void displayUserName(String userName, String id){
    //     welcomeLabel.setText("welcome "+userName+ "id "+ id);
    // }

    public void ConnectToStudent(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/student","root","");
            System.out.println("sucessfully connected to student database yayyy");
        } catch (ClassNotFoundException ex) {
          System.out.println("hikhik");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void displayUserName(String userName, String id, String name) throws SQLException{
        ResultSet rs1 = null;
        welcomeLabel.setText("welcome back "+userName);
        searchStudentID.setText(id);
        stUserNameTxt.setText(userName);
        stNameTxt.setText(name);
        ConnectToStudent();
        String sql1 = "select *from issued where id = '" + id + "'";
        pst = con.prepareStatement(sql1);
        rs1 = pst.executeQuery();
        if(rs1.next()){
            System.out.println("yayyy "+rs1.getString("bid"));
            searchBookCode.setText(rs1.getString("bid"));
            btitleTxt.setText(rs1.getString("booktitle"));
            bquantityTxt.setText(rs1.getString("quantity"));
            bcategoryTxt.setText(rs1.getString("category"));

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }








}
