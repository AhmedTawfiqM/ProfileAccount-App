package atdev.com.retrofitrxjavaall.pojo.postnetcomponent;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.BuildConfig;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    @NetworkCompScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        //HttpLoggingInterceptor write every thing to log screen in android studio
        //and show sensetive information like authentication and passwords
        // Check if it is in Development Mode
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(loggingInterceptor);
        }

        return okHttpClient.build();
    }

    @Provides
    @NetworkCompScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}

//HttpLoggingInterceptor
//        retrofit by default create okHTTp but we creaste custom one to add HttpLoggingInterceptor
//        HttpLoggingInterceptor write every thing to log screen in android studio
//        and show sensetive information like authentication and passwords
//Be careful when using HttpLoggingInterceptor
//if u download image it will pring more lines in log and that will effect on user performance