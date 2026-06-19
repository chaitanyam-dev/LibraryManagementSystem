# Library Management System

A console-based Library Management System developed using Java, JDBC, and MySQL. The application allows users to manage books, members, and book issue/return operations through a menu-driven interface.

## Features

* Add new books to the library database
* Remove books from the library
* Search books by title
* View all available books
* Add new library members
* View all registered members
* Issue books to members
* Return issued books
* Automatic book quantity management
* Input validation and error handling

## Technologies Used

* Java
* JDBC (Java Database Connectivity)
* MySQL
* Object-Oriented Programming (OOP)
* DAO (Data Access Object) Pattern
* Git & GitHub
* VS Code

## Database Tables

### books

Stores book information:

* id
* title
* author
* quantity

### members

Stores member information:

* id
* name
* email

### issued_books

Stores issued book records:

* id
* book_id
* member_id
* issue_date
* return_date

## Project Structure

* Book.java
* Member.java
* DatabaseConnection.java
* BookDAO.java
* MemberDAO.java
* IssueDAO.java
* Main.java

## Key Concepts Implemented

* Object-Oriented Programming
* Encapsulation
* JDBC Connectivity
* PreparedStatement
* ResultSet
* CRUD Operations
* Exception Handling
* Foreign Key Constraints
* DAO Design Pattern

## How to Run

1. Create the MySQL database and tables.
2. Configure database credentials in `DatabaseConnection.java`.
3. Compile the project:

```bash
javac -cp ".;mysql-connector-j-9.7.0.jar" *.java
```

4. Run the application:

```bash
java -cp ".;mysql-connector-j-9.7.0.jar" Main
```
## Sample Menu

Library Management System

1. Add Book
2. Remove Book
3. Search Book
4. View All Books
5. Add Member
6. View All Members
7. Issue Book
8. Return Book
9. Exit

## What I Learned

- JDBC connectivity with MySQL
- CRUD operations using PreparedStatement and ResultSet
- Object-Oriented Programming concepts
- DAO Design Pattern
- Exception handling and validation
- Git and GitHub version control

## Author

Chaitanya Malik
