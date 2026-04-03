public interface Borrowable {
    boolean borrow();   // mark item as borrowed
    boolean returnItem(); // mark item as returned
    boolean isBorrowed(); // check borrow status
}
