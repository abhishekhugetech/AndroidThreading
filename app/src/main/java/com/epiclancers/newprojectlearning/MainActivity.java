package com.epiclancers.newprojectlearning;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    TextView textView;
    ProgressBar progressBar;
    String finalValue;
    String MESSAGE_KEY = "message_key";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                finalValue = bundle.getString(MESSAGE_KEY);
                textView.setText( finalValue );
                Toast.makeText(MainActivity.this, finalValue, Toast.LENGTH_SHORT).show();
                showProgressBar(false);
            }
        };

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                Runtime.getRuntime().freeMemory();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0 ; i < 100000 ; i ++ ){
                            finalValue = i+"";
                            System.out.println(finalValue);
                        }
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString( MESSAGE_KEY , "Hello there my friends, and the Number is " + finalValue);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                };
                Thread thread = new Thread(runnable , "Abhishek's Thread");
                thread.start();
            }
        });

    }

    /*
    This Method will be used to show or hide progress bar by passing a boolean object
    if true is passed then progress bar will be shown
    else if false is passed then the progress bar's visibility will be lost
     */
    private void showProgressBar(Boolean toShow){
        if (toShow){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }


}
