package ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Social Network simulation demonstrating Observer pattern
 * Users can follow other users and get notified when they post statuses
 */
public class Main {
    private static final String[] SAMPLE_STATUSES = {
        "made omelette, looks delicious",
        "just finished a great book",
        "loving this sunny weather",
        "coffee time!",
        "working on a new project",
        "heading to the gym",
        "watching my favorite show",
        "had an amazing dinner",
        "feeling productive today",
        "can't wait for the weekend"
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Social Network Simulation (Observer Pattern) ===\n");

        // Create users
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");
        User diana = new User("Diana");
        User eve = new User("Eve");

        List<User> allUsers = List.of(alice, bob, charlie, diana, eve);

        // Setup follower relationships
        System.out.println("--- Setting up follower relationships ---");
        bob.follow(alice);
        charlie.follow(alice);
        diana.follow(alice);
        
        alice.follow(bob);
        charlie.follow(bob);
        
        alice.follow(charlie);
        bob.follow(charlie);
        eve.follow(charlie);
        
        diana.follow(eve);
        bob.follow(eve);

        System.out.println("\n--- Network Status ---");
        for (User user : allUsers) {
            System.out.println(user);
        }

        System.out.println("\n--- Users start posting statuses randomly ---\n");

        // Simulate random posts
        Random random = new Random();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        final int[] postCount = {0};
        final int MAX_POSTS = 15;

        scheduler.scheduleAtFixedRate(() -> {
            if (postCount[0] >= MAX_POSTS) {
                scheduler.shutdown();
                return;
            }

            User randomUser = allUsers.get(random.nextInt(allUsers.size()));
            String randomStatus = SAMPLE_STATUSES[random.nextInt(SAMPLE_STATUSES.length)];
            
            randomUser.postStatus(randomStatus);
            postCount[0]++;

        }, 0, 800, TimeUnit.MILLISECONDS);

        // Wait for all posts to complete
        scheduler.awaitTermination(20, TimeUnit.SECONDS);

        System.out.println("\n=== Simulation Complete ===");
        System.out.println("Total posts: " + postCount[0]);
    }
}

