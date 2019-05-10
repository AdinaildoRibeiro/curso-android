package br.com.androidchatfirebaseapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.androidchatfirebaseapp.fragments.FragSalas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActivityChat extends AppCompatActivity {

    Context context;
    Bundle bundle;
    FirebaseFirestore db = FirebaseFirestore.getInstance(); //conexão com o banco


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        context = this;  // inicia a variavel com o contexto atual
        bundle = getIntent().getExtras(); // pega os valores passado da actvity anterior
        CheckNick();

    }

    public void CheckNick(){

        db.collection("usuarios").document(bundle.getString("userId")) //adiciona o nome da nossa coleção no caso usuario, e seu id onde estarão as infos
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) { //verifica se o usuario já está no nosso banco de dados
                        //caso já exista
                        bundle.putString("nick",document.get("nome").toString()); //adiciona o nick dentro do bundle, que esta armazenado no banco
                        loadFrag();
                    } else {
                        final EditText edittext = new EditText(context); //cria um edittext
                        final AlertDialog alert = new AlertDialog.Builder(context) // cria um alertDialog
                                .setMessage("Por favor escolha um nickname") // adiciona uma mensagem ao alertDialog
                                .setTitle("nickname") // adiciona um titulo ao alertDialog
                                .setView(edittext) // adiciona o edittext ao alertDialog
                                .setCancelable(false) //torna o alertDialog apenas fechavel quando for completo
                                .setPositiveButton("Feito", null) //adiciona um botão com evento clique null
                                .show();

                        Button btnFeito = alert.getButton(AlertDialog.BUTTON_POSITIVE); //cria um botão e o vincula com o do alertDialog
                        btnFeito.setOnClickListener(new View.OnClickListener() { //o cria o evento clique do botão
                            @Override
                            public void onClick(View view) {
                                if (edittext.getText().toString().length() != 0){ //verifica se o campo não esta vazio
                                    //caso não esteja
                                    setNick(edittext.getText().toString());
                                    alert.dismiss(); // fecha o alertDialog
                                }else {
                                    //caso esteja
                                    Toast.makeText(context, "Por favor adicione um nick", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } else {
                    Log.e("console", "error: ", task.getException());
                }
            }
        });


    }


    public void setNick(final String nick){
        final ProgressDialog progressDialog = new ProgressDialog(context); //cria um progressDialog
        progressDialog.setCancelable(false); // o bloqueia o cancelamento dele
        progressDialog.setMessage("Aguarde"); //adiciona uma mensagem ao progressDialog
        progressDialog.show(); //o exibe

        Map<String, String> user = new HashMap<>(); // cria um hashmap onde iremos armazenar as informaçoes que seram enviadas
        user.put("nome", nick); //adicionamos o valor, sendo o primeiro o nome do campo e depois o valor

        db.collection("usuarios").document(bundle.getString("userId")) //adiciona o nome da nossa coleção no caso usuario, e seu id onde iremos inserir as infos
                .set(user) // adiciona o hashMap com as informaçoes
                .addOnSuccessListener(new OnSuccessListener<Void>() { //verifica se foi inserido com sucesso
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("console", "Adicionado com sucesso");
                        bundle.putString("nick",nick); //adiciona o nick dentro do bundle
                        progressDialog.dismiss(); // remove o progressDialog
                        loadFrag();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { //caso algo ocorra errado
                Log.w("console", "Error", e);
                progressDialog.dismiss(); // remove o progressDialog
            }
        });

    }

    public void loadFrag(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragSalas frag_salas = new FragSalas(); // cria o fragment
        frag_salas.setArguments(bundle); //adiciona o bundle no fragment
        ft.add(R.id.container, frag_salas); //adiciona o fragment no FrameLayout

        ft.commit(); //inicia a Transição
    }


}
