package atdev.com.retrofitrxjavaall.pojo.postnetcomponent;


import atdev.com.retrofitrxjavaall.pojo.network.APIService;
import dagger.Component;


@Component(modules = ApiModule.class)
@NetworkCompScope
public interface NetworkComponent {

    APIService getApiService();
}
