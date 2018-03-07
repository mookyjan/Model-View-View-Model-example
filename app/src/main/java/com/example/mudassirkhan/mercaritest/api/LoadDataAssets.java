package com.example.mudassirkhan.mercaritest.api;

import android.content.Context;

import com.example.mudassirkhan.mercaritest.model.DataResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Mudassir Khan on 2/24/2018.
 */

public class LoadDataAssets {

    public LoadDataAssets(){

    }

    public DataResponse getJsonResponse(Context context, String fileNeme){
        // 1 networking code will replace this
        String json = loadJSONFromAsset(fileNeme, context);

        // 2 parse json into model objects
        return new Gson().fromJson(json, DataResponse.class);
    }

    private String loadJSONFromAsset(String fileName, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
