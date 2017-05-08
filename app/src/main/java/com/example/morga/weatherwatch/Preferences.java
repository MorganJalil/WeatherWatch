package com.example.morga.weatherwatch;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Morga on 2017-05-08.
 */

public class Preferences {

        SharedPreferences prefs;

        public Preferences(Activity activity){
            prefs = activity.getPreferences(Activity.MODE_PRIVATE);
        }

        // If the user has not chosen a city yet, return
        // Sydney as the default city
        String getCity(){
            return prefs.getString("city", "Stockholm, SE");
        }

        void setCity(String city){
            prefs.edit().putString("city", city).commit();
        }
}