package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtLoginEmail, txtLoginSenha;
    Button btnLoginEntrar;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        txtLoginEmail = findViewById(R.id.idLoginEmail);
        txtLoginSenha = findViewById(R.id.idLoginSenha);


        db = new DatabaseHelper(this);

        btnLoginEntrar = findViewById(R.id.idBtnLoginEntrar);

        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha;

                email = txtLoginEmail.getText().toString();
                senha = txtLoginSenha.getText().toString();

                Boolean checarEmailSenha = db.checarEmailSenha(email,senha);

                if (checarEmailSenha==true){
                    Log.i("btnLoginEntar","Cliquei no botão entrar do login");
                  //  Toast.makeText(getApplicationContext(),"Acesso autorizado!!!", Toast.LENGTH_SHORT).toString();
                    Intent intent = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Acesso negado!!!", Toast.LENGTH_SHORT).toString();
                }


            }
        });
    }
}
