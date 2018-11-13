package com.example.utente.asynctask;


import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utente.asynctask.api.CEPService;
import com.example.utente.asynctask.api.DataService;
import com.example.utente.asynctask.model.CEP;
import com.example.utente.asynctask.model.Foto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;
    private Retrofit retrofit;
    private List<Foto> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.buttonAsync);
        textoResultado = findViewById(R.id.textView);

        retrofit = new Retrofit.Builder()
//                .baseUrl("https://viacep.com.br/ws/")
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //recuperarCEPRetrofit();
                recuperarListaRetrofit();


//                MyTask myTask = new MyTask();
//                String url = "https://blockchain.info/ticker";
//                String urlCEP = "https://viacep.com.br/ws/01001000/json";
//                myTask.execute(url);
            }
        });


    }

    private void recuperarListaRetrofit(){
        DataService service = retrofit.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if(response.isSuccessful()){
                    listaFotos = response.body();

                    for(int i=0; i<listaFotos.size(); i++){
                        Foto foto = listaFotos.get(i);
                        Log.d("resultado", "resultado: " + foto.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }



    private void recuperarCEPRetrofit() {
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> cepCall = cepService.recuperarCEP("01001000");

        cepCall.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    textoResultado.setText(cep.getLogradouro() + " / " + cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }


    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String uf = null;
            String valorReal = null;

            try {
//                JSONObject jsonObject = new JSONObject(resultado);
//                logradouro = jsonObject.getString("logradouro");
//                cep = jsonObject.getString("cep");
//                complemento = jsonObject.getString("complemento");
//                bairro = jsonObject.getString("bairro");
//                localidade = jsonObject.getString("localidade");
//                uf = jsonObject.getString("uf");

                JSONObject jsonObject = new JSONObject(resultado);
                valorReal = jsonObject.getString("BRL");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            textoResultado.setText(valorReal);
//            textoResultado.setText(logradouro + " / " + cep + " / " + complemento + " / " + bairro + " / " + localidade + " / " + uf);
        }

        @Override
        protected String doInBackground(String... strings) {
            String stringURL = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {
                URL url = new URL(stringURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader reader = new BufferedReader(inputStreamReader);
                String linha = "";
                buffer = new StringBuffer();
                while ((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toString();
        }
    }


}
