package com.example.loginarchivo.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.loginarchivo.model.Usuario;
import com.example.loginarchivo.request.ApiClient;
import com.example.loginarchivo.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    private Usuario usuario;

    public  ViewModelMain(@NonNull Application application){
        super(application);
        context=application.getApplicationContext();
    }

    public void buscarUsuario(String mailBuscado,String passwordBuscado){
        if(mailBuscado.equals("") || passwordBuscado.equals(""))
            Toast.makeText(context, "Complete los datos", Toast.LENGTH_LONG).show();
        else
        {   boolean rta = ApiClient.login(context, mailBuscado, passwordBuscado);
            if(rta) {
                Intent i = new Intent(context, RegistroActivity.class);
                i.putExtra("correo",mailBuscado);
                context.startActivity(i);
            }
            else Toast.makeText(context, "Datos INCORRECTOS", Toast.LENGTH_LONG).show();
        }

    }
    public void registrarUsuario(String mailBuscado,String passwordBuscado){
        if(!mailBuscado.equals("") && !passwordBuscado.equals(""))
        {
            boolean rta = ApiClient.login(context, mailBuscado, passwordBuscado);
            if(rta) {
                Toast.makeText(context, "Ya esta registrado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, RegistroActivity.class);
                i.putExtra("correo",mailBuscado);
                context.startActivity(i);
            }
            else registrar();
        }
        else registrar();
    }

    public void registrar(){
        Intent i = new Intent(context, RegistroActivity.class);
        i.putExtra("correo","vacio");
        context.startActivity(i);
    }

}
