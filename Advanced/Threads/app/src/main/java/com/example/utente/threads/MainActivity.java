package com.example.utente.threads;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoIniciar;
    private int numero;
    private Handler handler = new Handler();
    private boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = findViewById(R.id.buttonIniciar);
    }

    public void startThread(View view){
//        MyThread thread = new MyThread();
//        thread.run();
//
        pararExecucao = false;
        MyRunnable myRunnable = new MyRunnable();
        new Thread( myRunnable).start();
    }

    public void finishThread(View view){
        pararExecucao = true;
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i <= 15; i++){
                Log.d("Thread", "contador:  " +i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class MyRunnable implements Runnable{
        @Override
        public void run() {

            for(numero = 0; numero <= 15; numero++){
                Log.d("Thread", "contador:  " + numero);


                if(pararExecucao) {
                    return;
                }

                // so the work in the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("contador:  " + numero);
                    }
                });


//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        botaoIniciar.setText("contador:  " + numero);
//                    }
//                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
