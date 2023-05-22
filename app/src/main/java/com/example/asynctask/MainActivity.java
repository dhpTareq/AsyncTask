package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText time;
    private TextView finalRsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.in_time);
        button = findViewById(R.id.btn_run);
        finalRsult = findViewById(R.id.tv_result);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);
            }
        });
    }
    public class AsyncTaskRunner extends AsyncTask<String, String,String> {
        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping...");
            try {
                int time = Integer.parseInt(strings[0]) * 1000;
                Thread.sleep(time);
                resp = "slept for " + strings[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for" + time.getText().toString() + " seconds");

        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            finalRsult.setText(s);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            finalRsult.setText(values[0]);
        }


    }
}