package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {

    private EditText    editLoginUsuario;
    private EditText    editLoginSenha;
    private Button      botaoLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLoginUsuario    = (EditText) findViewById(R.id.edit_login_usuario);
        editLoginSenha      = (EditText) findViewById(R.id.edit_login_senha);
        botaoLogar          = (Button) findViewById(R.id.button_logar);

        //ParseUser.logOut();

        //Verificar usuario esta logado
        verificarUsuarioLogado();

        //adicionar evento click no botao logar
        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editLoginUsuario.getText().toString();
                String senha = editLoginSenha.getText().toString();

                verifivarLogin(usuario, senha);

            }
        });
    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity( intent );
    }


    private void verificarUsuarioLogado(){

        if(ParseUser.getCurrentUser() != null){
            //enviar usuario para área principal
            abrirAreaPrincipal();
        }

    }

    private void abrirAreaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity( intent );
        finish();
    }

    private void verifivarLogin(String usuario, String senha){

        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e == null){ //secesso no login
                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show();
                    abrirAreaPrincipal();
                }else{//erro ao logar
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
