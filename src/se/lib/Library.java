package se.lib;

public class Library {
    static final int RENEWAL_REQUESTS = 2;
    static final int NORMAL_REQUESTS = 5;
    static final int BOOK_WEAR = 50;
    static final int BORROWS_PER_USER = 10;

    private Users users = new Users();
    private Books books = new Books();
    private Requests requests = new Requests();

    public void addUser(String username) {
        users.add(username);
    }

    public void newRequest(String isbn, String author, String title) {
        BookDetails userRequestDetails = new BookDetails(isbn, author, title);
        BookDetails bookToBuy = requests.addUserRequest(userRequestDetails);

        if (bookToBuy != null) books.buyBook(bookToBuy);
    }

    public void bookBorrow(String username, String bookname) throws UserNotFoundException, BookNotFoundException, BookAlreadyBorrowedException, TooManyBorrowsException {
        User user = users.getUserByName(username);
        Book book = books.getBookByName(bookname);
        book.borrowing(user);
    }

    public void bookReturn(String bookname) throws BookNotFoundException {
        Book book = books.getBookByName(bookname);
        BookDetails libRequestDetails = books.returnBook(book);
        if (libRequestDetails != null) requests.addLibraryRequest(libRequestDetails);
    }

    public String getBooks() {
        return books.getBookList();
    }

    public String getBookCurrentUser(String bookname) throws BookNotFoundException {
        return books.getBookByName(bookname).getCurrentUser().getName();
    }

    public int getBookBorrowsIndex(String bookname) throws BookNotFoundException {
        return books.getBookByName(bookname).getBorrowsIndex();
    }

    public int getRequestCurrentIndex(String bookname) throws RequestNotFoundException {
        return requests.getRequestByName(bookname).getRequestsIndex();
    }

}
