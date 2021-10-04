package com.example.loginspmvvm.ui.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginspmvvm.model.Usuario;
import com.example.loginspmvvm.request.ApiClient;

public class ViewModelRegistro extends AndroidViewModel {
    public ViewModelRegistro(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<Usuario> usuario;

    public LiveData<Usuario> geUsuario(){
        if(usuario==null){
            usuario= new MutableLiveData<>();

        }
        return usuario;
    }

    public void guardar(Context context, Usuario usuario ){
        ApiClient.guardar(context,usuario);
    }

    public void mostrar(Context context){
        Usuario us= ApiClient.leer(context);
        usuario.setValue(us);
    }
}
