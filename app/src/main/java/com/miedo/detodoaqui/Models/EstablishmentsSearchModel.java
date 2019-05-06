package com.miedo.detodoaqui.Models;

import com.miedo.detodoaqui.Data.EstablishmentSearch;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentsSearchModel {

    public List<EstablishmentSearch> SearchEstablishments(String keyword, String location, String category){
        List<EstablishmentSearch> establishments = new ArrayList<>();
        if(keyword.equals("1")){
            establishments.add(new EstablishmentSearch("La tiendita de don pepe", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-amor02.jpg",5));
            establishments.add(new EstablishmentSearch("Pollería", "Av. holi 124", "https://www.chiquipedia.com/imagenes/animo01.jpg",4));
            establishments.add(new EstablishmentSearch("Esta es una prueba", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-san-valentin18.jpg",3.5f));
        }else{
            establishments.add(new EstablishmentSearch("Casa de Coco", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-animo10.jpg",1));
            establishments.add(new EstablishmentSearch("Patio de SMAAAAAAAAASH", "Av. holi 124", "https://www.chiquipedia.com/imagenes/imagenes-frases05.jpg",4.5f));
        }
        return establishments;
    }
}
