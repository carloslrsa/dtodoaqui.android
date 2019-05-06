package com.miedo.detodoaqui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.miedo.detodoaqui.Adapters.EstablishmentSearchAdapter;
import com.miedo.detodoaqui.Adapters.EstablishmentUserAdapter;
import com.miedo.detodoaqui.Viewmodels.EstablishmentsSearchViewModel;

import org.w3c.dom.Text;

import java.util.Timer;

public class SearchFragment extends Fragment {

    private final int searchContainerColapsedHeight = 75;
    private final int searchContainerExpandedHeight = 275;

    private LinearLayout searchLayout;
    private EditText keywordSearchParam;
    private EditText locationSearchParam;
    private Spinner categoriesSearchParam;
    private Button searchButton;
    private Button colapseButton;

    private RecyclerView establishmentsSearchResult;

    private EstablishmentsSearchViewModel viewModel;

    private boolean colapsed = false;


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

        searchLayout = view.findViewById(R.id.searchLayout);
        keywordSearchParam = (EditText) view.findViewById(R.id.keywordSearchET);
        locationSearchParam = (EditText) view.findViewById(R.id.locationSearchET);
        categoriesSearchParam = (Spinner) view.findViewById(R.id.categoriesSearchSpinner);
        searchButton = (Button) view.findViewById(R.id.searchButton);
        colapseButton = (Button) view.findViewById(R.id.colapseButton);


        //Listeners
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        colapseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colapsed){
                    expandSearchLayout(300);

                }else {
                    collapseSearchLayout(300);

                }
                colapsed = !colapsed;
            }
        });

        establishmentsSearchResult = (RecyclerView) view.findViewById(R.id.establishmentsSearchRV);

        establishmentsSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        establishmentsSearchResult.setAdapter(new EstablishmentSearchAdapter(viewModel.getData(), getContext()));


        return view;
    }

    //Utilities

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    //Animations
    public void expandSearchLayout(int duration) {
        int prevHeight  = searchLayout.getHeight();
        //int targetHeight = dpToPx(searchContainerExpandedHeight);
        searchLayout.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int targetHeight = searchLayout.getMeasuredHeight();
        //targetHeight = dpToPx(targetHeight);

        searchLayout.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                searchLayout.getLayoutParams().height = (int) animation.getAnimatedValue();
                searchLayout.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    public void collapseSearchLayout(int duration) {
        int prevHeight  = searchLayout.getHeight();
        int targetHeight = dpToPx(searchContainerColapsedHeight);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                searchLayout.getLayoutParams().height = (int) animation.getAnimatedValue();
                searchLayout.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }
}
