public class Song {
    private String title;
    private String artist;
    private long playCount;

    public Song(String title, String artist, long playCount) {
        this.title = title;
        this.artist = artist;
        this.playCount = playCount;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Play Count: " + playCount;
    }
}
