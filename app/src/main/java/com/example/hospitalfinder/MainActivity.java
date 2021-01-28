package com.example.hospitalfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    Context context;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;
    LocationManager locationManager;
    SearchView searchview;
    String showl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        searchview = findViewById(R.id.search_view);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String locationname = searchview.getQuery().toString();
                List<Address> addresses =  null;

                if (locationname != null || !locationname.equals(""))
                {
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try{

                        addresses = geocoder.getFromLocationName(locationname, 5000);

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Address address = addresses.get(0);
                    showl =  address.getSubLocality()+" "+address.getLocality()+" "+address.getAdminArea();

                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(locationname));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        supportMapFragment.getMapAsync(this);

        final String[] placeTypeList = {"hospital", "bank", "atm"};
        String[] placeNameList = {"Hospital", "Bank", "ATM"};


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //when permission granted call method
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void getCurrentLocation() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();



                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 12));

                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    String markertitle = marker.getTitle();
                                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                    intent.putExtra("title", markertitle);
                                    intent.putExtra("Location_Address", showl);
                                    startActivity(intent);
                                    return false;
                                }
                            });



                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    private class PlaceTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {

        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();
        reader.close();
        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {

            JsonParser jsonParser = new JsonParser();
            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mapList;
        }


    }
}