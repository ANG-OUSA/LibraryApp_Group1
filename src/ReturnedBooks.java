import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ReturnedBooks
{
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty userName;
    private final StringProperty  bid;
    private final StringProperty  booktitle;
    private final StringProperty  dateReturn;

     
    public ReturnedBooks()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        userName = new SimpleStringProperty(this, "userName");
        bid = new SimpleStringProperty(this, "bid");
        booktitle = new SimpleStringProperty(this, "booktitle");
        dateReturn = new SimpleStringProperty(this, "dateReturn");
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
    
    public StringProperty bidProperty() { return bid; }
    public String getbid() { return bid.get(); }
    public void setbid(String newbid) { bid.set(newbid); }

    public StringProperty bookTitleProperty() { return booktitle; }
    public String getBookTitle() { return booktitle.get(); }
    public void setBookTitle(String newBookTitle) { booktitle.set(newBookTitle); }

    public StringProperty dateReturnProperty() { return dateReturn; }
    public String getDateReturn() { return dateReturn.get(); }
    public void setDateReturn(String newDateReturn) { dateReturn.set(newDateReturn); }
}