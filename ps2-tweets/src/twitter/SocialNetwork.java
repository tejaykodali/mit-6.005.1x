package twitter;

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
//        throw new RuntimeException("not implemented");
        Map<String, Set<String>> followsGraph = new HashMap<String, Set<String>>();
        
        for (Tweet t : tweets) {
            String author = t.getAuthor().toLowerCase();
            Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(t));
            if (followsGraph.containsKey(author)) {
                Set<String> follows = followsGraph.get(author);
                for (String user : follows) {
                    if (!user.equals(author)) {
                        // user can't follow himself
                        mentionedUsers.add(user);
                    }
                }
                followsGraph.put(author, mentionedUsers);
            } else {
                Set<String> mentionedUsersExcludingAuthor = new HashSet<>();
                for (String user : mentionedUsers) {
                    if (!user.equals(author)) {
                        mentionedUsersExcludingAuthor.add(user);
                    }
                }
                followsGraph.put(author, mentionedUsersExcludingAuthor);
            }
        }
        return followsGraph;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
//        throw new RuntimeException("not implemented");
        Map<String, Integer> userFollowers = new HashMap<>();
        
        for (String key : followsGraph.keySet()) {
            for (String value : followsGraph.get(key)) {
                int followersCount = userFollowers.getOrDefault(value, 0);
                userFollowers.put(value, followersCount + 1);
            }
        }
        
        TreeMap<String, Integer> usersSortedByFollowers = sortMap(userFollowers);
        List<String> influencers = new ArrayList<String>();
        influencers.addAll(usersSortedByFollowers.keySet());
        return influencers;
    }
    
    //https://www.programcreek.com/2013/03/java-sort-map-by-value/
    private static TreeMap<String, Integer> sortMap(Map<String, Integer> map) {
        Comparator<String> comparator = new ValueComparator(map);
        TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
        result.putAll(map);
        return result;
    }
    
    private static class ValueComparator implements Comparator<String> {
        HashMap<String, Integer> map = new HashMap<>();
        
        public ValueComparator(Map<String, Integer> map) {
            this.map.putAll(map);
        }
        
        @Override
        public int compare(String s1, String s2) {
            if (map.get(s1) > map.get(s2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
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
    private static final Tweet tweet7 = new Tweet(7, "user123", "hi @user123 @alyssa @bbitdiddle", d3);
    
    public static void main(String[] args) {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7));
        
        System.out.println(followsGraph);
        
        Tweet t = tweet7;
        
        String author = t.getAuthor().toLowerCase();
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(t));
        
        for (String user : mentionedUsers) {
            System.out.println("'" + user + "'");
            System.out.println("'" + author + "'");
            System.out.println(user.equals(author));
        }
    }
}
