package com.example.shop;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class RandomAsyncTask extends AsyncTask<Void,Void,String> {
    private WeakReference<TextView> mTextView;
    public RandomAsyncTask(TextView textView) {
        mTextView = new WeakReference<>(textView);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int number = random.nextInt(11);
        int ms = number*100;

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Bejelentkezás vendégként" + ms + " után";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTextView.get().setText(s);
    }
}
