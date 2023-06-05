import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ReturnListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id,myDate;
    
    
    @FXML
    private TableColumn<ReturnedBooks, String> IDcolumn;

    @FXML
    private TableColumn<ReturnedBooks, String> bIDcolumn;

    @FXML
    private TableColumn<ReturnedBooks, String> bookTitlecolumn;

    @FXML
    private TableColumn<ReturnedBooks, String> returnDatecolumn;

    @FXML
    private TableColumn<ReturnedBooks, String> studentNamecolumn;

    @FXML
    private TableView<ReturnedBooks> table;

    @FXML
    private TableColumn<ReturnedBooks, String> userNamecolumn;

    @FXML
    void goBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToLoanList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoanList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    //table
    public void table(){
        Connect();
        ObservableList<ReturnedBooks> returned = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select id,name,username,bid,booktitle,datereturn from returned");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          ReturnedBooks rt = new ReturnedBooks();
          rt.setId(rs.getString("id"));
          rt.setName(rs.getString("name"));
          System.out.println(rs.getString("name"));
          rt.setUserName(rs.getString("username"));
          rt.setbid(rs.getString("bid"));
          rt.setBookTitle(rs.getString("booktitle"));  
          rt.setDateReturn(rs.getString("datereturn"));


        
     
          returned.add(rt);
     }
  }
              table.setItems(returned);
              IDcolumn.setCellValueFactory(f -> f.getValue().idProperty());
              studentNamecolumn.setCellValueFactory(f -> f.getValue().nameProperty());
              userNamecolumn.setCellValueFactory(f -> f.getValue().userNameProperty());
              bIDcolumn.setCellValueFactory(f -> f.getValue().bidProperty());
              bookTitlecolumn.setCellValueFactory(f -> f.getValue().bookTitleProperty());
              returnDatecolumn.setCellValueFactory(f -> f.getValue().dateReturnProperty());
              
     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ReturnListController.class.getName()).log(Level.SEVERE, null, ex);
     }

              table.setRowFactory( tv -> {
   TableRow<ReturnedBooks> myRow = new TableRow<>();
   myRow.setOnMouseClicked (event ->
   {
      if (event.getClickCount() == 1 && (!myRow.isEmpty()))
      {

         myIndex =  table.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        //  txtBookTitle.setText(table.getItems().get(myIndex).getBookTitle());
        //  txtBookQuantity.setText(table.getItems().get(myIndex).getQuantity());
        //  txtBookCategory.setText(table.getItems().get(myIndex).getCategory());       
                        
      }else{
        table.getSelectionModel().clearSelection();
        // txtBookTitle.setText("");
        // txtBookQuantity.setText("");
        // txtBookCategory.setText("");
      }
   });
      return myRow;
                 });
    }



    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/student","root","");
            System.out.println("sucessfully connected to Student database");
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
