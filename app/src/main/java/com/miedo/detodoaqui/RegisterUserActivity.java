package com.miedo.detodoaqui;

import android.app.Activity;
import android.os.Bundle;

import com.miedo.detodoaqui.Data.Local.SessionManager;
import com.miedo.detodoaqui.Data.User;
import com.miedo.detodoaqui.Viewmodels.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_email;
    private EditText et_passwordconfirm;

    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        et_username = findViewById(R.id.usernameUserRegisterET);
        et_password = findViewById(R.id.passwordUserRegisterET);
        et_email = findViewById(R.id.emailUserRegisterET);
        et_passwordconfirm = findViewById(R.id.passConfUserRegisterET);

        Button bt_register = findViewById(R.id.registerUserButton);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String email = et_email.getText().toString();
                String password_confirm = et_passwordconfirm.getText().toString();

                if(!username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !password_confirm.isEmpty()
                && password.equals(password_confirm)
                && password.length() > 7 && password_confirm.length() > 7){
                    et_username.setEnabled(false);
                    et_password.setEnabled(false);
                    et_email.setEnabled(false);
                    et_passwordconfirm.setEnabled(false);

                    viewModel.Register(username,email,password,password_confirm);
                }else{
                    Toast.makeText(RegisterUserActivity.this, "Asegúrese de llenar correctamente los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //ViewModel

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUserRegister().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null){
                    //Registro fallido
                    Toast.makeText(RegisterUserActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                }else{
                    //Registro exitoso
                    Toast.makeText(RegisterUserActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    SessionManager.getInstance().StartSession(user);
                    finish();
                }
            }
        });
    }

}
