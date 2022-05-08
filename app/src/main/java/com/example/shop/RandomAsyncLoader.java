package com.example.shop;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.Random;

public class RandomAsyncLoader extends AsyncTaskLoader<String> {
    public RandomAsyncLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Random r = new Random();
        int n = r.nextInt(20);
        int ms = n * 1000;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hehe";
    }
}