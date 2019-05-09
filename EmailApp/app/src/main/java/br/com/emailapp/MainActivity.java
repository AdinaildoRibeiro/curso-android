package br.com.emailapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    protected void sendEmail(){
        Log.i("Enviar email", "");
        String[] To = {""};
        String[] Cc = {""};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailTo:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,To);
        emailIntent.putExtra(Intent.EXTRA_CC,Cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Assunto");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Escreva seu email");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
            Log.i("Terminando...","");
        }catch (ActivityNotFoundException ex){
            Toast.makeText(MainActivity.this, "Não Existe um cliente de email instalado.", Toast.LENGTH_SHORT).show();
        }
    }
}
