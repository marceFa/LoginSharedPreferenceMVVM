package com.example.loginspmvvm.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginspmvvm.R;
import com.example.loginspmvvm.model.Usuario;
import com.example.loginspmvvm.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    private Button btRegistrar ;
    private Button btLogin;
    private EditText mail;
    private EditText password;
    private TextView msjerror;
    private ViewModelMain viewModelMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

        viewModelMain.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if(usuario!=null ){
                    Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                    intent.putExtra("",1);
                    startActivity(intent);
                }else{
                    msjerror.setText("Usuario Inexistente o Datos Incorrectos");
                }
            }
        });
    }
    public void inicializar(){
        btLogin= findViewById(R.id.login);
        btRegistrar = findViewById(R.id.registrar);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.password);
        msjerror= findViewById(R.id.msjact);
        viewModelMain = ViewModelProviders.of(this).get(ViewModelMain.class);

    }

    public void Ingresar(View view){

        String email = mail.getText().toString();
        String pass = password.getText().toString();
        if(!email.isEmpty() && !pass.isEmpty()){
            viewModelMain.Ingresar(this,email,pass);
        }else{
            msjerror.setText  ("Debe completar los campos email y contraseña para ingresar");

        }


    }
    public void Registrarse (View view){
        Intent registrar = new Intent(this, RegistroActivity.class);
        registrar.putExtra("registrarse",0);
        startActivity(registrar);
    }
}
