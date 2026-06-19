import java.sql.*;

public class MemberDAO {
    private Connection con;

    public MemberDAO() {
        con = DatabaseConnection.getConnection();
    }

    public void addMember(String name, String email) {
        try {
            String query = "INSERT INTO members (name, email) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewAllMembers() {
        try {
            String query = "SELECT * FROM members";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Member m = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                m.display();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}