package com.miedo.detodoaqui.Viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miedo.detodoaqui.Data.EstablishmentSearch;
import com.miedo.detodoaqui.Models.EstablishmentsSearchModel;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentsSearchViewModel extends ViewModel {
    private MutableLiveData<List<EstablishmentSearch>> data = new MutableLiveData<List<EstablishmentSearch>>();
    private EstablishmentsSearchModel model = new EstablishmentsSearchModel();

    public EstablishmentsSearchViewModel() {
    }

    public void SearchEstablishments(final String keyword, final String location, final String category){
        /*Se llama al Model*/
        new SearchTask().execute(new String[]{keyword,location,category});
    }

    public LiveData<List<EstablishmentSearch>> getSearchData() {
        return data;
    }


    //Test
    private class SearchTask extends AsyncTask<String,Void,String[]>{

        @Override
        protected String[] doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return strings;

        }

        @Override
        protected void onPostExecute(String[] strings) {
            data.setValue(model.SearchEstablishments(strings[0],strings[1],strings[2]));
        }
    }
}
