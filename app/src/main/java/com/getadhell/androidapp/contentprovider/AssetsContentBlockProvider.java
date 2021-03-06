package com.getadhell.androidapp.contentprovider;

import android.content.Context;
import android.util.Log;

import com.getadhell.androidapp.model.BlockDb;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetsContentBlockProvider {
    private final String LOG_TAG = AssetsContentBlockProvider.class.getCanonicalName();
    private Context mContext;
    private Gson gson;

    public AssetsContentBlockProvider(Context context) {
        this.gson = new Gson();
        this.mContext = context;
    }

    public BlockDb getBlockDb(String assetName) {
        Log.d(LOG_TAG, "Entering getBlockDb()");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open(assetName)));
            return gson.fromJson(reader, BlockDb.class);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to read from assets", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Failed to close reader", e);
                }
            }
        }
        return null;
    }
}
