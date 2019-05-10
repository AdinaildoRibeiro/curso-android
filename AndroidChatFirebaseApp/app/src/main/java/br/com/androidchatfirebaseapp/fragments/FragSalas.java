package br.com.androidchatfirebaseapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.androidchatfirebaseapp.R;


public class FragSalas extends Fragment {


    Bundle bundle;
    Context context;
    View view;
    Button btnSala1;
    Button btnSala2;
    Button btnSala3;



    public FragSalas() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_frag_salas, container, false); //infla o layout dentro da view
        context = view.getContext();
        bundle = getArguments(); //pega os valores do bundle passado da activity
        //vincula os botões
        btnSala1 = view.findViewById(R.id.btnSala1);
        btnSala2 = view.findViewById(R.id.btnSala2);
        btnSala3 = view.findViewById(R.id.btnSala3);

        btnSala1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("sala","sala1"); // adiciona o valor nome da sala para nossa bundle
                loadRoom();
            }
        });
        btnSala2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("sala","sala2");
                loadRoom();
            }
        });
        btnSala3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("sala","sala3");
                loadRoom();
            }
        });
       return view; //retorna a view para que seja criada
    }


    public void loadRoom(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragChat frag_chat = new FragChat();
        frag_chat.setArguments(bundle);
        ft.addToBackStack(null);
        ft.replace(R.id.container, frag_chat);
        ft.commit();
    }



}
