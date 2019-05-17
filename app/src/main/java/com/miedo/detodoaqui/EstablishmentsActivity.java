package com.miedo.detodoaqui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.miedo.detodoaqui.Adapters.EstablishmentUserAdapter;
import com.miedo.detodoaqui.Utils.StateViewUtils;
import com.miedo.detodoaqui.Viewmodels.EstablishmentsUserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


public class EstablishmentsActivity extends AppCompatActivity {

    private EstablishmentsUserViewModel viewModel;
    private EstablishmentUserAdapter adapter;

    RecyclerView recyclerView;
    View message_layout;

    public static final String TAG = EstablishmentsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishments);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // message layout
        message_layout = findViewById(R.id.message_layout);

        //adapter
        adapter = new EstablishmentUserAdapter(getApplicationContext());

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(EstablishmentsUserViewModel.class);
        // actualiza el adapter
        viewModel.getUserEstablishments().observe(this, establishmentUsers -> {

            if (establishmentUsers.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                StateViewUtils.setNotFoundEstablishmentState(message_layout, recyclerView, R.drawable.ic_not_found,
                        R.string.est_user_notfound_title,
                        R.string.est_user_notfound_descrip);

            } else {
                message_layout.setVisibility(View.GONE);
                adapter.setData(establishmentUsers);
                recyclerView.setVisibility(View.VISIBLE);
            }


        });


        // Recyclerview
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // Floating Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                // cambiar por start activity for result
                Intent intent = new Intent(EstablishmentsActivity.this, RegisterEstablishmentActivity.class);
                EstablishmentsActivity.this.startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actualizar();
    }


    void actualizar() {
        StateViewUtils.setLoading(message_layout);
        recyclerView.setVisibility(View.INVISIBLE);
        viewModel.updateData();

    }


}
