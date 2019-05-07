package br.com.listviewapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.list_item);

        String[] elementos = getResources().getStringArray(R.array.numero_elementos);

        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, elementos ));

        ListView lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String elemento = ((TextView) view).getText().toString();

                Intent i = new Intent(getApplicationContext(),SingleListItem.class);
                i.putExtra("elemento", elemento);
                startActivity(i);
            }
        });


    }
}
