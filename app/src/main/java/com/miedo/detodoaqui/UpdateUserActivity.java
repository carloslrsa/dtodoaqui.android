package com.miedo.detodoaqui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.miedo.detodoaqui.Data.Local.SessionManager;
import com.miedo.detodoaqui.Data.Profile;
import com.miedo.detodoaqui.Data.User;
import com.miedo.detodoaqui.Utils.Validators;
import com.miedo.detodoaqui.Viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UpdateUserActivity extends AppCompatActivity {

    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText phoneEditText;
    Spinner countriesSpinner;
    EditText addressEditText;
    EditText descriptionEditText;
    EditText facebookEditText;

    Button updateButton;

    UserViewModel viewModel;

    Profile lastProfile = null;
    
    private boolean flagUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        firstNameEditText = (EditText) findViewById(R.id.nameUserUpdateET);
        lastNameEditText = (EditText) findViewById(R.id.lastnameUserUpdateET);
        phoneEditText = (EditText) findViewById(R.id.phoneUserUpdateET);
        countriesSpinner = (Spinner) findViewById(R.id.countryUserUpdateSpinner);
        addressEditText = (EditText) findViewById(R.id.addressUserUpdateET);
        descriptionEditText = (EditText) findViewById(R.id.descriptionUserUpdateET);
        facebookEditText = (EditText) findViewById(R.id.facebookUserUpdateET);
        updateButton = (Button) findViewById(R.id.updateUserButton);

        firstNameEditText.setEnabled(false);
        lastNameEditText.setEnabled(false);
        phoneEditText.setEnabled(false);
        countriesSpinner.setEnabled(false);
        addressEditText.setEnabled(false);
        descriptionEditText.setEnabled(false);
        facebookEditText.setEnabled(false);
        updateButton.setEnabled(false);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastProfile == null)
                    lastProfile = new Profile();
                lastProfile.setAddress(addressEditText.getText().toString());
                lastProfile.setCountry((String)countriesSpinner.getSelectedItem());
                lastProfile.setDescription(descriptionEditText.getText().toString());
                lastProfile.setFacebookUrl(facebookEditText.getText().toString());
                lastProfile.setFirstName(firstNameEditText.getText().toString());
                lastProfile.setLastName(lastNameEditText.getText().toString());
                lastProfile.setPhone(phoneEditText.getText().toString());

                if(Validators.Validate(lastProfile)){
                    viewModel.UpdateProfile(lastProfile);
                }else{
                    Toast.makeText(UpdateUserActivity.this, "Revise los campos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        List<String> countriesList = getCountryList();
        ArrayAdapter countriesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countriesList);
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countriesSpinner.setAdapter(countriesAdapter);

        //ViewModel

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null){
                    //Actualización fallida
                    Log.i("Profile","Login fallido");
                }else {

                    if (user.getId().equals("-")) {
                        //Cierre sesión

                    } else {
                        if(flagUpdate){
                            viewModel.SincUser(user.getUsername(),user.getPassword());
                            flagUpdate = false;
                        }else{
                            lastProfile = user.getProfile();
                            if(user.getProfile() != null){
                                firstNameEditText.setText(user.getProfile().getFirstName());
                                lastNameEditText.setText(user.getProfile().getLastName());
                                phoneEditText.setText(user.getProfile().getPhone());
                                for(int i=1; i<countriesList.size(); i++){
                                    if(countriesList.get(i).equals(user.getProfile().getCountry())){
                                        countriesSpinner.setSelection(i);
                                    }
                                }
                                addressEditText.setText(user.getProfile().getAddress());
                                descriptionEditText.setText(user.getProfile().getDescription());
                                facebookEditText.setText(user.getProfile().getFacebookUrl());
                            }

                            firstNameEditText.setEnabled(true);
                            lastNameEditText.setEnabled(true);
                            phoneEditText.setEnabled(true);
                            countriesSpinner.setEnabled(true);
                            addressEditText.setEnabled(true);
                            descriptionEditText.setEnabled(true);
                            facebookEditText.setEnabled(true);
                            updateButton.setEnabled(true);
                        }
                    }
                }
            }
        });
    }

    private List<String> getCountryList(){
        List<String> countries = new ArrayList();
        countries.add("Seleccione su pais");
        Locale[] locales = Locale.getAvailableLocales();
        for(Locale locale : locales){
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        return countries;
    }

}
