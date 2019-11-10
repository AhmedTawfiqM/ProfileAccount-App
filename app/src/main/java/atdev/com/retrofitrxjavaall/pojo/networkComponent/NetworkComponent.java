package atdev.com.retrofitrxjavaall.pojo.networkComponent;


import javax.inject.Singleton;

import atdev.com.retrofitrxjavaall.pojo.network.APIService;
import dagger.Component;


@Component(modules =ApiModule.class)
@NetworkCompScope
public interface NetworkComponent {

    APIService getApiService();
}
