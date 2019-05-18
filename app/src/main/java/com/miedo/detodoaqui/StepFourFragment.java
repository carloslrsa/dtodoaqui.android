package com.miedo.detodoaqui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;
import com.miedo.detodoaqui.Adapters.StepperAdapter;
import com.miedo.detodoaqui.Utils.MultiSpinner;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class StepFourFragment extends Fragment implements MultiSpinner.MultiSpinnerListener, Step {


    private int hora_inicio = -1, minuto_inicio = -1, hora_fin = -1, minuto_fin = -1;

    private static final String ERROR_HORA = "1";

    List<String> opciones = Arrays.asList("Wi-fi", "Delivery", "Pago con tarjeta", "Parking", "Patio de juegos", "Servicios Higiénicos");

    boolean[] seleccionados = new boolean[opciones.size()];


    // Variables auxiliares para el formateo de la hora
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";

    private static final int HORA_INICIO = 1;
    private static final int HORA_FIN = 2;


    // TextViews para obtener la hora de inicio y la hora de cierre.
    private TextView textViewStartTime;
    private TextView textViewEndTime;

    // View para actualizar la seleccion
    private FlexboxLayout flexboxLayout;


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
        flexboxLayout = view.findViewById(R.id.flex_box);


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
                opciones,
                "Selecciona uno o más servicios",
                "Categorías", this);


        return view;
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

        seleccionados = selected;
        updateItemsSelected();
    }

    private void obtenerHora(final int opt) {


        TimePickerDialog recogerHora = new TimePickerDialog(this.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String resultado = formatTime(hourOfDay, minute);
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

    // actualizar la interfaz de la seleccion de categorías
    public void updateItemsSelected() {
        flexboxLayout.removeAllViews();
        for (int i = 0; i < seleccionados.length; i++) {
            if (seleccionados[i]) {

                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.label_category, null);
                textView.setText(opciones.get(i));

                flexboxLayout.addView(textView);

            }
        }
    }

    public String formatTime(int hourOfDay, int minute) {
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
        return horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {

        VerificationError ve = null;
        if (hora_inicio != -1 && hora_fin != -1) {
            if (hora_fin < hora_inicio) {
                ve = new VerificationError("La hora de cierre debe ser mayor que la hora de inicio.");
            } else if (hora_inicio == hora_fin) {
                if (minuto_fin < minuto_inicio) {
                    ve = new VerificationError("La hora de cierre debe ser mayor que la hora de inicio");
                }
            }
        }

        return ve;
    }

    @Override
    public void onSelected() {

        updateItemsSelected();

        if (hora_inicio != -1 && hora_fin != -1) {
            textViewStartTime.setText(formatTime(hora_inicio, minuto_inicio));
            textViewEndTime.setText(formatTime(hora_fin, minuto_fin));
        }
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(getContext(), error.getErrorMessage(), Toast.LENGTH_LONG).show();

    }

    public Bundle getData() {
        Bundle bundle = new Bundle();


        return null;
    }
}
