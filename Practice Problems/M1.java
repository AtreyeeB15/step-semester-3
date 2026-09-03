class Book {
    private String title;
    private String isbn;
    private boolean catalogued;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = (isbn == null || isbn.isEmpty()) ? "PENDING" : isbn;
        this.catalogued = true;
    }

    public Book(String title) {
        this(title, "PENDING");
    }

    public void print() {
        System.out.println(title + " | " + isbn + " | Catalogued: " + catalogued);
    }
}

public class M1 {
    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            new Book(titles[i], isbns[i]).print();
        }
    }
}
