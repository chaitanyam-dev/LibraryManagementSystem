import java.sql.*;

public class IssueDAO {
    private Connection con;

    public IssueDAO() {
        con = DatabaseConnection.getConnection();
    }

    public void issueBook(int bookId, int memberId) {
        try {
            String checkBook ="SELECT * FROM books WHERE id = ?";

            PreparedStatement bookPs = con.prepareStatement(checkBook);

            bookPs.setInt(1, bookId);

            ResultSet bookRs = bookPs.executeQuery();

            if (!bookRs.next()) {

                System.out.println("Book not found!");
                return;
            }
            String checkMember =
            "SELECT * FROM members WHERE id = ?";

            PreparedStatement memberPs = con.prepareStatement(checkMember);

            memberPs.setInt(1, memberId);

            ResultSet memberRs = memberPs.executeQuery();

            if (!memberRs.next()) {

                System.out.println("Member not found!");
                return;
            }
            String checkQuery ="SELECT quantity FROM books WHERE id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);

            checkPs.setInt(1, bookId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");

                if (quantity <= 0) {
                    System.out.println("Book not available!");
                    return;
                }
            }

            String query = "INSERT INTO issued_books (book_id, member_id, issue_date) VALUES (?, ?, CURDATE())";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);

            ps.executeUpdate();

            // Reduce quantity
            String update = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";

            PreparedStatement ps2 = con.prepareStatement(update);

            ps2.setInt(1, bookId);

            ps2.executeUpdate();

            System.out.println("Book issued successfully!");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public void returnBook(int bookId, int memberId) {

        try {

            String update1 =
            "UPDATE issued_books " +
            "SET return_date = CURDATE() " +
            "WHERE id = (" +
            "SELECT id FROM (" +
            "SELECT id FROM issued_books " +
            "WHERE book_id = ? " +
            "AND member_id = ? " +
            "AND return_date IS NULL " +
            "LIMIT 1" +
            ") temp)";

            PreparedStatement ps =
            con.prepareStatement(update1);

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);

            int rowsAffected = ps.executeUpdate();


            String update2 = "UPDATE books SET quantity = quantity + 1 WHERE id = ?";

            if (rowsAffected > 0) {

            PreparedStatement ps2 =
            con.prepareStatement(update2);

            ps2.setInt(1, bookId);

            ps2.executeUpdate();

            System.out.println("Book returned successfully!");

            } else {

            System.out.println("No active issue record found!");
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}