package cory_edgett.rowdyhacks2018;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SCService {

    @GET("/songs/")
    Call<SongsList> getSongs();


    @GET("/songs/{id}/listen")
    Call<ResponseBody> getSongFile(@Path("id") long id);
}
