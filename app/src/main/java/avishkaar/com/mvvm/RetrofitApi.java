package avishkaar.com.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    String BASE_URL = "https://jsonplaceholder.typicode.com";

    @GET("/posts")
    Call<List<User>> get();

    @GET("/posts")
    LiveData<List<User>> browseRepoLiveData();

}
