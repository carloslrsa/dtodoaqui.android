package com.miedo.detodoaqui.Viewmodels;

import androidx.lifecycle.ViewModel;

import com.miedo.detodoaqui.Data.EstablishmentUser;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentsUserViewModel extends ViewModel {

    private ArrayList<EstablishmentUser> data;

    public EstablishmentsUserViewModel() {
        init();
    }

    private void init() {
        data = new ArrayList<>();
        data.add(new EstablishmentUser("La tiendita de don pepe", 4f, 124, "GAAAA"));
        data.add(new EstablishmentUser("La tia venocancer", 3f, 5555, "GAAAA"));
        data.add(new EstablishmentUser("El ezkai", 1f, 69, "GAAAA"));
        data.add(new EstablishmentUser("La ratacueva", 2f, 322, "GAAAA"));
        data.add(new EstablishmentUser("laboratorio gaaaaa", 4.7f, 322322, "GAAAA"));
    }


    public List<EstablishmentUser> getData() {
        return data;
    }
}
