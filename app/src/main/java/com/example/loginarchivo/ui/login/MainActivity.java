package com.example.loginarchivo.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginarchivo.R;
import com.example.loginarchivo.model.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnEnviar;
    private Button btnRegistrar;
    private TextView tvMensaje;
    private ViewModelMain vm;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);

        iniciarVista();
    }
    private void iniciarVista(){
        etUsuario=findViewById(R.id.etMailLogueo);
        etPassword=findViewById(R.id.etPasswordLogueo);
        tvMensaje=findViewById(R.id.tvMensajeLogueo);
        btnEnviar=findViewById(R.id.btnEnviarLogueo);
        btnRegistrar=findViewById(R.id.btnRegistro);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.buscarUsuario(etUsuario.getText().toString(),etPassword.getText().toString());
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registrarUsuario(etUsuario.getText().toString(),etPassword.getText().toString());
            }
        });
    }
}
