package com.miedo.detodoaqui;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miedo.detodoaqui.Data.Local.SessionManager;
import com.miedo.detodoaqui.Data.User;
import com.miedo.detodoaqui.Viewmodels.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la barra de navegacion inferior

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Definimos el host
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        // Obtenemos el nav controller
        NavController navController = host.getNavController();


        //Seteamos la navegacion del navview al nav controller
        NavigationUI.setupWithNavController(navView, navController);

        //Seteamos el contexto al SessionManager

        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.SetContext(getApplicationContext());

        User user = sessionManager.getCurrentSession();

        //ViewModel

        UserViewModel viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null){
                    //Login fallido
                    Log.i("Profile","Login fallido");
                }else{
                    //Login exitoso
                    Log.i("Profile","Login exitoso");
                }
            }
        });

        if(!user.getUsername().equals(""))
            viewModel.Login(user.getUsername(),user.getPassword());
    }
}
