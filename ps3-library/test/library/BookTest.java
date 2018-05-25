package library;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Book ADT.
 */
public class BookTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    private final static String title1 = "title1";
    private final static String title2 = "Title1";
    
    private final static List<String> authors1 = Arrays.asList("a", "b");
    private final static List<String> authors2 = Arrays.asList("b", "a");
    
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals("This Test Is Just An Example", book.getTitle());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
    @SuppressWarnings("unused")
    @Test(expected=AssertionError.class)
    public void testEmptyTitle() {
        Book book = new Book("", Arrays.asList("a"), 2018);
    }
    
    @SuppressWarnings("unused")
    @Test(expected=AssertionError.class)
    public void testEmptyAuthor() {
        Book book = new Book("Title", Arrays.asList(" "), 2018);
    }
    
    @SuppressWarnings("unused")
    @Test(expected=AssertionError.class)
    public void testNegativeYear() {
        Book book = new Book("Title", Arrays.asList("author"), -10);
    }
    
    @Test
    public void testAuthorsDifferentOrder() {
        Book book1 = new Book(title1, authors1, 2018);
        Book book2 = new Book(title1, authors2, 2018);
        
        assertFalse("The order of authors' names matters", book1.getAuthors().equals(book2.getAuthors()));
    }
    
    @Test
    public void testTitleDifferentCase() {
        Book book1 = new Book(title1, authors1, 2018);
        Book book2 = new Book(title2, authors1, 2018);
        
        assertFalse("The case of the titles matters", book1.getTitle().equals(book2.getTitle()));
    }
}
