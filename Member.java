class Member {
    String  name;
    int memberId;
    Book [] borrowedBooks;  
    int borrowedBooksCount;
    Member (String name, int memberId){
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new Book[5]; // Assuming a member can borrow up to 5 books
        this.borrowedBooksCount = 0;
    }
    boolean borrowBook(Book b) {
        if (b == null) return false;
        if (borrowedBooksCount < borrowedBooks.length) {
            borrowedBooks[borrowedBooksCount] = b;
            borrowedBooksCount++;
            b.isborrowed = true;
            return true;
        }
        return false;
    }
    }