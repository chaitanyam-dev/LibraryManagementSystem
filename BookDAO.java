import java.sql.*;
import java.util.*;

public class BookDAO {
    private Connection con;

    public BookDAO() {
        con = DatabaseConnection.getConnection();
    }

    public void addBook(String title, String author, int quantity) {
        try {
            String query = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removeBook(int id) {
        try {
            String query = "DELETE FROM books WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book removed successfully!");
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchBook(String title) {
        try {
            String query = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                Book b = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("quantity")
                );

                b.display();
            }

            if (!found) {
                System.out.println("Book not found!");
            }
        } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewAllBooks() {
        try {
            String query = "SELECT * FROM books";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            boolean found = false;
            while (rs.next()) {
                found = true;

                Book b = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                );
                b.display();
            }
            if (!found) {
                System.out.println("No books available.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}