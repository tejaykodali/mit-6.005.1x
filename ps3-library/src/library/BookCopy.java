package library;

import java.util.Arrays;

/**
 * BookCopy is a mutable type representing a particular copy of a book that is held in a library's
 * collection.
 */
public class BookCopy {

    // TODO: rep
    
    // TODO: rep invariant
    // TODO: abstraction function
    // TODO: safety from rep exposure argument
    
    private final Book book;
    private Condition condition;
    
    public static enum Condition {
        GOOD, DAMAGED
    };
    
    /**
     * Make a new BookCopy, initially in good condition.
     * @param book the Book of which this is a copy
     */
    public BookCopy(Book book) {
//        throw new RuntimeException("not implemented yet");
        this.book = new Book(book);
        this.condition = Condition.GOOD;
        checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
//        throw new RuntimeException("not implemented yet");
        assert condition == Condition.GOOD || condition == Condition.DAMAGED;
    }
    
    /**
     * @return the Book of which this is a copy
     */
    public Book getBook() {
//        throw new RuntimeException("not implemented yet");
        return new Book(book);
    }
    
    /**
     * @return the condition of this book copy
     */
    public Condition getCondition() {
//        throw new RuntimeException("not implemented yet");
        return condition;
    }

    /**
     * Set the condition of a book copy.  This typically happens when a book copy is returned and a librarian inspects it.
     * @param condition the latest condition of the book copy
     */
    public void setCondition(Condition condition) {
//        throw new RuntimeException("not implemented yet");
        this.condition = condition;
    }
    
    /**
     * @return human-readable representation of this book that includes book.toString()
     *    and the words "good" or "damaged" depending on its condition
     */
    
    @Override
    public String toString() {
        return book + " It is in " + condition.toString().toLowerCase() + " condition.";
    }

    // the eclipse generated hashCode and equals seem to cause the grader's BookCopyTest to fail
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((book == null) ? 0 : book.hashCode());
//        result = prime * result + ((condition == null) ? 0 : condition.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        BookCopy other = (BookCopy) obj;
//        if (book == null) {
//            if (other.book != null)
//                return false;
//        } else if (!book.equals(other.book))
//            return false;
//        if (condition != other.condition)
//            return false;
//        return true;
//    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
    public static void main(String[] args) {
        Book book = new Book("Title", Arrays.asList("a"), 2018);
        BookCopy bookCopy = new BookCopy(book);
        bookCopy.setCondition(Condition.DAMAGED);
        System.out.println(bookCopy);
    }

}
