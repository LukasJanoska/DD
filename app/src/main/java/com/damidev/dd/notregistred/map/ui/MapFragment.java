package com.damidev.dd.notregistred.map.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentMapBinding;
import com.damidev.dd.notregistred.map.inject.MapComponent;
import com.damidev.dd.notregistred.map.inject.MapModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class MapFragment extends D2MvvmFragment<FragmentMapBinding, MapViewModel> implements OnMapReadyCallback, MapFragView {

    public static String MapFragmnetTag = "MAP_FRAGMENT_TAG";

    MapView mapView;
    GoogleMap map;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((MapComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new MapModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_map);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this); //when you already implement OnMapReadyCallback in your
    }

    public ServerMapResponseDto readObjectFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput("ServerMapResponseDto.srl");
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();

            if(readObject != null && readObject instanceof ServerMapResponseDto) {
                return (ServerMapResponseDto) readObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MapFragment newInstance(String someTitle) {
        MapFragment loginFragment = new MapFragment();
        return loginFragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Updates the location and zoom of the MapFragView
        ServerMapResponseDto responseDto = readObjectFromFile(getContext());
        Double lat = responseDto.getChildResponse().get(0).getLat();
        Double lng = responseDto.getChildResponse().get(0).getLng();

        addMarker(new LatLng(lat, lng), R.drawable.start, "start");
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 7);
        map.animateCamera(cameraUpdate);
    }

    public void addMarker(final LatLng point, final @DrawableRes int iconId, final String title) {
        map.addMarker(new MarkerOptions()
                .position(point)
                .title(title)
                .snippet("aaaaaaa")
                .icon(BitmapDescriptorFactory.fromResource(iconId)));
    }

}
