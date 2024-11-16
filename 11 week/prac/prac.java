import java.util.ArrayList;
import java.util.List;

class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    Book(String t, String a, String i) {
        title = t;
        author = a;
        isbn = i;
        isAvailable = true;
    }

    void markAsLoaned() {
        if (!isAvailable) throw new RuntimeException("Book is already loaned.");
        isAvailable = false;
    }

    void markAsAvailable() {
        isAvailable = true;
    }

    void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn +
                ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}

class User {
    int id;
    String name;

    User(int userId, String userName) {
        id = userId;
        name = userName;
    }

    void displayInfo() {
        System.out.println("User ID: " + id + ", Name: " + name);
    }

    String getName() {
        return name;
    }
}

class Reader extends User {
    List<Book> borrowedBooks = new ArrayList<>();

    Reader(int userId, String userName) {
        super(userId, userName);
    }

    void borrowBook(Book book) {
        if (!book.isAvailable) throw new RuntimeException("Book is not available.");
        borrowedBooks.add(book);
        book.markAsLoaned();
        System.out.println(name + " borrowed \"" + book.title + "\"");
    }

    void returnBook(Book book) {
        if (!borrowedBooks.remove(book))
            throw new RuntimeException("Book not found in borrowed list.");
        book.markAsAvailable();
        System.out.println(name + " returned \"" + book.title + "\"");
    }

    void displayBorrowedBooks() {
        System.out.println(name + " has borrowed the following books:");
        for (Book book : borrowedBooks) {
            System.out.println("- " + book.title);
        }
    }
}

class Librarian extends User {
    Librarian(int userId, String userName) {
        super(userId, userName);
    }

    void addBook(List<Book> catalog, Book book) {
        catalog.add(book);
        System.out.println("Book \"" + book.title + "\" added to the library by " + name);
    }

    void removeBook(List<Book> catalog, String isbn) {
        catalog.removeIf(book -> book.isbn.equals(isbn));
        System.out.println("Book with ISBN \"" + isbn + "\" removed by " + name);
    }
}

class Loan {
    Book book;
    Reader reader;
    String loanDate;
    String returnDate;

    Loan(Book b, Reader r, String ld, String rd) {
        book = b;
        reader = r;
        loanDate = ld;
        returnDate = rd;
    }

    void issueLoan() {
        book.markAsLoaned();
        System.out.println("Loan issued: \"" + book.title + "\" to " + reader.getName() + " on " + loanDate);
    }

    void completeLoan() {
        book.markAsAvailable();
        System.out.println("Loan completed: \"" + book.title + "\" returned by " + reader.getName() + " on " + returnDate);
    }
}

class Library {
    List<Book> catalog = new ArrayList<>();

    void searchBookByTitle(String title) {
        for (Book book : catalog) {
            if (book.title.equals(title)) {
                book.displayInfo();
                return;
            }
        }
        System.out.println("Book with title \"" + title + "\" not found.");
    }

    void displayCatalog() {
        System.out.println("Library Catalog:");
        for (Book book : catalog) {
            book.displayInfo();
        }
    }
}

public class prac {
    public static void main(String[] args) {
        try {
            Library library = new Library();
            library.catalog.add(new Book("1984", "George Orwell", "12345"));
            library.catalog.add(new Book("To Kill a Mockingbird", "Harper Lee", "67890"));

            Librarian librarian = new Librarian(1, "John Smith");
            Reader reader = new Reader(2, "Alice Johnson");

            librarian.addBook(library.catalog, new Book("Brave New World", "Aldous Huxley", "11223"));
            library.displayCatalog();

            reader.borrowBook(library.catalog.get(0));
            reader.displayBorrowedBooks();

            reader.returnBook(library.catalog.get(0));
            library.displayCatalog();

            Loan loan = new Loan(library.catalog.get(1), reader, "2024-11-16", "2024-11-30");
            loan.issueLoan();
            loan.completeLoan();

            library.searchBookByTitle("1984");
        } catch (RuntimeException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}