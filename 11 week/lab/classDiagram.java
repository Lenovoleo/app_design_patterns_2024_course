import java.util.ArrayList;

class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean isAvailable;

    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public void markAsLoaned() {
        this.isAvailable = false;
        System.out.println("The book \"" + title + "\" is now loaned.");
    }

    public void markAsAvailable() {
        this.isAvailable = true;
        System.out.println("The book \"" + title + "\" is now available.");
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getTitle() {
        return title;
    }
}

class Reader {
    private int id;
    private String name;
    private String email;

    public Reader(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.markAsLoaned();
            System.out.println(name + " has borrowed the book \"" + book.getTitle() + "\".");
        } else {
            System.out.println("Sorry, the book \"" + book.getTitle() + "\" is not available.");
        }
    }

    public void returnBook(Book book) {
        book.markAsAvailable();
        System.out.println(name + " has returned the book \"" + book.getTitle() + "\".");
    }
}

class Librarian {
    private int id;
    private String name;
    private String position;

    public Librarian(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public void addBook(ArrayList<Book> library, Book book) {
        library.add(book);
        System.out.println("The book \"" + book.getTitle() + "\" has been added to the library.");
    }

    public void removeBook(ArrayList<Book> library, Book book) {
        if (library.contains(book)) {
            library.remove(book);
            System.out.println("The book \"" + book.getTitle() + "\" has been removed from the library.");
        } else {
            System.out.println("The book \"" + book.getTitle() + "\" is not in the library.");
        }
    }
}

public class classDiagram {
    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<>();

        Librarian librarian = new Librarian(1, "Alice", "Chief Librarian");

        Book book1 = new Book("1984", "George Orwell", 12345);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 67890);

        librarian.addBook(library, book1);
        librarian.addBook(library, book2);

        Reader reader = new Reader(1, "Leo", "leo@mail.ru");

        reader.borrowBook(book1);

        reader.borrowBook(book1);

        reader.returnBook(book1);

        librarian.removeBook(library, book2);
    }
}
