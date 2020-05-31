package com.example.loginarchivo.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginarchivo.model.Usuario;
import com.example.loginarchivo.request.ApiClient;
import com.example.loginarchivo.ui.login.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewModelRegistro extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private Context context;
    private Usuario usuario;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutableLiveData() {
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }


    public  void guardar(Usuario usuario){
        ApiClient.guardar(context,usuario);
        Intent i=new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    public void traerDatos(String correoBuscado){
        if(!correoBuscado.equals("vacio")) {
            usuario = ApiClient.leer(context, correoBuscado);
            usuarioMutableLiveData.setValue(usuario);
        }
    }
}
