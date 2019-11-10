package atdev.com.retrofitrxjavaall.pojo.repositories;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import atdev.com.retrofitrxjavaall.pojo.models.User;
import atdev.com.retrofitrxjavaall.pojo.network.APIService;
import atdev.com.retrofitrxjavaall.pojo.postnetcomponent.DaggerNetworkComponent;
import atdev.com.retrofitrxjavaall.pojo.postnetcomponent.NetworkComponent;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepo {

    private NetworkComponent networkComponent;
    private MutableLiveData<User> userLiveData;

    public LoginRepo() {

        userLiveData = new MutableLiveData<>();
        networkComponent = DaggerNetworkComponent.create();
    }

    //.....
    public MutableLiveData<User> CreateAccount(final User user) {

        APIService apiService = networkComponent.getApiService();
        apiService.CreateAccount(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User user1 = response.body();
                Log.d("ErrorR", "onFailure: " + user1.getId());
                userLiveData.setValue(user1);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.d("ErrorR", "onFailure: " + t.getMessage());
            }
        });

        return userLiveData;
    }
}
