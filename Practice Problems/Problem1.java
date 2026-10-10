import java.util.List;

public class Problem1 {
    public static class BookRecord {
        private String isbn;
        private String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
    }

    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookRecord midRecord = catalog.get(mid);
            int cmp = midRecord.getIsbn().compareTo(targetIsbn);

            if (cmp == 0) {
                return midRecord.getTitle();
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }
}