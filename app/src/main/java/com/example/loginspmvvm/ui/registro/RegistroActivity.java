package com.example.loginspmvvm.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginspmvvm.R;
import com.example.loginspmvvm.model.Usuario;
import com.example.loginspmvvm.ui.login.MainActivity;

public class RegistroActivity extends AppCompatActivity {

    EditText dni;
    EditText nombre;
    EditText apellido;
    EditText email;
    EditText password;
    TextView msjerror;
    ViewModelRegistro viewModelRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializar();

        viewModelRegistro.geUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni() + "");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getMail());
                password.setText(usuario.getPassword());
            }
        });
        if (getIntent().getExtras().getInt("") == 1) {
            viewModelRegistro.mostrar(getApplicationContext());
        }
    }

    public void inicializar() {
        dni = findViewById(R.id.dni);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        msjerror = findViewById(R.id.msjreg);
        viewModelRegistro = ViewModelProviders.of(this).get(ViewModelRegistro.class);
    }

    public void guardar(View view) {
        Usuario us = new Usuario();
        if (!dni.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            us.setDni(Long.parseLong(dni.getText().toString()));
            us.setNombre(nombre.getText().toString());
            us.setApellido(apellido.getText().toString());
            us.setMail(email.getText().toString());
            us.setPassword(password.getText().toString());
            viewModelRegistro.guardar(getApplicationContext(), us);
            msjerror.setText("Datos guardados");
            Intent ingresar = new Intent(this, MainActivity.class);

            startActivity(ingresar);

        } else {
            msjerror.setText("Todos los campos deben estar completos");
        }
    }

}