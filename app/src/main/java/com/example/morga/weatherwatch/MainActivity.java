package com.example.morga.weatherwatch;

import android.content.DialogInterface;
import android.os.Bundle;

import android.os.StrictMode;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.util.Log;

import android.view.LayoutInflater;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_layout);

        //setSupportActionBar(toolbar);








        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);}



    public void newSearch(View view) {

        LayoutInflater myInflater = LayoutInflater.from(this);
        View alertLayout = myInflater.inflate(R.layout.new_search, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);

        final EditText editText = (EditText)alertLayout.findViewById(R.id.edit_text);


        //final String message = editText.getText().toString();





        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

/*                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Cancelled", Snackbar.LENGTH_LONG);

                snackbar.show();*/

            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int id) {



                if (editText.getText().toString().isEmpty()) {


                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Please enter a city", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else {

                    try {

                        final String message = editText.getText().toString();



                        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+message +
                                "&lang=se" + "uk&appid=4781378f7ed634b4ae430e81c58893b5&units=metric");

                        Scanner sc = new Scanner(url.openStream());

                        StringBuffer buf = new StringBuffer();

                        while(sc.hasNext()){

                            buf.append(sc.next());

                        }



                        //Log.v("test", buf.toString());

                        JSONObject jsonObject = new JSONObject(buf.toString());


                        // country
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


                    /*Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "New thing to do added", Snackbar.LENGTH_LONG);
                    snackbar.show();*/
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();



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