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

public class LoanListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id,myDate;
    
    @FXML
    private TableColumn<LoanBook, String> IDcolumn;

    @FXML
    private TableColumn<LoanBook, String> bIDcolumn;

    @FXML
    private TableColumn<LoanBook, String> bookAuthorcolumn;

    @FXML
    private TableColumn<LoanBook, String> bookTitlecolumn;

    @FXML
    private TableColumn<LoanBook, String> bookcategorycolumn;

    @FXML
    private TableColumn<LoanBook, String> loanDatecolumn;

    @FXML
    private TableColumn<LoanBook, String> studentNamecolumn;

    @FXML
    private TableView<LoanBook> table;

    @FXML
    private TableColumn<LoanBook, String> userNamecolumn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void goToReturnedList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReturnList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

        //table
        public void table(){
            Connect();
            ObservableList<LoanBook> loanbook = FXCollections.observableArrayList();
         try
         {
             pst = con.prepareStatement("select id,name,username,bid,booktitle,quantity,category,dateissued from issued");  
             ResultSet rs = pst.executeQuery();
        {
          while (rs.next())
          {
              LoanBook lb = new LoanBook();
              lb.setId(rs.getString("id"));
              lb.setName(rs.getString("name"));
              lb.setUserName(rs.getString("username"));
              lb.setbid(rs.getString("bid"));
              lb.setBookTitle(rs.getString("booktitle"));  
              lb.setQuantity(rs.getString("quantity"));
              lb.setCategory(rs.getString("category"));
              System.out.println(rs.getString("dateissued"));
              lb.setDateissued(rs.getString("dateissued"));


              loanbook.add(lb);
         }
      }
                  table.setItems(loanbook);
                  IDcolumn.setCellValueFactory(f -> f.getValue().idProperty());
                  studentNamecolumn.setCellValueFactory(f -> f.getValue().nameProperty());
                  userNamecolumn.setCellValueFactory(f -> f.getValue().userNameProperty());
                  bIDcolumn.setCellValueFactory(f -> f.getValue().bidProperty());
                  bookTitlecolumn.setCellValueFactory(f -> f.getValue().bookTitleProperty());
                  bookAuthorcolumn.setCellValueFactory(f -> f.getValue().quantityProperty());
                  bookcategorycolumn.setCellValueFactory(f -> f.getValue().categoryProperty());
                  loanDatecolumn.setCellValueFactory(f -> f.getValue().dateissuedProperty());
                  
         }
        
         catch (SQLException ex)
         {
             Logger.getLogger(LoanBookController.class.getName()).log(Level.SEVERE, null, ex);
         }
    
                  table.setRowFactory( tv -> {
       TableRow<LoanBook> myRow = new TableRow<>();
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
    public void initialize(URL arg0, ResourceBundle arg1) {
        Connect();
        table();
    }

}
