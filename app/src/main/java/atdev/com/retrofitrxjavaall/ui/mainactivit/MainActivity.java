package atdev.com.retrofitrxjavaall.ui.mainactivit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import atdev.com.retrofitrxjavaall.R;
import atdev.com.retrofitrxjavaall.databinding.ActivityMainBinding;
import atdev.com.retrofitrxjavaall.pojo.models.GitHubRepo;
import atdev.com.retrofitrxjavaall.ui.loginactivity.LoginActivity;
import atdev.com.retrofitrxjavaall.ui.uploadfile.UploadFileActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "REtro";
    //Vars
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.Init();
        viewModel.getRepos().observe(this, new Observer<List<GitHubRepo>>() {
            @Override
            public void onChanged(List<GitHubRepo> gitHubRepos) {

                Log.d(TAG, "onChanged: " + gitHubRepos.size());
                binding.text.setText("Number Repos " + gitHubRepos.size() + "");
            }
        });


        binding.btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UploadFileActivity.class);
                startActivity(intent);
            }
        });

    }


}
