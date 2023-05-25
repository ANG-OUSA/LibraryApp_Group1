import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class Main extends Application
{
    Button Librarian, Student, Return, LLogin;
    Stage Window;
    Scene Login, LoginL, LoginS;

    @Override
    public void start(Stage primarStage) throws Exception 
    {
        Window = primarStage;
        Label Lable1 = new Label("Welcome to Group 1 Library App\nPlease choose your login method");
        Label LabelSL = new Label("This is Student Login Screen");
        Label LabelLLName = new Label("Username:");
        Label LabelLLPW = new Label("Password:");

        TextField LLName = new TextField();
        LLName.setPromptText("Name");
        TextField LLPW = new TextField();
        LLPW.setPromptText("Password");

        Librarian = new Button("Librarian Login");
        Librarian.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) 
            {
                Window.setScene(LoginL);
            }
            
        });

        LLogin = new Button("Log in");
        LLogin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) 
            {
                System.out.println(LLName.getText());
            }
            
        });

        Student = new Button("Student Login"); 
        Student.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) 
            {
                Window.setScene(LoginS);
            }
            
        });

        Return = new Button("Return");
        Return.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) 
            {
                Window.setScene(Login);
            }
            
        });

        VBox Layout1 = new VBox(20);
        Layout1.getChildren().addAll(Lable1, Librarian, Student);
        Layout1.setAlignment(Pos.CENTER);
        Login = new Scene(Layout1, 600, 300);

        VBox LayoutSL = new VBox(20);
        LayoutSL.getChildren().add(LabelSL);
        LayoutSL.setAlignment(Pos.CENTER);
        LoginS = new Scene(LayoutSL, 600, 300);

        GridPane LayoutLL = new GridPane();
        LayoutLL.setPadding(new Insets(100, 200, 100, 200));
        GridPane.setConstraints(LabelLLName, 0, 0);
        GridPane.setConstraints(LLName, 1, 0);
        GridPane.setConstraints(LabelLLPW, 0, 1);
        GridPane.setConstraints(LLPW, 1, 1);
        GridPane.setConstraints(LLogin, 1, 2);
        GridPane.setConstraints(Return, 1, 3);
        LayoutLL.getChildren().addAll(LabelLLName, LLName, LabelLLPW, LLPW, LLogin, Return);
        LoginL = new Scene(LayoutLL, 600, 300);

        Window.setTitle("Library App");
        Window.setScene(Login);
        Window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) 
            {
                
                Boolean Answer = ConfirmBox.display("Exit", "Sure you want to exit?");
                if (Answer)
                    Window.close();
                event.consume();
            }
            
        });
        Window.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}