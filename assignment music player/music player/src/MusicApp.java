import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MusicApp {
    private List<Song> songs;

    // Constants for validation limits
    private static final int MIN_ARTIST_NAME_LENGTH = 1;
    private static final int MAX_ARTIST_NAME_LENGTH = 100;
    private static final int MIN_SONG_TITLE_LENGTH = 1;
    private static final int MAX_SONG_TITLE_LENGTH = 200;
    private static final long MAX_PLAY_COUNT = 5000000000L;

    public MusicApp() {
        songs = new ArrayList<>();
    }

    // method for validating string length
    private boolean isValidStringLength(String str, int minLength, int maxLength) {
        return str != null && !str.isEmpty() && str.length() >= minLength && str.length() <= maxLength;
    }

    // Validate artist name
    private boolean isValidArtistName(String artist) {
        return isValidStringLength(artist, MIN_ARTIST_NAME_LENGTH, MAX_ARTIST_NAME_LENGTH);
    }

    // Validate song title
    private boolean isValidSongTitle(String title) {
        return isValidStringLength(title, MIN_SONG_TITLE_LENGTH, MAX_SONG_TITLE_LENGTH);
    }

    // Validate play count
    private boolean isValidPlayCount(long playCount) {
        return playCount >= 0 && playCount <= MAX_PLAY_COUNT;
    }

    // method for validating song details
    private boolean validateSongDetails(String title, String artist, long playCount, StringBuilder errorMessage) {
        boolean valid = true;
        if (!isValidSongTitle(title)) {
            errorMessage.append("Invalid song title. Title must be between 1 and 200 characters.\n");
            valid = false;
        }
        if (!isValidArtistName(artist)) {
            errorMessage.append("Invalid artist name. Artist name must be between 1 and 100 characters.\n");
            valid = false;
        }
        if (!isValidPlayCount(playCount)) {
            errorMessage.append("Invalid play count. Play count must be an integer between 0 and 5 billion.\n");
            valid = false;
        }
        return valid;
    }

    // Add a new song to the list of songs
    public void addSong(String title, String artist, long playCount) {
        boolean valid = true;
        StringBuilder errorMessage = new StringBuilder();
        valid = validateSongDetails(title, artist, playCount, errorMessage);
        if (valid) {
            Song newSong = new Song(title, artist, playCount);
            songs.add(newSong);
            System.out.println("Song added successfully!");
        } else {
            System.out.println(errorMessage.toString());
        }
    }

    // Remove a song from the list based on user title input
    public void removeSong(String title) {
        Iterator<Song> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                System.out.println("Song removed: " + title);
                return;
            }
        }
        System.out.println("Song not found: " + title);
    }

    // Print a list of all songs
    public void printAllSongs() {
        if (songs.isEmpty()) {
            System.out.println("No songs available.");
        } else {
            for (Song song : songs) {
                System.out.println(song);
            }
        }
    }

    // Print songs with a play count above the given threshold
    public void printSongsAbovePlayCount(int threshold) {
        boolean found = false;
        for (Song song : songs) {
            if (song.getPlayCount() > threshold) {
                System.out.println(song);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No songs with play count above " + threshold);
        }
    }

    // Method to make sure user input is valid length and non null
    private String getValidStringInput(Scanner scanner, String prompt, int minLength, int maxLength) {
        String input = "";
        while (input == null || input.trim().isEmpty() || input.length() < minLength || input.length() > maxLength) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input == null || input.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else if (input.length() < minLength || input.length() > maxLength) {
                System.out.println("Input must be between " + minLength + " and " + maxLength + " characters.");
            }
        }
        return input;
    }

    // Method to make sure user input is a valid play count
    private long getValidPlayCountInput(Scanner scanner) {
        Long playCount = null;
        while (playCount == null) {
            System.out.print("Enter play count (0 - 5,000,000,000): ");
            if (!scanner.hasNextLong()) {
                System.out.println("Invalid input. Please enter a valid long integer.");
                scanner.next(); 
                playCount = null;
            } else {
                playCount = scanner.nextLong();
                if (playCount < 0 || playCount > MAX_PLAY_COUNT) {
                    System.out.println("Invalid play count. Play count must be between 0 and 5 billion.");
                    playCount = null; 
                }
            }
        }
        return playCount; 
    }

    // Menu to interact with the app
    public static void main(String[] args) {
        MusicApp app = new MusicApp();
        Scanner scanner = new Scanner(System.in);

        // Sample data
        app.addSong("Blinding Lights", "The Weeknd", 123456789);
        app.addSong("Levitating", "Dua Lipa", 98765432);
        app.addSong("Shape of You", "Ed Sheeran", 500000000);
        app.addSong("Rolling in the Deep", "Adele", 300000000);
        app.addSong("Bad Guy", "Billie Eilish", 600000000);
        app.addSong("Uptown Funk", "Mark Ronson ft. Bruno Mars", 750000000);
        app.addSong("Old Town Road", "Lil Nas X", 2000000000);
        app.addSong("Happier", "Marshmello ft. Bastille", 120000000);
        app.addSong("Sunflower", "Post Malone ft. Swae Lee", 1300000000);
        app.addSong("Rockstar", "Post Malone ft. 21 Savage", 1000000000);

        boolean running = true;

        while (running) {
            System.out.println("\nMusic App Menu:");
            System.out.println("1. Add a song");
            System.out.println("2. Remove a song");
            System.out.println("3. Print all songs");
            System.out.println("4. Print songs with play count above a given number");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    String title = app.getValidStringInput(scanner, "Enter song title (1-200 characters): ", 1, 200);
                    String artist = app.getValidStringInput(scanner, "Enter artist name (1-100 characters): ", 1, 100);
                    long playCount = app.getValidPlayCountInput(scanner);
                    app.addSong(title, artist, playCount);
                    break;
                case 2:
                    System.out.print("Enter song title to remove: ");
                    String removeTitle = scanner.nextLine();
                    app.removeSong(removeTitle);
                    break;
                case 3:
                    app.printAllSongs();
                    break;
                case 4:
                    System.out.print("Enter the play count threshold: ");
                    int threshold = scanner.nextInt();
                    app.printSongsAbovePlayCount(threshold);
                    break;
                case 5:
                    System.out.println("Exiting the app...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
}
