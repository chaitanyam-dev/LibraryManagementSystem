import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        IssueDAO issueDAO = new IssueDAO();

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5. Add Member");
            System.out.println("6. View All Members");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    bookDAO.addBook(title, author, qty);

                    break;

                case 2:

                    System.out.print("Enter book ID to remove: ");
                    int removeId = sc.nextInt();

                    bookDAO.removeBook(removeId);

                    break;

                case 3:

                    System.out.print("Enter book title to search: ");
                    String search = sc.nextLine();

                    bookDAO.searchBook(search);

                    break;

                case 4:

                    bookDAO.viewAllBooks();

                    break;

                case 5:

                    System.out.print("Enter member name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter member email: ");
                    String email = sc.nextLine();

                    memberDAO.addMember(name, email);

                    break;

                case 6:

                    memberDAO.viewAllMembers();

                    break;

                case 7:

                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();

                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();

                    issueDAO.issueBook(bookId, memberId);

                    break;

                case 8:

                    System.out.print("Enter book ID: ");
                    int retBookId = sc.nextInt();

                    System.out.print("Enter member ID: ");
                    int retMemberId = sc.nextInt();

                    issueDAO.returnBook(retBookId, retMemberId);

                    break;

                case 9:

                    System.out.println("Goodbye!");
                    System.exit(0);

                default:

                    System.out.println("Invalid choice!");
            }
        }
    } 
}