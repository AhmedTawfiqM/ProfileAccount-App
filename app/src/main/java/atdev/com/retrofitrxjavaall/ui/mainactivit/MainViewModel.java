package atdev.com.retrofitrxjavaall.ui.mainactivit;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import atdev.com.retrofitrxjavaall.pojo.models.GitHubRepo;
import atdev.com.retrofitrxjavaall.pojo.repositories.MainRepo;

public class MainViewModel extends ViewModel {


    private MutableLiveData<List<GitHubRepo>> repos;
    private MainRepo mainRepo;

    public void Init() {
        mainRepo = new MainRepo();
        repos = mainRepo.getRepos();
    }

    public LiveData<List<GitHubRepo>> getRepos() {
        return repos;
    }

}
