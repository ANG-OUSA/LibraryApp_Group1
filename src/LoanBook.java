import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LoanBook
{
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty userName;
    private final StringProperty  bid;
    private final StringProperty  booktitle;
    private final StringProperty  quantity;
    private final StringProperty category;
    private final StringProperty dateissued;


     
    public LoanBook()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        userName = new SimpleStringProperty(this, "userName");
        bid = new SimpleStringProperty(this, "bid");
        booktitle = new SimpleStringProperty(this, "booktitle");
        quantity = new SimpleStringProperty(this, "quantity");
        category = new SimpleStringProperty(this, "category");
        dateissued = new SimpleStringProperty(this, "dateissued");
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

    public StringProperty quantityProperty() { return quantity; }
    public String getQuantity() { return quantity.get(); }
    public void setQuantity(String newQuantity) { quantity.set(newQuantity); }

    public StringProperty categoryProperty() { return category; }
    public String getCategory() { return category.get(); }
    public void setCategory(String newCategory) { category.set(newCategory); }

    public StringProperty dateissuedProperty() { return dateissued; }
    public String getDateissued() { return dateissued.get(); }
    public void setDateissued(String newDateissued) { dateissued.set(newDateissued); }
}