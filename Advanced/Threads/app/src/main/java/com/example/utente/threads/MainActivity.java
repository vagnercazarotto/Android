package com.example.utente.threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoIniciar;

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
        MyRunnable myRunnable = new MyRunnable();
        new Thread( myRunnable).run();
    }

    public void finishThread(View view){ }

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
            for(int i = 0; i <= 15; i++){
                Log.d("Thread", "contador:  " +i);
                botaoIniciar.setText("contador:  " +i);
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
