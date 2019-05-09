package com.miedo.detodoaqui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.miedo.detodoaqui.Utils.MultiSpinner;

import java.util.Arrays;
import java.util.Calendar;

public class StepFourFragment extends Fragment implements MultiSpinner.MultiSpinnerListener {


    private int hora_inicio, minuto_inicio, hora_fin, minuto_fin;


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la hora actual
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    // Variables auxiliares para el formateo de la hora
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";

    private static final int HORA_INICIO = 1;
    private static final int HORA_FIN = 2;


    // TextViews para obtener la hora de inicio y la hora de cierre.
    private TextView textViewStartTime;
    private TextView textViewEndTime;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_step4, container, false);

        // Set views
        textViewStartTime = view.findViewById(R.id.tv_start_time);
        textViewEndTime = view.findViewById(R.id.tv_end_time);


        textViewStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora(HORA_INICIO);
            }
        });

        textViewEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora(HORA_FIN);
            }
        });

        // Obtenemos el multispinner
        MultiSpinner multiSpinner = (MultiSpinner) view.findViewById(R.id.spinner_services);
        multiSpinner.setItems(
                Arrays.asList("Wi-fi", "Delivery", "Pago con tarjeta", "Parking", "Patio de juegos", "Servicios Higiénicos"),
                "Selecciona uno o más servicios",
                "Categorías", this);


        return view;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {
        // TODO implementar el listener para la seleccion de servicios
    }

    private void obtenerHora(final int opt) {


        TimePickerDialog recogerHora = new TimePickerDialog(this.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada = (hourOfDay < 10) ? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10) ? String.valueOf(CERO + minute) : String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                String resultado = horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM;

                // Si se selecciona la hora de inicio se asignan las variables de inicio, sino las de fin
                if (opt == HORA_INICIO) {
                    textViewStartTime.setText(resultado);
                    hora_inicio = hourOfDay;
                    minuto_inicio = minute;

                } else if (opt == HORA_FIN) {
                    textViewEndTime.setText(resultado);
                    hora_fin = hourOfDay;
                    minuto_fin = minute;
                }

            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        },

                (opt == HORA_INICIO) ? hora_inicio : hora_fin,
                (opt == HORA_INICIO) ? minuto_inicio : minuto_fin,
                false);

        recogerHora.show();
    }

}
