package cory_edgett.rowdyhacks2018;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.File;


public class Song {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("artist")
    private String artist;
    @SerializedName("publisher")
    private String publisher;

    @SerializedName("year")
    private long year;

    @SerializedName("audioname")
    private String audioname;

    @SerializedName("artname")
    private String artname;

    private File audioFile;

    public void setAudioFile(File audioFile){
        this.audioFile = audioFile;
    }
    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public File getSong(){
        if (audioFile != null) {
            Log.d("AudioFile in Song.java", audioFile.toString());
        } else {
            Log.d("AudioFile in Song.java", "null");
        }
        return audioFile;
    }


}
