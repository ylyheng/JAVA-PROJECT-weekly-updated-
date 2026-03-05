public abstract class LibraryItem {
    private String title;
    private String author;
    private int publicationYear;

    public LibraryItem(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    // Getters & Setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int year) { this.publicationYear = year; }

    // Abstract method — every subclass MUST implement this
    public abstract String getItemType();

    @Override
    public String toString() {
        return getItemType() + "{title='" + title + "', author='" + author +
               "', year=" + publicationYear + "}";
    }
}