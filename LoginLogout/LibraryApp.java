import java.util.Scanner;

public class LibraryApp {
    private static boolean isLoggedIn = false; // Flag to track if user is logged in
    private static String currentUser = ""; // String to store the username of the currently logged in user

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String username, password;

        // Loop until user logs in successfully or chooses to quit
        while (true) {
            // If user is already logged in, show logout option
            if (isLoggedIn) {
                System.out.println("Current user: " + currentUser);
                System.out.print("Enter 'logout' to log out or 'quit' to exit: ");
                String choice = input.nextLine();
                if (choice.equals("logout")) {
                    logout();
                } else if (choice.equals("quit")) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            } else { // If user is not logged in, show login option
                System.out.print("Enter username or 'quit' to exit: ");
                username = input.nextLine();
                if (username.equals("quit")) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter password: ");
                password = input.nextLine();

                // Check if the provided username and password match the database
                if (authenticate(username, password)) {
                    System.out.println("Login successful!");
                    isLoggedIn = true;
                    currentUser = username;
                } else {
                    System.out.println("Invalid username or password.");
                }
            }
        }
    }

    // Method to authenticate the user based on the provided username and password
    private static boolean authenticate(String username, String password) {
        // Code to check the database for matching credentials goes here
        // For simplicity, we are hardcoding one username/password combination
        return username.equals("user123") && password.equals("pass123");
    }

    // Method to log out the current user
    private static void logout() {
        isLoggedIn = false;
        currentUser = "";
        System.out.println("Logout successful.");
    }
}
