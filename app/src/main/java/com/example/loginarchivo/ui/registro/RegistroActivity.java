package com.example.loginarchivo.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginarchivo.R;
import com.example.loginarchivo.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private EditText etDni,etApellido,etNombre,etMail,etPassword;
    private Button btnGuardar;
    private ViewModelRegistro vm;
    private boolean esta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        btnGuardar = findViewById(R.id.btnActualizarPerfil);

       vm= AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario=new Usuario();
                usuario.setDni(etDni.getText().toString());
                usuario.setApellido(etApellido.getText().toString());
                usuario.setNombre(etNombre.getText().toString());
                usuario.setMail(etMail.getText().toString());
                usuario.setPassword(etPassword.getText().toString());
                vm.guardar(usuario);
            }
        });
        vm.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni()+"");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etMail.setText(usuario.getMail());
                etPassword.setText(usuario.getPassword());
            }
        });
        Intent i = getIntent();
        vm.traerDatos(i.getStringExtra("correo"));
    }

}
