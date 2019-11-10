package atdev.com.retrofitrxjavaall.pojo.network;

import java.util.List;
import java.util.Map;

import atdev.com.retrofitrxjavaall.pojo.models.GitHubRepo;
import atdev.com.retrofitrxjavaall.pojo.models.User;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface APIService {


    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> getReposforUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<GitHubRepo>> getReposforUserObservable(@Path("user") String user);

    //https://reqres.in/api/users   Post Request
    @POST("users")
    Call<User> CreateAccount(@Body User user);

    //Upload Photo or File to Server
    @Multipart
    @POST("users")
    Call<ResponseBody> UploadPhoto(
            @Part("first_name") RequestBody description,
            @Part MultipartBody.Part photo
    );
    //Multipart make request to parts
    //ResponseBody return response not json or xml but it can deal as string

    @Multipart
    @POST("users")
    Call<ResponseBody> UploadPhoto(
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody lastname,
            @Part("email") RequestBody email,
            @Part MultipartBody.Part photo
    );

    //Use PartMap when we have more data strings for example
    @Multipart
    @POST("users")
    Call<ResponseBody> UploadPhoto(
            @PartMap() Map<RequestBody, String> data,
            @Part MultipartBody.Part photo
    );
}
