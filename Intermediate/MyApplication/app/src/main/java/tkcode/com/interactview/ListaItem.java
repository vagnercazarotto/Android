package tkcode.com.interactview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        ArrayList<item> lista = new ArrayList<item>();

        item a = new item(R.drawable.a, "Item A","Example item A");
        item b = new item(R.drawable.b, "Item B","Example item B");
        item c = new item(R.drawable.c, "Item C","Example item C");
        item d = new item(R.drawable.d, "Item D","Example item D");

        lista.add(a);
        lista.add(b);
        lista.add(c);
        lista.add(d);

        ListaAdapterItem adapterItem = new ListaAdapterItem(this,lista);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapterItem);
    }

}
