package tkcode.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // A normal List Adapter
        String[] foods = {"Bacon","Ham","Tuna","Candy","Meatball","Potato","Bacon","Ham","Tuna","Candy","Meatball","Potato"};
        ListAdapter listAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,foods);
        ListView listView = (ListView) findViewById(R.id.listaItem);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                String food = String.valueOf(parent.getItemAtPosition(position));
                                                Toast.makeText(MainActivity.this,food,Toast.LENGTH_SHORT).show();
                                            }
                                        });


    }

}
