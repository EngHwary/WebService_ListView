package com.example.nh.webservice_listview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
String link="http://pastebin.com/raw/wgkJgazE";
URL url;
InputStream inputStream;
String result;
HttpURLConnection urlConnection;
ArrayList<Items> users=new ArrayList<>();
int likes;
String name;
String image;
Items_Adapter adapter;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn1);
        listView=findViewById(R.id.list1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask=new MyTask();
                myTask.execute(link);

            }
        });
    }
    public class MyTask extends AsyncTask<String, Void, ArrayList<Items>> {

        @Override
        protected ArrayList<Items> doInBackground(String... strings) {

            try {
                url=new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);
                urlConnection.setRequestMethod("GET");
                inputStream=urlConnection.getInputStream();
                int responseCode=urlConnection.getResponseCode();
                int c=0;

                StringBuffer buffer=new StringBuffer();
                if (responseCode==HttpURLConnection.HTTP_OK){

                    while ((c=inputStream.read()) !=-1){
                        buffer.append((char) c);
                    }
                }

                result=buffer.toString();
                inputStream.close();



                JSONArray array=new JSONArray(result);
                for (int i =0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);
                    likes=object.getInt("likes");
                    JSONObject object1=object.getJSONObject("user");
                    name=object1.getString("name");
                    JSONObject object2=object1.getJSONObject("profile_image");
                    image=object2.getString("medium");
                    users.add(new Items(name,likes,image));


                }





            } catch (Exception e) {
                e.printStackTrace();
            }

            finally {
                urlConnection.disconnect();


            }


            return users;
        }

        @Override
        protected void onPostExecute(ArrayList<Items> items) {
            super.onPostExecute(items);
            adapter =new Items_Adapter(MainActivity.this,items);
            listView.setAdapter(adapter);
        }
    }



}
