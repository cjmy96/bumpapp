package com.bump.chris.bump.network;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.bump.chris.bump.activities.BumpActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by chris on 18/07/16.
 */
public class AuthenticatorTask extends AsyncTask<Void, Void, String> {
    private static final String LOG_TAG = "AuthenticatorTask";
    public static final String PREF_USERNAME = "username";
    private final Context mContext;
    ProgressDialog mProgressDialog;
    Map<String, String> mCredentials;

    public AuthenticatorTask(Context context, Map<String, String> credentials) {
        mContext = context;
        mCredentials = credentials;
    }


    @Override
    protected String doInBackground(Void... voids) {
        Authenticator authenticator = new Authenticator(mCredentials);
        String result = authenticator.authenticate();
        mProgressDialog.dismiss();
        return result;
    }

    protected void onPreExecute(){
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Authenticating");
        mProgressDialog.show();
    }

    protected void onPostExecute(String resultJson) {
        if (resultJson == null) {
            return;
        }
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        System.out.println(resultJson);
        Map<String, String> jsonMap = gson.fromJson(resultJson, type);
        if (jsonMap.get("status").equals("error")) {
            Log.d(LOG_TAG, "Error!");

        } else {
            if (jsonMap.get("isAuthentic").equals("true")) {
                PreferenceManager.getDefaultSharedPreferences(mContext)
                        .edit()
                        .putString(PREF_USERNAME, mCredentials.get("username"))
                        .apply();
                Intent i = new Intent(mContext, BumpActivity.class);
                mContext.startActivity(i);
            } else {

            }

        }
        mProgressDialog.dismiss();
    }




}
