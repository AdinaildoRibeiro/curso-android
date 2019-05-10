package br.com.androidchatfirebaseapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.androidchatfirebaseapp.chat.AdapterChat;
import br.com.androidchatfirebaseapp.chat.Mensagem;
import br.com.androidchatfirebaseapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FragChat extends Fragment {


    Bundle bundle;
    View view;
    Context context;
    RecyclerView rcChat;
    RecyclerView.LayoutManager manager;
    EditText edMsg;
    Button btnSend;

    DatabaseReference myRef; //referencia ao database
    List<Mensagem> dataSet;  //uma lista com os objetos mensagem
    AdapterChat adapterChat;
    FirebaseDatabase database;

    public FragChat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag_chat, container, false); //infla o nosso layout dentro do view
        context = view.getContext();
        bundle = getArguments();
        rcChat = view.findViewById(R.id.rcChat);
        btnSend = view.findViewById(R.id.btnSend);
        edMsg = view.findViewById(R.id.edText);

        manager = new LinearLayoutManager(context); //cria um gerenciador para o linearLayout

        rcChat.setLayoutManager(manager);  // adiciona o gerenciador ao RecyclerView
        dataSet = new ArrayList<>();  //inicializa a nossa lista
        adapterChat = new AdapterChat(dataSet,context,bundle);  //cria o adapter passando o nosso dataset, contexto e bundle
        rcChat.setAdapter(adapterChat);  //adiciona o adapter ao ao RecyclerView


        database = FirebaseDatabase.getInstance(); //instancia o banco
        myRef = database.getReference().child("chats").child(bundle.getString("sala")); // pega a referencia do banco, e da sala
        myRef.addChildEventListener(new ChildEventListener() { //cria o evento
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //senpre que uma nova mensagem for adiciona, será colocada no nosso adapter
                Mensagem chat = dataSnapshot.getValue(Mensagem.class); //pega todas e as mensagens recentes adicionada ao banco
                ((AdapterChat) adapterChat).addChat(chat);  // adiciona a mensagem ao nosso adapter
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //executa se alguma mensagem foi mudada
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                //executa se alguma mensagem foi removida
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //executa se alguma mensagem foi movida
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //executa se alguma mensagem foi cancelada
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edMsg.getText().toString(); //coloca o texto do edite na variavel

                if (text.length() != 0  ){ //verefica se a mensagem não está vazia
                    SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // cria um formato de data e/ou hora
                    Date date = new Date(); //instancia a classe date
                    String data = formataData.format(date); //coloca o data formatada dentro de uma string

                    //cria a classe Mensagem com o construtor passando
                    Mensagem msg = new Mensagem(bundle.getString("nick"), //o nome
                            text, // o texto
                            bundle.getString("userId"), // o id unico
                            data); // e a data
                    DatabaseReference re  = database.getReference().child("chats").child(bundle.getString("sala")) //referencia onde será armazenada a mensagem
                            .push(); //cria um id de mensagem aleatoria
                    re.setValue(msg); // adiciona a mensagem no banco

                    edMsg.setText(""); // limpa a caixa de texto
                }
            }
        });


        return view;
    }



}
