package com.example.morga.weatherwatch;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        //menyknappen, vit färg och -->change city
        getMenuInflater().inflate(R.menu.weather, menu);
        Drawable drawable = menu.findItem(R.id.change_city).getIcon();
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }
            return true;

        }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.change_city){

            showInputDialog();
        }
        return false;



    }

// När man trycker på menyknappen startar detta
    private void showInputDialog() {

        int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);

        } catch (GooglePlayServicesRepairableException |

        GooglePlayServicesNotAvailableException e){
            // TODO: Handle the error.
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);
            //latlong = place.getLatLng();
            changeCity(place.getAddress().toString());
        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
        } else if (requestCode == RESULT_CANCELED) {


        }
    }

    public void changeCity (String city) {
        WeatherFragment wf = (WeatherFragment)getFragmentManager()
                .findFragmentById(R.id.container);
        wf.changeCity(city);
        new Preferences(this).setCity(city);

    }
}