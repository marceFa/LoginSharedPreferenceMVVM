package com.example.loginspmvvm.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginspmvvm.model.Usuario;
import com.example.loginspmvvm.request.ApiClient;

public class ViewModelMain extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;

    public ViewModelMain(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario= new MutableLiveData<>();

        }
        return usuario;
    }
    public void Ingresar (Context context, String mail, String password){
        Usuario us = ApiClient.login(context,mail,password);
        usuario.setValue(us);
    }
}
