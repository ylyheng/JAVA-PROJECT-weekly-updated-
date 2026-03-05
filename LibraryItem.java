abstract class LibraryItem {
    String title;
    String author;
    int publicationYear;
    int publishedYearSnapshot;

    LibraryItem(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publishedYearSnapshot = publicationYear;
    }

    abstract String getItemType();
}
