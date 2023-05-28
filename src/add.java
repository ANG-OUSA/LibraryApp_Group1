import java.io.IOException;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Bidi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class add {
    @FXML
    private Button addButton;
    @FXML
    private TextField bCategory;
    @FXML
    private TextField bID;
    @FXML
    private TextField bName;
    @FXML
    private TextField bAuthor;
    @FXML
    private void handleaddbook(){
        String URL = "jdbc:mysql://localhost/book";
        String user = "root";
        String password = "";
        //cat = category
        String bookname = bName.getText();
        int ID = Integer.parseInt(bID.getText());
        String cat = bCategory.getText();
        String author = bAuthor.getText();
        try{
            String sql = "Insert into book(BID,BName,BAuthor,BCategory)values(?,?,?,?)";
            Connection conn= DriverManager.getConnection(URL, user, password);
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,ID);
            pst.setString(2,bookname);
            pst.setString(3, author);
            pst.setString(4, cat);
            pst.execute();
            System.out.println("Data INserted");
        }catch(SQLException ex){
           System.out.println("fail");
        }
    }

    public void clear(){
        //clear form

        bName.setText("");
        bID.setId(null);
        bAuthor.setText("");
        bCategory.setText("");

    }

    public void test() throws IOException{
        handleaddbook();
    }   
}