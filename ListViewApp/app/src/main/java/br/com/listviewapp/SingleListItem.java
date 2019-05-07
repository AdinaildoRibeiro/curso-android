package br.com.listviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SingleListItem extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_item);

        TextView txtElemento = (TextView) findViewById(R.id.elemento_label);

        Intent i = getIntent();

        String elemento = i.getStringExtra("elemento");

        txtElemento.setText(elemento);
    }
}
