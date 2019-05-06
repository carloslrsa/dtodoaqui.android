package com.miedo.detodoaqui.Viewmodels;

import androidx.lifecycle.ViewModel;

import com.miedo.detodoaqui.Data.EstablishmentSearch;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentsSearchViewModel extends ViewModel {
    private ArrayList<EstablishmentSearch> data;

    public EstablishmentsSearchViewModel() {
        init();
    }

    private void init() {
        data = new ArrayList<>();
        data.add(new EstablishmentSearch("La tiendita de don pepe", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-amor02.jpg",5));
        data.add(new EstablishmentSearch("Pollería", "Av. holi 124", "https://www.chiquipedia.com/imagenes/animo01.jpg",4));
        data.add(new EstablishmentSearch("Esta es una prueba", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-san-valentin18.jpg",3.5f));
        data.add(new EstablishmentSearch("Casa de Coco", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-animo10.jpg",1));
        data.add(new EstablishmentSearch("Patio de SMAAAAAAAAASH", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-frases05.jpg",4.5f));
    }

    public List<EstablishmentSearch> getData() {
        return data;
    }
}
