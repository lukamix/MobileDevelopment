package com.ducnb.uigmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.ducnb.uigmail.data.Gmail;
import com.ducnb.uigmail.data.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerGmail;
    private UserAdapter mUserAdapter;
    private ArrayList<Users> mUsers;
    private JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerGmail = findViewById(R.id.gmail_view);
        mUsers = new ArrayList<>();
        new JsonTask().execute("https://lebavui.github.io/jsons/users.json");

    }
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }
                jsonArray = new JSONArray(buffer.toString());
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject tmp = (JSONObject) jsonArray.get(i);
                    mUsers.add(Utils.Mapping(tmp));
                }
                mUserAdapter = new UserAdapter(MainActivity.this,mUsers);
                mRecyclerGmail.setAdapter(mUserAdapter);
                mRecyclerGmail.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}