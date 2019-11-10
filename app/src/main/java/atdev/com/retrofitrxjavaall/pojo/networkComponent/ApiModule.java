package atdev.com.retrofitrxjavaall.pojo.networkComponent;

import atdev.com.retrofitrxjavaall.pojo.network.APIService;
import atdev.com.retrofitrxjavaall.utilities.Urls;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiModule {


    @Provides
    @NetworkCompScope
    public APIService getApiService(Retrofit retrofit) {

        return retrofit.create(APIService.class);

    }

    @Provides
    @NetworkCompScope
    public Retrofit getRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(Urls.baseUrl1)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //for RX JAVA ONLY
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
