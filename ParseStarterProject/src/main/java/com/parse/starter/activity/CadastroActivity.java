package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.util.ParseErros;

public class CadastroActivity extends AppCompatActivity {

    private EditText    textoNome;
    private EditText    textoEmail;
    private EditText    textoSenha;
    private TextView    facaLogin;
    private Button      botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textoNome       = (EditText) findViewById(R.id.text_nome);
        textoEmail      = (EditText) findViewById(R.id.text_email);
        textoSenha      = (EditText) findViewById(R.id.text_senha);
        facaLogin       = (TextView) findViewById(R.id.text_faca_login);
        botaoCadastrar  = (Button) findViewById(R.id.botao_cadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
        facaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginUsuario();
            }
        });

    }

    private void cadastrarUsuario(){
        ParseUser usuario = new ParseUser();
        usuario.setUsername( textoNome.getText().toString() );
        usuario.setEmail( textoEmail.getText().toString() );
        usuario.setPassword( textoSenha.getText().toString() );

        //Salvar dados usuario
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(CadastroActivity.this,"Cadastro feito com  sucesso!",Toast.LENGTH_LONG).show();
                    abrirLoginUsuario();
                }else{
                    ParseErros parseErros = new ParseErros();
                    String erro = parseErros.getErro( e.getCode() );
                    Toast.makeText(CadastroActivity.this, erro,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity( intent );
        finish();
    }

}
