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
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageAdminController implements Initializable {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int myIndex;
    int id;

    @FXML
    private TableColumn<Admin, String> IDcolumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Admin, String> passwordcolumn;

    @FXML
    private TableColumn<Admin, String> phoneColumn;

    @FXML
    private TableColumn<Admin, String> studentNamecolumn;

    @FXML
    private TableView<Admin> table;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsername;

    @FXML
    private TableColumn<Admin, String> userNamecolumn;

    @FXML
    void AddAdmin(ActionEvent event) {
        String name, email, password, phone;
        name = txtName.getText();
        email= txtUsername.getText();
        password = txtPassword.getText();
        phone = txtPhone.getText();
        
        try{

           
            pst = con.prepareStatement("insert into users(name,email,password,phone)values(?,?,?,?)");
            pst.setString(1,name);
            pst.setString(2,email);
            pst.setString(3,password);
            pst.setString(4, phone);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("test connection");
            alert.setContentText("added Admin sucessfully");
            alert.showAndWait();
            table();

        }catch(Exception e){
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void deleteAdmin(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
		 
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                     

        try 
        {
            pst = con.prepareStatement("delete from users where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Admin Registation");

		
		alert.setHeaderText("Admin Registation");
		alert.setContentText("Deleted!");

		alert.showAndWait();
                  table();
        } 
        catch (SQLException ex)
        {
            System.out.println("error");
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void updateAdmin(ActionEvent event) {
        String name,email,password,phone;
        
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));         
        name = txtName.getText();
        email = txtUsername.getText();
        password = txtPassword.getText();
        phone = txtPhone.getText();
    try
    {
        pst = con.prepareStatement("update users set name = ?,email = ? ,password = ?, phone = ? where id = ? ");
        pst.setString(1, name);
        pst.setString(2, email);
        pst.setString(3, password);
        pst.setString(4, phone);
        pst.setInt(5, id);
        pst.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Admin Registation");

        alert.setHeaderText("Admin Registation");
        alert.setContentText("Updated!");

        alert.showAndWait();
         
        table();
    }
    catch (SQLException ex)
    {
        System.out.println("error updating");
    }
    }

     //table
     public void table(){
        Connect();
        ObservableList<Admin> admins = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select id,name,email,password,phone from users");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Admin ad = new Admin();
          ad.setId(rs.getString("id"));
          ad.setName(rs.getString("name"));
          ad.setUserName(rs.getString("email"));
          ad.setPassword(rs.getString("password"));
          ad.setPhone(rs.getString("phone"));

          admins.add(ad);
     }
  }
              table.setItems(admins);
              IDcolumn.setCellValueFactory(f -> f.getValue().idProperty());
              studentNamecolumn.setCellValueFactory(f -> f.getValue().nameProperty());
              userNamecolumn.setCellValueFactory(f -> f.getValue().userNameProperty());
              passwordcolumn.setCellValueFactory(f -> f.getValue().passwordProperty());
              phoneColumn.setCellValueFactory(f -> f.getValue().phoneProperty());
              
            

     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ManageAdminController.class.getName()).log(Level.SEVERE, null, ex);
     }

              table.setRowFactory( tv -> {
   TableRow<Admin> myRow = new TableRow<>();
   myRow.setOnMouseClicked (event ->
   {
      if (event.getClickCount() == 1 && (!myRow.isEmpty()))
      {

         myIndex =  table.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
         txtName.setText(table.getItems().get(myIndex).getName());
         txtUsername.setText(table.getItems().get(myIndex).getUserName());
         txtPassword.setText(table.getItems().get(myIndex).getPassword());       
         txtPhone.setText(table.getItems().get(myIndex).getPhone());
                        
      }else{
        table.getSelectionModel().clearSelection();
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
      }
   });
      return myRow;
                 });
    }

    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/myadmin","root","");
            System.out.println("sucessfully connected");
        } catch (ClassNotFoundException ex) {
          System.out.println("hikhik");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connect();
        table();
    }

}
