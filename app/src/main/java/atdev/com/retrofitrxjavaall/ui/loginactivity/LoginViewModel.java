package atdev.com.retrofitrxjavaall.ui.loginactivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import atdev.com.retrofitrxjavaall.pojo.models.User;
import atdev.com.retrofitrxjavaall.pojo.repositories.LoginRepo;

public class LoginViewModel extends ViewModel {


    public LiveData<User> CreateAccount(User user) {

        LoginRepo loginRepo = new LoginRepo();
        return loginRepo.CreateAccount(user);
    }
}
