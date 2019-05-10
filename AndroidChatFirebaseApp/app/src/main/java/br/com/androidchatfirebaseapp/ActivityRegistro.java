package br.com.androidchatfirebaseapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistro extends AppCompatActivity {



    EditText edEmail;
    EditText edSenha;
    Button btnRegistro;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        btnRegistro = findViewById(R.id.btnRegistrar);

        mAuth = FirebaseAuth.getInstance();


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }


    public void cadastrar(){

        if (edEmail.getText().toString().length() > 0 || edSenha.getText().toString().length() >0){
            mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(),edSenha.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()){
                                Toast.makeText(ActivityRegistro.this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();
                                // envia uma mensagem temporaria para o usuario


                                finish();  //finaliza a activity atual

                            }else{
                                Toast.makeText(ActivityRegistro.this, "Ocorreu um erro ao se cadastrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else {

            Toast.makeText(getApplicationContext(), "Campos invalidos", Toast.LENGTH_SHORT).show();

        }
    }
}
