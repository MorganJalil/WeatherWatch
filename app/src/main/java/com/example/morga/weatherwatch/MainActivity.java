package com.example.morga.weatherwatch;

import android.os.Bundle;

import android.os.StrictMode;

import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.util.Log;

import android.view.View;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;



import org.json.JSONException;

import org.json.JSONObject;



import java.io.IOException;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.Scanner;



public class MainActivity extends AppCompatActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)

                        .setAction("Action", null).show();

            }

        });





        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);



        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener(){



            @Override

            public void onClick(View v) {

                EditText editText = (EditText)findViewById(R.id.editText);

                String message = editText.getText().toString();





                try {



                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+message +
                            "uk&appid=4781378f7ed634b4ae430e81c58893b5&units=metric");

                    Scanner sc = new Scanner(url.openStream());

                    StringBuffer buf = new StringBuffer();

                    while(sc.hasNext()){

                        buf.append(sc.next());

                    }



                    //Log.v("test", buf.toString());

                    JSONObject jsonObject = new JSONObject(buf.toString());



                    JSONObject sysObj = jsonObject.getJSONObject("sys");

                    String country = sysObj.getString("country");



                    TextView countryText = (TextView)findViewById(R.id.country_text);

                    countryText.setText(country);



                    //temp

                    JSONObject tempObj = jsonObject.getJSONObject("main");

                    String temp = tempObj.getString("temp");



                    TextView tempText = (TextView)findViewById(R.id.temp_text);

                    tempText.setText(temp);



                    //pressure

                    JSONObject mainObj = jsonObject.getJSONObject("main");

                    String pressure = mainObj.getString("pressure");



                    TextView pressureText = (TextView)findViewById(R.id.pressure_text);

                    pressureText.setText(pressure);



                    //humidity

                    String humidity = mainObj.getString("humidity");



                    TextView humidityText = (TextView)findViewById(R.id.humidity_text);

                    humidityText.setText(humidity);





                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }catch (JSONException e) {

                    e.printStackTrace();

                }

            }

        });

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }



    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will

        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();



        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {

            return true;

        }



        return super.onOptionsItemSelected(item);

    }

}