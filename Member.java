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
        if (b.isBorrowed()) return false;
        if (borrowedBooksCount < borrowedBooks.length) {
            borrowedBooks[borrowedBooksCount] = b;
            borrowedBooksCount++;
            return b.borrow();
        }
        return false;
    }

    boolean returnBook(Book b) {
        if (b == null) return false;
        for (int i = 0; i < borrowedBooksCount; i++) {
            if (borrowedBooks[i] == b) {
                borrowedBooks[i] = borrowedBooks[borrowedBooksCount - 1];
                borrowedBooks[borrowedBooksCount - 1] = null;
                borrowedBooksCount--;
                return b.returnItem();
            }
        }
        return false;
    }
    }