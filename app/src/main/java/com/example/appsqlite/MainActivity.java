package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtEmail, txtSenha, txtConfirmaSenha;
    Button btnRegistar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtEmail = findViewById(R.id.idEmail);
        txtSenha = findViewById(R.id.idSenha);
        txtConfirmaSenha = findViewById(R.id.idConfirmaSenha);

        btnRegistar = findViewById(R.id.idBtnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha, confirmaSenha;

                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();
                confirmaSenha = txtConfirmaSenha.getText().toString();

                if (email.equals("") || senha.equals("") || confirmaSenha.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor inserir valores!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmaSenha)) {
                        Boolean checharEmail = db.validarEmail(email);
                        if (checharEmail == true) {
                            Boolean inserir = db.insert(email, senha);
                            if (inserir == true) {
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email inserido já existe!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Senha não confere!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
