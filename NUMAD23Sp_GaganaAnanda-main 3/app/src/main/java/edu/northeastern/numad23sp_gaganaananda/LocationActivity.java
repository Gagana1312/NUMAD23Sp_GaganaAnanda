package edu.northeastern.numad23sp_gaganaananda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
//import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LocationActivity extends AppCompatActivity implements LocationListener {

    TextView latitudeText;

    TextView longitudeText;

    TextView distanceText;

    private float Distance = 0f;

    private Location previousLocation = null;

    private Button resetButton;

    private double totalDistance = 0.0;

    private static final String TAG = "LocationActivity";

    LocationManager locationManager;

    Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        latitudeText = findViewById(R.id.CurrentLatitude);
        longitudeText = findViewById(R.id.CurrentLongitude);
        distanceText = findViewById(R.id.Distance);
        resetButton = findViewById(R.id.button);

        // reset button to nullify the distance.
        resetButton.setOnClickListener(view -> reset());
        checkLocationPermission();

        // Restore state after configuration change
        if (savedInstanceState != null) {
            totalDistance = savedInstanceState.getFloat("totalDistance");
            distanceText.setText("Distance: " + String.format("%.2f", totalDistance) + " m");
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        // Display the latitude and longitude values
        latitudeText.setText("Latitude: " + latitude);
        longitudeText.setText("Longitude: " + longitude);
        Log.d("LOCATION", "Latitude: " + latitude + ", Longitude: " + longitude);
        calculateDistance(location);
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
        else {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, this);
            onLocationChanged(location);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationPermission();
            } else {
                // Permission denied, handle accordingly
                requestLocationPermission();
            }
        }
    }
    private void calculateDistance(Location location) {
        if (previousLocation != null) {
            totalDistance += location.distanceTo(previousLocation);
        }
        previousLocation = location;
        distanceText.setText("Distance: " + String.format("%.2f", totalDistance) + "m");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.getDouble("Distance: ", Distance);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to dismiss the activity? Total distance will be lost.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // dismiss the activity
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    @Override
    public void onStop() {
        super.onStop();
        if(locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void reset() {
        totalDistance = 0;
        previousLocation = null;
        distanceText.setText("Distance: " + String.format("%.2f", totalDistance) + " m");
    }
    private void requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Explain why location permission is needed
            new AlertDialog.Builder(this)
                    .setTitle("Location permission")
                    .setMessage("This app needs location permission to calculate the distance traveled.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            checkLocationPermission();
        }
    }



}
