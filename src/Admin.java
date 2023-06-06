
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Admin
{
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty userName;
    private final StringProperty  password;
    private final StringProperty phone;
     
    public Admin()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        userName = new SimpleStringProperty(this, "userName");
        password = new SimpleStringProperty(this, "password");
        phone = new SimpleStringProperty(this, "phone");
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

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }
}