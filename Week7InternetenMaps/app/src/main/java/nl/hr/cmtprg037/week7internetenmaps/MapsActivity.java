package nl.hr.cmtprg037.week7internetenmaps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public final static String LOG_TAG = "week7maps";
    // maps
    private GoogleMap mMap;

    // location
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates = false;

    private int REQ_PERM_LOC_UPDATES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // connect
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // create callback for location update
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                    Log.d(LOG_TAG, "Location update received");
                    addMarker(new LatLng(location.getLatitude(), location.getLongitude()), "Real time");
                }
            }
        };
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(LOG_TAG, "Map ready");

        mMap = googleMap;

        // marker at wijnhaven
        addMarker(new LatLng(51.917221, 4.484463), "Hogeschool Rotterdam");

        // first get the last known location
        requestLastKnownLocation();
    }

    private void addMarker(LatLng loc, String name) {
        // Add a marker and move the camera

        mMap.addMarker(new MarkerOptions().position(loc).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(13));
    }

    // last known
    private void requestLastKnownLocation() {

        // hier ook permissies vragen
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.d(LOG_TAG, "Last known location found");
                            addMarker(new LatLng(location.getLatitude(), location.getLongitude()), "Last known");

                            requestingLocationUpdates = true;
                            startLocationUpdates();
                        } else {
                            Log.d(LOG_TAG, "Last known location success, but still null");

                            requestingLocationUpdates = true;
                            startLocationUpdates();
                        }
                    }


                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(LOG_TAG, "Something went wrong with last known location");
                requestingLocationUpdates = true;
                startLocationUpdates();
            }
        });

    }

    // create a request for updates
    protected LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }

    // start updates
    @Override
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        Log.d(LOG_TAG, "start requesting");

        boolean permissionAccessCoarseLocationApproved =
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED;

        if (permissionAccessCoarseLocationApproved) {
            Log.d(LOG_TAG, "permission ok");
            fusedLocationClient.requestLocationUpdates(createLocationRequest(),
                    locationCallback,
                    Looper.getMainLooper());
        } else {
            Log.d(LOG_TAG, "no permission, ask for it");
            requestingLocationUpdates = false;
            // Make a request for foreground-only location access.
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQ_PERM_LOC_UPDATES);
        }


    }

    // stop updates to avoid battery drain
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        Log.d(LOG_TAG, "stop requesting");
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    // permissions asked
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(LOG_TAG, "permission requested");

        // req for perm
        if (requestCode == REQ_PERM_LOC_UPDATES) {
            Log.d(LOG_TAG, "handle permission requested");
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    Log.d(LOG_TAG, "permission not granted... try again");
                }
            }
            Log.d(LOG_TAG, "ask location again");
            requestingLocationUpdates = true;
            startLocationUpdates();
        }
    }
}
