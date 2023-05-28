
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Student 
{
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty userName;
    private final StringProperty  password;
     
    public Student()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        userName = new SimpleStringProperty(this, "userName");
        password = new SimpleStringProperty(this, "password");
    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }

    public StringProperty userNameProperty() { return userName; }
    public String getUserName() { return userName.get(); }
    public void setUserName(String newUserName) { userName.set(newUserName); }
    
    public StringProperty passwordProperty() { return password; }
    public String getPassword() { return password.get(); }
    public void setPassword(String newPassword) { password.set(newPassword); }
}