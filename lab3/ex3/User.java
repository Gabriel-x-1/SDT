package ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User in social network - acts as both Subject (Observable) and Observer
 */
public class User {
    private final String username;
    private final List<User> followers = new ArrayList<>();
    private final Random random = new Random();

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    // Observer pattern: User follows another user
    public void follow(User user) {
        if (user != this && !user.followers.contains(this)) {
            user.followers.add(this);
            System.out.println(username + " is now following " + user.getUsername());
        }
    }

    public void unfollow(User user) {
        user.followers.remove(this);
        System.out.println(username + " unfollowed " + user.getUsername());
    }

    // Post status and notify all followers
    public void postStatus(String status) {
        System.out.println("\n[" + username + " posted: '" + status + "']");
        notifyFollowers(status);
    }

    private void notifyFollowers(String status) {
        for (User follower : followers) {
            follower.receiveNotification(this.username, status);
        }
    }

    // This method is called when a followed user posts something
    private void receiveNotification(String posterUsername, String status) {
        System.out.println(username + " was notified that " + posterUsername + 
                          " posted the status '" + status + "'");
    }

    public int getFollowerCount() {
        return followers.size();
    }

    public List<User> getFollowers() {
        return new ArrayList<>(followers);
    }

    @Override
    public String toString() {
        return username + " (" + followers.size() + " followers)";
    }
}

