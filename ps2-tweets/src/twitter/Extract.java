package twitter;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.time.Instant;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
//        throw new RuntimeException("not implemented");
        Instant start = tweets.get(0).getTimestamp();
        Instant end = tweets.get(0).getTimestamp();
        
        for (Tweet t : tweets) {
            Instant timestamp = t.getTimestamp();
            if (timestamp.isBefore(start)) {
                start = timestamp;
            } else if (timestamp.isAfter(end)) {
                end = timestamp;
            }
        }
        
        return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//        throw new RuntimeException("not implemented");
        Set<String> mentions = new HashSet<>();
        for (Tweet t : tweets) {
            String text = t.getText();
//            System.out.println("text: " + text);
            String[] words = text.split("[ ]+");
            for (String w : words) {
//                System.out.println(w);
                if (w.charAt(0) == '@' && w.matches("[@a-zA-Z0-9_\\-]+")) {
                    w = w.substring(1);
//                    System.out.println("w: " + w);
                    mentions.add(w.toLowerCase());
                }
            }
        }
        return mentions;
    }
    
    public static void main(String[] args) {
        Instant d3 = Instant.parse("2016-02-17T10:30:00Z");
        Tweet tweet6 = new Tweet(6, "mit_org", "@random345 @RANDOM123 please contact us at mit@mit.edu", d3);
        
        List<Tweet> tweets = Arrays.asList(tweet6);
        
        Extract.getMentionedUsers(tweets);
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
