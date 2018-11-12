package tkcode.com.interactview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void click(View view){
        TextView editText = (TextView) findViewById(R.id.textView);
        TextView textView = (TextView) findViewById(R.id.textView2);

        textView.setText(editText.getText());
    }

    public void abreLista(View view){
        Intent intent = new Intent(this,ListaAdapterItem.class);
        startActivity(intent);
    }

}
