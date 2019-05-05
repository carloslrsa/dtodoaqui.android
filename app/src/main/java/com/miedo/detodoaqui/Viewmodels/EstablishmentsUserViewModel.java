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
        data.add(new EstablishmentUser("La tiendita de don pepe", 4f, 124, "https://i.ytimg.com/vi/IfWQBBgs4y4/maxresdefault.jpg"));
        data.add(new EstablishmentUser("La tia venocancer", 3f, 5555, "https://i.ytimg.com/vi/XEQtZNuBpJA/maxresdefault.jpg"));
        data.add(new EstablishmentUser("El ezkai", 1f, 69, "https://redaccion.lamula.pe/media/uploads/b44fa272-bae5-4401-9d90-41af62185727.jpg"));
        data.add(new EstablishmentUser("La ratacueva", 2f, 322, "https://i.ytimg.com/vi/tdEctrE76C8/maxresdefault.jpg"));
        data.add(new EstablishmentUser("laboratorio gaaaaa", 4.7f, 322322, "https://multimedia.larepublica.pe/720x405/larepublica/imagen/2018/08/04/noticia-facebook-cabina-comercial.png"));
    }


    public List<EstablishmentUser> getData() {
        return data;
    }
}
