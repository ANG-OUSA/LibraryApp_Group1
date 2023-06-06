
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {

    private final StringProperty id;
    private final StringProperty bookTitle;
    private final StringProperty quantity;
    private final StringProperty  category;
    private final StringProperty  mydate;

    public Book(){
        id = new SimpleStringProperty(this, "id");
        bookTitle = new SimpleStringProperty(this, "bookTitle");
        quantity = new SimpleStringProperty(this,"quantity");
        category = new SimpleStringProperty(this,"category");
        mydate = new SimpleStringProperty(this,"mydate");   
    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty bookTitleProperty() { return bookTitle; }
    public String getBookTitle() { return bookTitle.get(); }
    public void setBookTitle(String newBookTitle) { bookTitle.set(newBookTitle); }

    public StringProperty quantityProperty() { return quantity; }
    public String getQuantity() { return quantity.get(); }
    public void setQuantity(String newQuantity) { quantity.set(newQuantity); }

    public StringProperty categoryProperty() { return category; }
    public String getCategory() { return category.get(); }
    public void setCategory(String newCategory) { category.set(newCategory); }

    public StringProperty myDateProperty() {return mydate; }
    public String getMyDate() { 
        return mydate.get(); }
    public void setMyDate(String newMyDate) { mydate.set(newMyDate); }


}
