package atdev.com.retrofitrxjavaall.pojo.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import atdev.com.retrofitrxjavaall.pojo.models.GitHubRepo;
import atdev.com.retrofitrxjavaall.pojo.networkComponent.DaggerNetworkComponent;
import atdev.com.retrofitrxjavaall.pojo.networkComponent.NetworkComponent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainRepo {

    //Vars
    private MutableLiveData<List<GitHubRepo>> repos;
    private CompositeDisposable compositeDisposable;
    private NetworkComponent networkComp;

    public MainRepo() {

        compositeDisposable = new CompositeDisposable();
        repos = new MutableLiveData<>();
        networkComp = DaggerNetworkComponent.create();
    }

    public MutableLiveData<List<GitHubRepo>> getRepos() {


        //
        compositeDisposable.add(networkComp.getApiService().getReposforUserObservable("futurestudio")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GitHubRepo>>() {
                    @Override
                    public void accept(List<GitHubRepo> gitHubRepos) throws Exception {

                        Log.d("mainREPO", "accept: " + gitHubRepos.size());
                        repos.setValue(gitHubRepos);
                    }
                }));

        return repos;
    }
}
