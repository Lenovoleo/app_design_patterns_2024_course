import java.util.ArrayList;
import java.util.List;

class Book {
    String title;
    String author;
    String ISBN;
    boolean isAvailable;

    Book(String t, String a, String isbn) {
        title = t;
        author = a;
        ISBN = isbn;
        isAvailable = true;
    }
}

class Reader {
    String name;
    List<Book> rentedBooks = new ArrayList<>();

    Reader(String n) {
        name = n;
    }

    void rentBook(Book book) {
        if (book.isAvailable) {
            rentedBooks.add(book);
            book.isAvailable = false;
            System.out.println(name + " rented " + book.title);
        } else {
            System.out.println(book.title + " is not available");
        }
    }

    void returnBook(Book book) {
        if (rentedBooks.remove(book)) {
            book.isAvailable = true;
            System.out.println(name + " returned " + book.title);
        }
    }
}

class Librarian {
    String name;

    Librarian(String n) {
        name = n;
    }

    void manageBooks(List<Book> books) {
        System.out.println(name + " manages the library books.");
    }
}

class Library {
    List<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println("- " + book.title + " by " + book.author +
                    " (" + (book.isAvailable ? "Available" : "Rented") + ")");
        }
    }
}

public class home {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("1984", "George Orwell", "123456789"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "987654321"));

        Reader reader = new Reader("Alice");
        Librarian librarian = new Librarian("Mr. Smith");

        library.displayBooks();

        reader.rentBook(library.books.get(0));
        library.displayBooks();

        reader.returnBook(library.books.get(0));
        library.displayBooks();

        librarian.manageBooks(library.books);
    }
}