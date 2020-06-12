package com.example.workit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;

public class addplacemap extends AppCompatActivity implements MapboxMap.OnMapClickListener, PermissionsListener {
    private MapView mapView;
    private MapboxMap map;
    private Marker marker;
    private LatLng location;
    Button gotoplace;
    LocationComponent locationComponent ;
    PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibWVkbWg5NiIsImEiOiJjazR4YjVjYXIwNWFxM2dwY2pjb2t4eG9yIn0.byTwF3bP7kGn04KzKZD7Ug");

        setContentView(R.layout.activity_addplacemap);
        final Intent intent=getIntent();
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        gotoplace=findViewById(R.id.saveplace);
        gotoplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent main = new Intent(getApplicationContext(),addtask.class);
                main.putExtra("posetion",location);
                if(intent.getExtras().get("titleS") == null)main.putExtra("titleS","");
                else main.putExtra("titleS",(String) intent.getExtras().get("titleS"));
                if(intent.getExtras().get("priceS") == null)main.putExtra("priceS","0");
                else main.putExtra("priceS",(String) intent.getExtras().get("priceS"));
                if(intent.getExtras().get("descriptionS") == null)main.putExtra("descriptionS","");
                else main.putExtra("descriptionS",(String) intent.getExtras().get("descriptionS"));



                startActivity(main);
            }
        });
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                map=mapboxMap;


                mapboxMap.addOnMapClickListener(addplacemap.this);
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        enableLocationComponent(mapboxMap.getStyle());
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(locationComponent.getLastKnownLocation().getLatitude(),locationComponent.getLastKnownLocation().getLongitude()))
                                .zoom(10)
                                .tilt(20)
                                .build();


                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        if(marker!=null){map.removeMarker(marker);}
        marker = map.addMarker(new MarkerOptions().position(point));
        location=point;
        //System.out.println(point.getLatitude());
        //System.out.println(point.getLongitude());

        return false;
    }



    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)){
            locationComponent = map.getLocationComponent();
            System.out.println("seguee");
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return ;
            }
            locationComponent.activateLocationComponent(this,loadedMapStyle);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);

        }
        else{
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted){
            enableLocationComponent(map.getStyle());

        }else{
            Toast.makeText(getApplicationContext(),"Permission not granted",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
