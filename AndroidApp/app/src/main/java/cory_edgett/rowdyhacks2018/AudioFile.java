package cory_edgett.rowdyhacks2018;

/**
 * Created by BadAp on 4/7/2018.
 */

public class AudioFile {
    private long id;
    private String title;
    private String artist;

    public AudioFile(long songID, String songTitle, String songArtist){
        id=songID;
        title=songTitle;
        artist=songArtist;
    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
