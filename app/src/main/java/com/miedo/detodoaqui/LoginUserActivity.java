package com.miedo.detodoaqui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miedo.detodoaqui.Data.User;
import com.miedo.detodoaqui.Viewmodels.UserViewModel;

public class LoginUserActivity extends AppCompatActivity {

    // Log TAG
    private static final String TAG = LoginUserActivity.class.getSimpleName();

    private EditText et_username;
    private EditText et_password;

    private UserViewModel viewModel;

    private static boolean flagOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        et_username = findViewById(R.id.usernameUserRegisterET);
        et_password = findViewById(R.id.passwordUserRegisterET);

        Button bt_login = findViewById(R.id.registerUserButton);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if(!username.isEmpty() && !password.isEmpty()){
                    et_username.setEnabled(false);
                    et_password.setEnabled(false);
                    viewModel.Login(username,password);
                }
            }
        });

        //ViewModel

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null){
                    //Login fallido
                    if(flagOpen){
                        et_username.setEnabled(true);
                        et_password.setEnabled(true);
                        Toast.makeText(LoginUserActivity.this, "Login fallido", Toast.LENGTH_SHORT).show();
                        flagOpen = false;
                    }else{
                        flagOpen = true;
                    }

                }else{
                    //Login exitoso
                    if(flagOpen){
                        if(user.getProfile() == null){
                            Toast.makeText(LoginUserActivity.this, "Login exitoso, sin profile", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginUserActivity.this, "Login exitoso, con profile", Toast.LENGTH_SHORT).show();
                        }
                        //SessionManager.getInstance().StartSession(user);
                        flagOpen = false;
                        finish();
                    }else{
                        flagOpen = true;
                    }
                }
            }
        });

    }

}
