package com.miedo.detodoaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UpdateUserActivity extends AppCompatActivity {

    Spinner countriesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        countriesSpinner = (Spinner) findViewById(R.id.countryUserUpdateSpinner);

        ArrayAdapter countriesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getCountryList());
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countriesSpinner.setAdapter(countriesAdapter);
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
