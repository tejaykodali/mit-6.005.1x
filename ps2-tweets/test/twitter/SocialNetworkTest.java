package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T10:30:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "random123", "waddup fellow twitterati", d3);
    private static final Tweet tweet4 = new Tweet(4, "random345", "nothing much hbu @random123", d1);
    private static final Tweet tweet5 = new Tweet(5, "edx_org", "thanks @random123 @Random345", d2);
    private static final Tweet tweet6 = new Tweet(6, "mit_org", "@random345 @RANDOM123 please contact us at mit@mit.edu", d3);
    private static final Tweet tweet7 = new Tweet(7, "user123", "hi @user123 @bbitdiddle @alyssa", d3);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testGuessFollowsNoMentions() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3));
        
        assertEquals("expected empty result map value", new HashSet<>(), followsGraph.get("random123"));
    }
    
    @Test
    public void testGuessFollowsMultipleMentions() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4, tweet5, tweet6));
        
        assertEquals("expect multiple keys", 3, followsGraph.size());
        assertEquals("expected result map key", makeSet("random345", "edx_org", "mit_org"), followsGraph.keySet());
        assertEquals("expected result map value", makeSet("random123"), followsGraph.get("random345"));
        assertEquals("expected result map value", makeSet("random123", "random345"), followsGraph.get("edx_org"));
        assertEquals("expected result map value", makeSet("random123", "random345"), followsGraph.get("mit_org"));
    }
    
    @Test
    public void testGuessFollowsUsersCantFollowThemselves() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet7));
        
        assertEquals("expect empty result map value", makeSet("alyssa", "bbitdiddle"), followsGraph.get("user123"));
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersMultipleUsers() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals("expected result list", Arrays.asList("random123", "random345", "bbitdiddle", "alyssa"), influencers);
    }
    
    
    private static Set<String> makeSet(String... elements) {
        Set<String> set = new HashSet<>();
        for (String x : elements) {
            set.add(x);
        }
        return set;
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
