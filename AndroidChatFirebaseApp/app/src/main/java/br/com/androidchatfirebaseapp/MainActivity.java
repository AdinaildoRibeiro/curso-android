package br.com.androidchatfirebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



    EditText edEmail;
    EditText edSenha;
    Button btnLogar;
    Button btnRegistro;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnRegistro = findViewById(R.id.btnRegistro);

        mAuth = FirebaseAuth.getInstance();

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityRegistro.class); //instancia a activity
                startActivity(i); //inicia a activity instanciada
            }
        });




    }


    public void login(){
        if (edSenha.getText().toString().length() > 0 || edEmail.getText().toString().length() > 0){

            mAuth.signInWithEmailAndPassword(edEmail.getText().toString(),edSenha.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "logado com sucesso" + task.getResult().getUser().getUid(), Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("userId",task.getResult().getUser().getUid());
                                startActivity(new Intent(getApplicationContext(), ActivityChat.class).putExtras(bundle));
                                finish();


                            }else{
                                Toast.makeText(getApplicationContext(), "Login inválidos", Toast.LENGTH_SHORT).show();
                                //envia uma mensagem ao usuario o avisando
                                Log.d("console", String.valueOf(task.getException()));
                            }

                        }
                    });
        }else {

            Toast.makeText(getApplicationContext(), "e-mail ou senha inválidos", Toast.LENGTH_SHORT).show(); //envia um mensagem temporaria para o usuario
        }
    }
}
