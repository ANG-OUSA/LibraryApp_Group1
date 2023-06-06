import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReturnedBookController implements Initializable {

    private static final int MAX_COUNT = 3;
    private Stage stage;
    private Scene scene;
    private Parent root;

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;



    @FXML
    private TextField bcategoryTxt;

    @FXML
    private TextField bquantityTxt;

    @FXML
    private TextField btitleTxt;

    @FXML
    private Button clearBtn;

    @FXML
    private TextField issueDateTxt;

    @FXML
    private Button returnBookBtn;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TextField searchBookCode;

    @FXML
    private Button searchStudentBtn;

    @FXML
    private TextField searchStudentID;

    @FXML
    private TextField stNameTxt;

    @FXML
    private TextField stPasswordTxt;

    @FXML
    private TextField stUserNameTxt;

    @FXML
    void reset(ActionEvent event) {
        issueDateTxt.setText("");
        returnDate.setValue(null);
        searchBookCode.setDisable(false);
        searchStudentID.setDisable(false);
        searchBookCode.setText("");
        btitleTxt.setText("");
        bquantityTxt.setText("");
        bcategoryTxt.setText("");
        searchStudentID.setText("");
        stNameTxt.setText("");
        stUserNameTxt.setText("");
        stPasswordTxt.setText("");
    }

    public void updateBook() {
        ResultSet rs;
        PreparedStatement pst;
        ConnectToBook();

        String sql1 = "insert into books (id,booktitle,quantity,category) values (?,?,?,?)";
        try {
  
            pst = conn.prepareStatement(sql1);
            pst.setString(1, searchBookCode.getText());
            pst.setString(2, btitleTxt.getText());
            pst.setString(3, bquantityTxt.getText());
            pst.setString(4, bcategoryTxt.getText());
            // LocalDate localDate = returnDate.getValue();
            // pst.setString(5, localDate.toString());
 

            pst.execute();
            JOptionPane.showMessageDialog(null, "Book returned Successfully");
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void del() {
        ResultSet rs;
        PreparedStatement pst;
        ConnectToStudent();
        try {
            // Connection conn = Connectivity.ConnectDb();
            String bId = searchBookCode.getText();
            String sql = "DELETE FROM issued where bid = '" + bId + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void decrementBook() {
        ResultSet rs;
        PreparedStatement pst3;
        ConnectToStudent();
        // Connectivity connect = new Connectivity();

        String reg = searchStudentID.getText();
        String sql4 = "select *from students where id='" + reg + "'";
        String sql3 = "update students set noIssued = ? where id='" + reg + "' ";

        try {
            // Connection conn = Connectivity.ConnectDb();
            pst3 = conn.prepareStatement(sql4);
            rs = pst3.executeQuery();
            rs.next();
            // int count = rs.getInt(8);
             int count = rs.getInt(6);
            if (count >= 0 && count <= MAX_COUNT) {
                int counter = count;
                counter--;
                PreparedStatement prepstm = conn.prepareStatement(sql3);
                prepstm.setInt(1, counter);
                prepstm.executeUpdate();
                pst3.close();
                rs.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Date Now  ### "To Date Picker"
    public static final LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }

    public void returned() {
        ResultSet rs;
        PreparedStatement pst;
        ConnectToStudent();
        // Connectivity connect = new Connectivity();
        String sql2 = "insert into returned (id,name,username,bid,booktitle) value(?,?,?,?,?)";
        try {
            // Connection conn = Connectivity.ConnectDb();
            pst = conn.prepareStatement(sql2);
            pst.setString(1, searchStudentID.getText());
            pst.setString(2, stNameTxt.getText());
            pst.setString(3, stUserNameTxt.getText());
            pst.setString(4, searchBookCode.getText());
            pst.setString(5, btitleTxt.getText());
         
            // Date dNow = new Date();
            // SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            // String date = ft.format(dNow);
            // pst.setString(6, date);

                     // pst.setString(13, issueDateTxt.getText());
            pst.execute();
            // JOptionPane.showMessageDialog(null, "Book returned succesfully");
            // decrementBook();
            pst.close();
            
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void search(ActionEvent event) {
        returnDate.setValue(NOW_LOCAL_DATE());
        ResultSet rs;
        PreparedStatement pst;
        ConnectToStudent();

        String id = searchBookCode.getText();
        String sql = "select *from issued where bid = '" + id + "' ";

        try {
            // Connection conn = Connectivity.ConnectDb();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                
                searchStudentID.setText(rs.getString(1));
                stNameTxt.setText(rs.getString(2));
                stUserNameTxt.setText(rs.getString(3));            
                btitleTxt.setText(rs.getString(5));
                bquantityTxt.setText(rs.getString(6));
                bcategoryTxt.setText(rs.getString(7));
                issueDateTxt.setText(rs.getString(8));

                rs.close();
                pst.close();
            } else {
                reset(event);
                JOptionPane.showMessageDialog(null, "Wrong book Number");        
            }
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, e);
            System.out.println(e.getMessage());

        }
    }


    @FXML
    void returnBook(ActionEvent event) {
        returned();
        del();
        updateBook();
        reset(event);
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

    @FXML
    void goToReturnedList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReturnList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    

   

        //call the book database
    public void ConnectToBook(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:/book","root","");
            System.out.println("sucessfully connected to book database yayyyy");
        } catch (ClassNotFoundException ex) {
          System.out.println("hikhik");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        //call the student database
        public void ConnectToStudent(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:/student","root","");
                System.out.println("sucessfully connected to student database yayyy");
            } catch (ClassNotFoundException ex) {
              System.out.println("hikhik");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectToBook();
        ConnectToStudent();
    }
    
}
