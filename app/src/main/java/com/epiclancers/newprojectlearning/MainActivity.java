package com.epiclancers.newprojectlearning;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

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
                // Running the Async Task
                SomeBackgroundTask task = new SomeBackgroundTask();
                task.execute( "Rohan1" , "Ritik2" , "Raju3");

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("My App" + " is Closed");
    }

    /*
    Creating the Async Task class
     */
    class SomeBackgroundTask extends AsyncTask<String,String,String>{

        private static final String TAG = "My App";

        @Override
        protected String doInBackground(String... strings) {
            // Do all the Task in background
            for (String key : strings){
                Log.i(TAG, "The String is " + key);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Use this method to send data to onProgressUpdate method
            publishProgress("User : " );
            return null;
        }

        /*
        Override this method which will give you the data that
        you pass from publishProgress() method
         */
        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_SHORT).show();
        }
    }


}
