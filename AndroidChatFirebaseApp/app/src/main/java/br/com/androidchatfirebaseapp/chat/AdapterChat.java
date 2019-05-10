package br.com.androidchatfirebaseapp.chat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.androidchatfirebaseapp.R;

import java.util.List;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.myViewHold>  {

    private List<Mensagem> msg; // cria uma lista de objetos nessa caso da classe mensagem
    private Context context;
    private Bundle bundle;


    public AdapterChat(List<Mensagem> msg, Context context, Bundle bundle) {
        this.msg = msg;
        this.context = context;
        this.bundle = bundle;
    }

    public void addChat(Mensagem chat){
        msg.add(chat); // adiciona as novas mensagem a lista
        notifyItemInserted(msg.size()-1); //notifica o adapter que foi adicionada nova mensagem
    }


    @NonNull
    @Override
    public AdapterChat.myViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.card_chat,viewGroup,false); //cria linearLayout com o layout da nossa view
        return new myViewHold(layout); //retorna o  myViewHold com o layout
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChat.myViewHold myViewHold, int i) {

        Mensagem data = msg.get(i); //pega o objeto do index atual

        if (data.getUid().equals(bundle.getString("userId"))){ //verifica se a mensagem é do usuario atual ou de outro
            //se for do usuario
            myViewHold.main.setGravity(Gravity.END); // joga o texto para o canto esquerdo
            myViewHold.tvNome.setText(data.getNome()); //pega o nome
            myViewHold.tvNome.setTextColor(Color.parseColor("#0037FF")); //muda a cor do texto para azul
            myViewHold.tvMsg.setText(data.getMensagem()); //pega a mensag

        }else{
            //se for de outro usuario
            myViewHold.main.setGravity(Gravity.START); // joga o texto para o canto direito
            myViewHold.tvNome.setText(data.getNome()); //pega o nome
            myViewHold.tvNome.setTextColor(Color.parseColor("#FF0000")); //muda a cor do texto para vermelho
            myViewHold.tvMsg.setText(data.getMensagem()); //pega a mensag
        }

    }

    @Override
    public int getItemCount() {
        return msg == null ? 0 : msg.size(); //retorna a quantidade mensagens, se for nulla retorna 0
    }


    public class myViewHold extends RecyclerView.ViewHolder{

        TextView tvNome;
        TextView tvMsg;
        LinearLayout main;

        public myViewHold(@NonNull View itemView) {
            super(itemView);

            tvNome = itemView.findViewById(R.id.tvNome);
            tvMsg = itemView.findViewById(R.id.tvMsg);
            main = itemView.findViewById(R.id.main);

        }
    }

}
