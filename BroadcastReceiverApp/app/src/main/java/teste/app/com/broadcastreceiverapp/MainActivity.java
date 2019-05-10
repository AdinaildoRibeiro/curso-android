package teste.app.com.broadcastreceiverapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    /**Chamado quando a primeira activity é criada */
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // broadcast intent customizado.

    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("com.bybstartexpress.CUSTOM_INTENT"); sendBroadcast(intent);
    }
}