package cory_edgett.rowdyhacks2018;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildMusicStream extends AsyncTask<Void, Void, Void> {
    private List<Song> songs;

    public AsyncResponse<Boolean> delegate = null;
    public BuildMusicStream(AsyncResponse<Boolean> delegate){
        this.delegate = delegate;
    }
    @Override
    protected Void doInBackground(Void... Params){
        Log.d("","BuildMusicStream called");
        BuildMusicList();
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    private void BuildMusicList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SCService scService = retrofit.create(SCService.class);
        Call<SongsList> call = scService.getSongs();
        try {
            songs = call.execute().body().songs;
        } catch (Throwable t){
            t.printStackTrace();
            return;
        }
        for(Song song: songs){
            syncMusic(song);
        }
        delegate.processFinish(true);
    }

    private void syncMusic(Song song) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        SCService scService = retrofit.create(SCService.class);
        Call<ResponseBody> call = scService.getSongFile(1);
        try{
            ResponseBody response = call.execute().body();
            boolean writeSuccess = writeResponseBodyToDisk(response, song);
            if(writeSuccess) {

            } else {
                delegate.processFinish(null);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, Song song) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Music");
            Log.d("writing response", "" + body.bytes());
            if(!root.exists()){
                root.mkdir();
            }

            File audioFile = new File(root, song.getTitle());

            try {
                FileWriter writer = new FileWriter(audioFile);
                writer.append(body.string());
                writer.flush();
                writer.close();
                song.setAudioFile(audioFile);

            } catch (IOException e) {
                e.printStackTrace();

                return false;
            }
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }
}
