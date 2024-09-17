
public class homework1 {

    public static void main(String[] args) {
        
        Book book1 = new Book("Book One", "Author One", 12345, 5);
        Book book2 = new Book("Book Two", "Author Two", 23456, 3);

       
        Reader reader1 = new Reader("John Doe", 1001);
        Reader reader2 = new Reader("Jane Smith", 1002);

        
        Library library = new Library(new Book[10], new Reader[10]);
        library.addBook(book1);
        library.addBook(book2);

        
        library.registerReader(reader1);
        library.registerReader(reader2);

        
        library.issueBook(0, 1001); 
        library.issueBook(0, 1002); 

        
        library.returnBook(0, 1001); 
    }
}

class Book {
    protected String name;
    protected String author;
    protected int isbn;
    protected int num_of_copies;
    protected int num_of_issued_copies = 0;

    Book(String name, String author, int isbn, int num_of_copies) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.num_of_copies = num_of_copies;
    }


    public boolean isAvailable() {
        return num_of_issued_copies < num_of_copies;
    }

    public void issue() {
        if (isAvailable()) {
            num_of_issued_copies++;
        } else {
            System.out.println("No available copies of this book.");
        }
    }

 
    public void returnBook() {
        if (num_of_issued_copies > 0) {
            num_of_issued_copies--;
        } else {
            System.out.println("No issued copies to return.");
        }
    }
}

class Reader {
    protected String name;
    protected int id_of_reader;

    Reader(String name, int id_of_reader) {
        this.name = name;
        this.id_of_reader = id_of_reader;
    }
}

class Library {
    protected Book[] list_of_books;
    protected int size_list_books = 0;
    protected Reader[] list_of_readers;
    protected int size_list_readers = 0;


    Library(Book[] list_of_books, Reader[] list_of_readers) {
        this.list_of_books = list_of_books;
        this.list_of_readers = list_of_readers;
    }

  
    public void addBook(Book book) {
        if (size_list_books < list_of_books.length) {
            list_of_books[size_list_books] = book;
            size_list_books++;
        } else {
            System.out.println("Library is full.");
        }
    }


    public void dropBook(int index_book) {
        if (index_book < 0 || index_book >= size_list_books) {
            System.out.println("Invalid index.");
            return;
        }
        Book[] arr = new Book[list_of_books.length - 1];
        int index = 0;
        for (int i = 0; i < list_of_books.length; i++) {
            if (i != index_book) {
                arr[index++] = list_of_books[i];
            }
        }
        list_of_books = arr;
        size_list_books--;
    }


    public void registerReader(Reader reader) {
        if (size_list_readers < list_of_readers.length) {
            list_of_readers[size_list_readers] = reader;
            size_list_readers++;
        } else {
            System.out.println("Library has reached the maximum number of readers.");
        }
    }


    public void issueBook(int index_book, int reader_id) {
        Book book = list_of_books[index_book];
        Reader reader = findReaderById(reader_id);

        if (reader != null && book != null) {
            if (book.isAvailable()) {
                book.issue();
                System.out.println("Book issued to " + reader.name);
            } else {
                System.out.println("No available copies of " + book.name);
            }
        }
    }


    public void returnBook(int index_book, int reader_id) {
        Book book = list_of_books[index_book];
        Reader reader = findReaderById(reader_id);

        if (reader != null && book != null) {
            book.returnBook();
            System.out.println("Book returned by " + reader.name);
        }
    }


    private Reader findReaderById(int reader_id) {
        for (int i = 0; i < size_list_readers; i++) {
            if (list_of_readers[i].id_of_reader == reader_id) {
                return list_of_readers[i];
            }
        }
        System.out.println("Reader not found.");
        return null;
    }
}
