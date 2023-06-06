import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.*;

public class ManageStudentController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    

    @FXML
    private TableColumn<Student, String> IDcolumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Student, String> majorcolumn;

    @FXML
    private TableColumn<Student, String> passwordcolumn;

    @FXML
    private TableColumn<Student, String> studentNamecolumn;

    @FXML
    private TableView<Student> table;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtMajor;

    @FXML
    private TableColumn<Student, String> userNamecolumn;


    //add students
    @FXML
    void AddStudent(ActionEvent event) {
     
        String name, userName, password, major;
        name = txtName.getText();
        userName= txtUsername.getText();
        password = txtPassword.getText();
        major = txtMajor.getText();
        
        try{

           
            pst = con.prepareStatement("insert into students(name,username,password,major,noIssued,counter)values(?,?,?,?,?,?)");
            pst.setString(1,name);
            pst.setString(2,userName);
            pst.setString(3,password);
            pst.setString(4, major);
            pst.setString(5,null);
            pst.setString(6,null);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("test connection");
            alert.setContentText("added Student sucessfully");
            alert.showAndWait();
            table();

        }catch(Exception e){
            Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, e);
        }
       
    }

    //table
    public void table(){
        Connect();
        ObservableList<Student> students = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select id,name,username,password,major from students");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Student st = new Student();
          st.setId(rs.getString("id"));
          st.setName(rs.getString("name"));
          st.setUserName(rs.getString("username"));
          st.setPassword(rs.getString("password"));
          st.setMajor(rs.getString("major"));

          students.add(st);
          System.out.println(rs.getString("username"));
     }
  }
              table.setItems(students);
              IDcolumn.setCellValueFactory(f -> f.getValue().idProperty());
              studentNamecolumn.setCellValueFactory(f -> f.getValue().nameProperty());
              userNamecolumn.setCellValueFactory(f -> f.getValue().userNameProperty());
              passwordcolumn.setCellValueFactory(f -> f.getValue().passwordProperty());
              majorcolumn.setCellValueFactory(f -> f.getValue().majorProperty());
              
            

     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ManageStudentController.class.getName()).log(Level.SEVERE, null, ex);
     }

              table.setRowFactory( tv -> {
   TableRow<Student> myRow = new TableRow<>();
   myRow.setOnMouseClicked (event ->
   {
      if (event.getClickCount() == 1 && (!myRow.isEmpty()))
      {

         myIndex =  table.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
         txtName.setText(table.getItems().get(myIndex).getName());
         txtUsername.setText(table.getItems().get(myIndex).getUserName());
         txtPassword.setText(table.getItems().get(myIndex).getPassword());       
         txtMajor.setText(table.getItems().get(myIndex).getMajor());
                        
      }else{
        table.getSelectionModel().clearSelection();
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtMajor.setText("");
      }
   });
      return myRow;
                 });
    }


    //delete students
    @FXML
    void deleteStudent(ActionEvent event) {
       
        myIndex = table.getSelectionModel().getSelectedIndex();
		 
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                     

        try 
        {
            pst = con.prepareStatement("delete from students where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registationn");

		
		alert.setHeaderText("Student Registation");
		alert.setContentText("Deleted!");

		alert.showAndWait();
                  table();
        } 
        catch (SQLException ex)
        {
            System.out.println("error");
        }
    }

    //update students
    @FXML
    void updateStudent(ActionEvent event) {
            String name,userName,password,major;
        
           myIndex = table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));         
           name = txtName.getText();
           userName = txtUsername.getText();
           password = txtPassword.getText();
           major = txtMajor.getText();
       try
       {
           pst = con.prepareStatement("update students set name = ?,username = ? ,password = ?, major = ? where id = ? ");
           pst.setString(1, name);
           pst.setString(2, userName);
           pst.setString(3, password);
           pst.setString(4, major);
           pst.setInt(5, id);
           pst.executeUpdate();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Student Registation");

           alert.setHeaderText("Student Registation");
           alert.setContentText("Updated!");

           alert.showAndWait();
            
           table();
       }
       catch (SQLException ex)
       {
           System.out.println("error updating");
       }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/student","root","");
            System.out.println("sucessfully connected");
        } catch (ClassNotFoundException ex) {
          System.out.println("hikhik");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
    }   



    
}
