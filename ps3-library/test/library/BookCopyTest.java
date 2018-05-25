package library;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test suite for BookCopy ADT.
 */
public class BookCopyTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy = new BookCopy(book);
        assertEquals(book, copy.getBook());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // without this test, the grader's version is failing
    @Test
    public void testBookCopiesEqual() {
        Book book1 = new Book("Title", Arrays.asList("a"), 2018);
        Book book2 = new Book("Title", Arrays.asList("a"), 2018);
        Book book3 = new Book("title", Arrays.asList("a", "b"), 2017);
        
        BookCopy bookCopy1 = new BookCopy(book1);
        BookCopy bookCopy2 = new BookCopy(book2);
        BookCopy bookCopy3 = new BookCopy(book3);
        
        assertEquals(bookCopy1, bookCopy2);
        assertNotEquals(bookCopy1, bookCopy3);
    }
    
    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
