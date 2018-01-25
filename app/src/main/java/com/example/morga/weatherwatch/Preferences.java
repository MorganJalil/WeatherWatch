package com.example.morga.weatherwatch;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Morga on 2017-05-08.
 */

public class Preferences {

    SharedPreferences prefs;

    public Preferences(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    //Första staden som syns när man startar appen för första gången
    String getCity() {
        return prefs.getString("city", "Stockholm, SE");
    }

    void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }
}