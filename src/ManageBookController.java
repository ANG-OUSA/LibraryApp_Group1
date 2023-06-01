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


public class ManageBookController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TableColumn<Book, String> IDcolumn;

    @FXML
    private TableColumn<Book, String> bookNamecolumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Book, String> categorycolumn;

    @FXML
    private TableColumn<Book, String> datecolumn;

    @FXML
    private TableColumn<Book, String> quantitycolumn;

    @FXML
    private TableView<Book> table;

    @FXML
    private TextField txtBookCategory;

    @FXML
    private TextField txtBookQuantity;

    @FXML
    private TextField txtBookTitle;

    @FXML
    void AddBook(ActionEvent event) {

        String bookTitle, quantity, category;
        bookTitle = txtBookTitle.getText();
        quantity= txtBookQuantity.getText();
        category = txtBookCategory.getText();
        
        try{

           
            pst = con.prepareStatement("insert into books(booktitle,quantity,category)values(?,?,?)");
            pst.setString(1,bookTitle);
            pst.setString(2,quantity);
            pst.setString(3,category);
            // pst.setString(4,myDate);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("test connection");
            alert.setContentText("added Book sucessfully");
            alert.showAndWait();
            table();

        }catch(Exception e){
            Logger.getLogger(ManageBookController.class.getName()).log(Level.SEVERE, null, e);
        }
       
    }

    @FXML
    void deleteBook(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
		 
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                     

        try 
        {
            pst = con.prepareStatement("delete from books where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Registation");

		
		alert.setHeaderText("Book Registation");
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
    void updateBook(ActionEvent event) {

        String bookTitle, quantity, category;
        
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));         
        bookTitle = txtBookTitle.getText();
        quantity= txtBookQuantity.getText();
        category = txtBookCategory.getText();

    try
    {
        pst = con.prepareStatement("update books set booktitle = ?,quantity = ? ,category = ? where id = ? ");
        pst.setString(1, bookTitle);
        pst.setString(2, quantity);
        pst.setString(3, category);
        pst.setInt(4, id);
        pst.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Manage Books");
        alert.setHeaderText("Book Management");
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




     //table
     public void table(){
        Connect();
        ObservableList<Book> books = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select id,booktitle,quantity,category,mydate from books");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Book bk = new Book();
          bk.setId(rs.getString("id"));
          bk.setBookTitle(rs.getString("booktitle"));
          bk.setQuantity(rs.getString("quantity"));
          bk.setCategory(rs.getString("category"));
          bk.setMyDate(rs.getString("mydate"));

        
     
          books.add(bk);
     }
  }
              table.setItems(books);
              IDcolumn.setCellValueFactory(f -> f.getValue().idProperty());
              bookNamecolumn.setCellValueFactory(f -> f.getValue().bookTitleProperty());
              quantitycolumn.setCellValueFactory(f -> f.getValue().quantityProperty());
              categorycolumn.setCellValueFactory(f -> f.getValue().categoryProperty());
              datecolumn.setCellValueFactory(f -> f.getValue().myDateProperty());
              
     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ManageBookController.class.getName()).log(Level.SEVERE, null, ex);
     }

              table.setRowFactory( tv -> {
   TableRow<Book> myRow = new TableRow<>();
   myRow.setOnMouseClicked (event ->
   {
      if (event.getClickCount() == 1 && (!myRow.isEmpty()))
      {

         myIndex =  table.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
         txtBookTitle.setText(table.getItems().get(myIndex).getBookTitle());
         txtBookQuantity.setText(table.getItems().get(myIndex).getQuantity());
         txtBookCategory.setText(table.getItems().get(myIndex).getCategory());       
                        
      }else{
        table.getSelectionModel().clearSelection();
        txtBookTitle.setText("");
        txtBookQuantity.setText("");
        txtBookCategory.setText("");
      }
   });
      return myRow;
                 });
    }



    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id,myDate;

    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:/book","root","");
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