package atdev.com.retrofitrxjavaall.ui.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import atdev.com.retrofitrxjavaall.R;
import atdev.com.retrofitrxjavaall.databinding.ActivityLoginBinding;
import atdev.com.retrofitrxjavaall.pojo.models.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Vars
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.btnLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        String fname = binding.etFname.getText().toString();
        String email = binding.etEmail.getText().toString();
        String pass = binding.etPass.getText().toString();

        if (fname.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Fill Fields plz....", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(email, fname, pass, "https");
        loginViewModel.CreateAccount(user).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                Toast.makeText(LoginActivity.this, "Successful Logging ID : " + user.getId().toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

}
