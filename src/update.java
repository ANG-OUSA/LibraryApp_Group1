import java.io.IOException;
import java.lang.ref.Cleaner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class update {
   
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
            String sql = "update book set BName = ?, BAuthor = ?, BCategory = ? where  BID=?";
            Connection conn= DriverManager.getConnection(URL, user, password);
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,bookname);
            pst.setString(2,author);
            pst.setString(3, cat);
            pst.setInt(4, ID);
            pst.execute();
            System.out.println("Data INserted");
        

        }catch(SQLException ex){
           System.out.println("fail");
        }
    }

    public void test() throws IOException{
        handleaddbook();
    }  
    public void Clear(ActionEvent event) throws Exception{
        //clear form
        
        bName.setText("");
        bAuthor.setText("");
        bCategory.setText("");
        bID.setText("");

    }
}
