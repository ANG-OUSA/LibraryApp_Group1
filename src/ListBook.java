import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListBook {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIManagement();
            }
        });
    }
}

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class GUIManagement extends JFrame implements ActionListener {
    private JTextField titleTextField, authorTextField;
    private DefaultListModel<String> bookListModel;
    private JList<String> bookList;

    private ArrayList<Book> books;

    public GUIManagement() {
        books = new ArrayList<>();

        setTitle("List Book");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Title: ");
        titleTextField = new JTextField(20);
        inputPanel.add(titleLabel);
        inputPanel.add(titleTextField);

        JLabel authorLabel = new JLabel("Author: ");
        authorTextField = new JTextField(20);
        inputPanel.add(authorLabel);
        inputPanel.add(authorTextField);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        panel.add(new JScrollPane(bookList), BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        String title = titleTextField.getText();
        String author = authorTextField.getText();

        if (!title.isEmpty() && !author.isEmpty()) {
            Book book = new Book(title, author);
            books.add(book);
            bookListModel.addElement("Book: "+book.getTitle() + " --- Writer: " + book.getAuthor());

            titleTextField.setText("");
            authorTextField.setText("");
        }
    }
}



