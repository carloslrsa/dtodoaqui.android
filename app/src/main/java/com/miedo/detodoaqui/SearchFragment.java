package com.miedo.detodoaqui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.miedo.detodoaqui.Adapters.EstablishmentSearchAdapter;
import com.miedo.detodoaqui.Adapters.EstablishmentUserAdapter;
import com.miedo.detodoaqui.Viewmodels.EstablishmentsSearchViewModel;

import org.w3c.dom.Text;

public class SearchFragment extends Fragment {

    private TextInputLayout keywordSearchParam;
    private TextInputLayout locationSearchParam;
    private Spinner categoriesSearchParam;
    private Button searchButton;

    private RecyclerView establishmentsSearchResult;

    private EstablishmentsSearchViewModel viewModel;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(EstablishmentsSearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        keywordSearchParam = (TextInputLayout) view.findViewById(R.id.keywordSearchTL);
        locationSearchParam = (TextInputLayout) view.findViewById(R.id.locationSearchTL);
        categoriesSearchParam = (Spinner) view.findViewById(R.id.categoriesSearchSpinner);
        searchButton = (Button) view.findViewById(R.id.searchButton);
        establishmentsSearchResult = (RecyclerView) view.findViewById(R.id.establishmentsSearchRV);

        establishmentsSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        establishmentsSearchResult.setAdapter(new EstablishmentSearchAdapter(viewModel.getData(), getContext()));

        return view;
    }

}
