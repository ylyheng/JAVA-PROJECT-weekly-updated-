class Book{
    String title;
    String author;
    int publicationYear;
    boolean isborrowed;
    int publishedYearSnapshot;
    Book(String title, String author, int publicationYear){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isborrowed = false;
        this.publishedYearSnapshot = publicationYear;
    }   
}