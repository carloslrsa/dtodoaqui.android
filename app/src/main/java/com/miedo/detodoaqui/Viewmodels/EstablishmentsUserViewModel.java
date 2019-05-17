package com.miedo.detodoaqui.Viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miedo.detodoaqui.Data.EstablishmentSearch;
import com.miedo.detodoaqui.Data.EstablishmentUser;
import com.miedo.detodoaqui.Models.EstablishmentUserModel;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentsUserViewModel extends ViewModel {

    private MutableLiveData<List<EstablishmentUser>> data = new MutableLiveData<List<EstablishmentUser>>();
    EstablishmentUserModel model = new EstablishmentUserModel();

    public EstablishmentsUserViewModel() {
    }

    public LiveData<List<EstablishmentUser>> getUserEstablishments() {
        return data;
    }

    public void updateData() {
        // data.setValue(model.searchData());
        new SearchTask().execute(null, null, null);
    }


    //Test para simular la carga de datos
    private class SearchTask extends AsyncTask<Void, Void, List<EstablishmentUser>> {

        @Override
        protected List<EstablishmentUser> doInBackground(Void... voids) {
            List<EstablishmentUser> result = null;
            try {
                Thread.sleep(2000);
                result = model.searchData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;

        }

        @Override
        protected void onPostExecute(List<EstablishmentUser> establishmentUsers) {
            data.setValue(establishmentUsers);
        }
    }

}
