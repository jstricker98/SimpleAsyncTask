package com.example.simpleasync;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Integer, Integer, String> {

    private WeakReference<TextView> mTextView;

    private ProgressBar bar;

    SimpleAsyncTask(TextView tv, ProgressBar bar) {
        mTextView = new WeakReference<>(tv);
        this.bar = bar;
    }

    @Override
    protected String doInBackground(Integer... params) {
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        int sleepTime = s/10;

        for (int count = 0 ; count <= 10; count++) {
            try {
                Thread.sleep(sleepTime);
                publishProgress(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Awake at last after sleeping for " + s + "milliseconds!";
    }

    @Override
    protected void onPreExecute() {
        Log.i("created" , "onPreExecute()");
        super.onPreExecute();
    }

   @Override
    protected void onProgressUpdate(Integer... values) {
        bar.setProgress(values[0]);
    }

    protected void onPostExecute(String result) {
        bar.setVisibility(View.GONE);
        mTextView.get().setText(result);
    }
    //endfile
}
