package com.miedo.detodoaqui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.miedo.detodoaqui.Adapters.EstablishmentUserAdapter;
import com.miedo.detodoaqui.Viewmodels.EstablishmentsUserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class EstablishmentsActivity extends AppCompatActivity {

    private EstablishmentsUserViewModel viewModel;

    public static final String TAG = EstablishmentsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishments);

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(EstablishmentsUserViewModel.class);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                // cambiar por start activity for result
                Intent intent = new Intent(EstablishmentsActivity.this, RegistroFakeActivity.class);
                EstablishmentsActivity.this.startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recyclerview
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new EstablishmentUserAdapter(viewModel.getData(), getApplicationContext()));


    }


}
