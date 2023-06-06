
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import com.mysql.cj.xdevapi.PreparableStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public String username;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private Label adminGreeting;

    @FXML
    void gotoAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switch1 (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void switch2 (ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginOption.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToManageBook(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManageBook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToLoanBook(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loanBook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToReturnBook(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("returnBook.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToManageStudent(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manageStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    private ImageView LibrarianIcon;

    @FXML
    private Label LoginLabel;

    @FXML
    private ImageView StudentIcon;

    @FXML
    void LoginAsLibrarian(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void LoginAsStudent(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginForStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnLoginAsStudent(ActionEvent event) {
        String title = tfTitle.getText();
        String password = tfPassword.getText();
        String id,name,username;

        if(title.equals("") && password.equals("")){
            description.setText("Your username or password is empty.");
            JOptionPane.showMessageDialog(null,"empty input fields");
        }else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:/student","root","");      
                pst = con.prepareStatement("SELECT * FROM students WHERE username=? AND password=?"); 
                pst.setString(1, title);
                pst.setString(2, password);

    
                rs = pst.executeQuery();



                if(rs.next()){
                    
                    pst = con.prepareStatement("select id,username,password from students");
                    System.out.println(rs.getString("id"));
                    id = rs.getString("id");
                    name = rs.getString("name");
                    username = rs.getString("username");
                    JOptionPane.showMessageDialog(null, "login success welcome "+username);
                    
                    //redirect to main page
          
                    // Parent root = FXMLLoader.load(getClass().getResource("studentDashboard.fxml")); 
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("studentDashboard.fxml"));
                    root = loader.load();
                    StudentDashboardController sdc = loader.getController();
                    sdc.displayUserName(title,id,name);
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();

                }else{
                    JOptionPane.showMessageDialog(null, "Login failed");   
                    tfPassword.setText("");
                    tfTitle.setText("");
                    tfTitle.requestFocus();
                }

            }catch(Exception e){
                System.out.println("error");
            }
        }
    }

    
    @FXML
    private Label description;

    @FXML
    private PasswordField tfPassword;
    
    @FXML
    private TextField tfTitle;

    public void displayAdminUserName(String email) throws SQLException{
        adminGreeting.setText("welcome back "+email);   
    }


    @FXML
    void btnOkClicked(ActionEvent event) {
        // Stage mainWindow = (Stage) tfTitle.getScene().getWindow();
        //getting the username and password
        String title = tfTitle.getText();
        String password = tfPassword.getText();

        if(title.equals("") && password.equals("")){
            description.setText("Your username or password is empty.");
            JOptionPane.showMessageDialog(null,"empty input fields");
        }else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:/myadmin","root","");      
                pst = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
                pst.setString(1, title);
                pst.setString(2, password);
                rs = pst.executeQuery();

                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "login success welcome "+title);
                    // description.setText(rs.getString("address"));
                    // System.out.println(rs.getString("email"));
    
                    // redirect to main page
                    Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                }else{
                    JOptionPane.showMessageDialog(null, "Login failed");   
                    tfPassword.setText("");
                    tfTitle.setText("");
                    tfTitle.requestFocus();
                }

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }







}
