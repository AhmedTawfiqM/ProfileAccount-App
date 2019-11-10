package atdev.com.retrofitrxjavaall.pojo.repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import atdev.com.retrofitrxjavaall.pojo.network.APIService;
import atdev.com.retrofitrxjavaall.utilities.FileUtils;
import atdev.com.retrofitrxjavaall.utilities.Urls;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadRepo {

    MutableLiveData<String> responseBody;

    public UploadRepo() {

        responseBody = new MutableLiveData<>();
    }

    public MutableLiveData<String> UploadPhoto(Context context, String des, Uri fileUri)
            throws Exception {

        //String descriptionString = "hello, this is description speaking";
        //RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, des);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

        //
        //File file = FileUtils.getFile(context, fileUri);
        File file = new File(fileUri.getPath());
        RequestBody fileRequest = RequestBody.create(MediaType.parse("image/*"),
                file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("avatar", file.getName(), fileRequest);

        // add another part within the multipart request

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseUrl2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        apiService.UploadPhoto(description, body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.d("UploadPH", "onResponse: " + response.body());
                responseBody.setValue(response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d("UploadPH", "onResponse: " + t.getMessage());
            }
        });

        return responseBody;
    }


    public MutableLiveData<String> UploadPhotoWithData(Context context, Map mapD, Uri fileUri)
            throws Exception {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseUrl2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        apiService.UploadPhoto(mapD, prepareFilePart(context, "avatar", fileUri))
                .enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        return responseBody;
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(Context context, String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(context, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @NonNull
    private RequestBody createRequestfromString(String string) {

        return RequestBody.create(okhttp3.MultipartBody.FORM, string);
    }
}
