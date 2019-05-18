package com.miedo.detodoaqui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AddressComponent;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.miedo.detodoaqui.Adapters.StepperAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.Arrays;


public class StepTwoFragment extends Fragment implements Step {

    private static final String TAG = StepTwoFragment.class.getSimpleName();
    Place selectedPlace;


    // Cosntructor necesario
    public StepTwoFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step2, container, false);

        // Initialize Places.
        Places.initialize(getContext(), getString(R.string.api_key_google_places));


        PlacesClient client = Places.createClient(getContext());

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ADDRESS_COMPONENTS, Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG));

        autocompleteFragment.setCountry("pe");
        autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                selectedPlace = place;

                for (AddressComponent a : selectedPlace.getAddressComponents().asList()) {
                    if (a.getTypes().equals(Arrays.asList("locality", "political"))) {
                        //Log.i(TAG, a.getName());

                    }
                    Log.i(TAG, a.getName() + " : " + a.getTypes());
                }

                //Log.i(TAG, "es nulo la lon lat ? : " + (place.getLatLng() == null));
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        autocompleteFragment.getView().findViewById(R.id.places_autocomplete_clear_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        autocompleteFragment.setText("");
                        selectedPlace = null;
                        view.setVisibility(View.GONE);

                    }
                });


        return view;

    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        VerificationError ve = null;

        if (selectedPlace == null) {
            ve = new VerificationError("Debes elegir una dirección válida.");
        }

        if(ve == null){

        }

        return ve;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

        Toast.makeText(getContext(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();

    }

    public void updateData(){




    }

    public Bundle getData() {
        Bundle bundle = new Bundle();

        for (AddressComponent a : selectedPlace.getAddressComponents().asList()) {
            if (a.getTypes().equals(Arrays.asList("locality", "political"))) {
                //Log.i(TAG, a.getName());

            }
            Log.i(TAG, a.getName() + " : " + a.getTypes());
        }

        return bundle;
    }
}
