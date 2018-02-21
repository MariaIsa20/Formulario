package com.isabel.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText elogin, epassword, epassword_confirm, ecorreo;
    Button bFecha;
    TextView tFecha, tInfo;
    String usuario, clave, correo, clave2, dataRadio ="", dataCheck ="", ciudad, fecha;
    CheckBox leer, nadar, tejer, trotar;
    int dia, mes, anio;
    Spinner sCiudades;
    RadioButton rFemenino, rMasculino;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elogin = findViewById(R.id.entrar);
        epassword = findViewById(R.id.contraseña);
        epassword_confirm = findViewById(R.id.confirmarcontraseña);
        ecorreo = findViewById(R.id.correo);
        leer = findViewById(R.id.Leer);
        nadar = findViewById(R.id.Nadar);
        tejer = findViewById(R.id.Tejer);
        trotar = findViewById(R.id.Trotar);
        tInfo = findViewById(R.id.info);
        rFemenino = findViewById(R.id.rFemenino);
        rMasculino = findViewById(R.id.rMasculino);

        bFecha = findViewById(R.id.pickdate); //lo relaciono con la interfaz
        tFecha = findViewById(R.id.date);

        bFecha.setOnClickListener(this); // accion

        sCiudades = findViewById(R.id.sSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ciudades,
                android.R.layout.simple_spinner_item);

        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void onRadioButtonClicked(View view) {
        int id = view.getId();

        if (id == R.id.rMasculino) {

            dataRadio = "Hombre";
        } else {
            dataRadio = "Mujer";
        }
    }

    public void onCheckBoxClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.Leer:
                if (leer.isChecked()) {
                    dataCheck = "Leer";
                }
                break;

            case R.id.Nadar:
                if (nadar.isChecked()) {
                    dataCheck = "Nadar";
                }
                break;

            case R.id.Tejer:
                if (tejer.isChecked()) {
                    dataCheck = "Tejer";
                }
                break;

            case R.id.Trotar:
                if (trotar.isChecked()) {
                    dataCheck = "Trotar";
                }
                break;




        }


    }

    @Override
    public void onClick(View view) {
        final Calendar calendario= Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        anio = calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog  = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tFecha.setText(i2+"/"+i1+"/"+i);

            }
        }
                ,dia, mes,anio);

        datePickerDialog.show();

    }


    public void Aceptar(View view) {
        int id = view.getId();

        if (id == R.id.boton) {
            usuario = elogin.getText().toString();
            clave = epassword.getText().toString();
            clave2 = epassword_confirm.getText().toString();
            correo = ecorreo.getText().toString();
            fecha = tFecha.getText().toString();

            if (usuario.isEmpty() || clave.isEmpty() || clave2.isEmpty() || correo.isEmpty() ||
                    dataRadio.isEmpty() || dataCheck.isEmpty() || fecha.isEmpty() || ciudad.isEmpty()  ) {

                //esta vacio
                tInfo.setText("Campo vacío");

            } else if (!clave.equals(clave2)) {
                tInfo.setText("Las contraseñas no coinciden");

                clave = epassword.getText().toString();
                clave2 = epassword_confirm.getText().toString();

                //


            } else if (ciudad.equals("Select...")){
                tInfo.setText("Seleccione ciudad");

            } else {

                StringBuilder parrafo = new StringBuilder();
                parrafo.append("Nombre:" + usuario + "\n");
                // parrafo.append(System.getProperty("line.separator"));
                parrafo.append("Contraseña:" + clave + "\n");
                parrafo.append("Confirmacion:" + clave2 + "\n");
                parrafo.append("Correo:" + correo + "\n");
                parrafo.append("Sexo:" + dataRadio + "\n");
                parrafo.append("Pasatiempo:" + dataCheck + "\n");
                parrafo.append("Nacimiento." + fecha + "-" + ciudad);




                String resultado = parrafo.toString();

                tInfo.setText(resultado);

                //setContentView(R.layout.activity_main);
                //TextView textView = (TextView) findViewById(R.id.Mostrar);
                //textView.setText(resultado);
                // }
            }


        }

    }









}
