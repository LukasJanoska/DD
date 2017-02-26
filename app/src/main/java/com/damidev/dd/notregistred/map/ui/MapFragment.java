package com.damidev.dd.notregistred.map.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentMapBinding;
import com.damidev.dd.notregistred.map.inject.MapComponent;
import com.damidev.dd.notregistred.map.inject.MapModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.splashscreen.dataaccess.ServerMapChildResponseDto;
import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MapFragment extends D2MvvmFragment<FragmentMapBinding, MapViewModel> implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, MapFragView {

    public static String MapFragmnetTag = "MAP_FRAGMENT_TAG";

    public static String ImageUrlName = "imagedd.jpg";
    public static String ImageUrl = "http://androidtest.dev.damidev.com/images/2.jpg";

    MapView mapView;
    GoogleMap map;
    private GroundOverlay mGroundOverlay;

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

        ServerMapResponseDto responseDto = readObjectFromFile(getContext());

        ArrayList<ServerMapChildResponseDto> points = new ArrayList<>();
        points = responseDto.getChildResponse();

        for (ServerMapChildResponseDto point : points) {
            Double lat = point.getLat();
            Double lng = point.getLng();
            String imgurl = point.getPhotos().get(2);

            addMarker(new LatLng(lat, lng), imgurl, "start");
        }


        /*CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 7);
        map.animateCamera(cameraUpdate);*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "marker", Toast.LENGTH_SHORT).show();

        return false;
    }

    public void addMarker(final LatLng point, final String imgurl, final String title) {

        BitmapDescriptor img = BitmapDescriptorFactory.fromResource(R.drawable.newark_nj_1922);

        map.setOnMarkerClickListener(this);
        m = map.addMarker(new MarkerOptions()
                .position(point)
                .title(title)
                .snippet("aaaaaaa"));

        PoiTarget pt = new PoiTarget(m);
        poiTargets.add(pt);
        Picasso.with(getContext())
                .load(imgurl)
                .resize(90, 90)
                .into(pt);
    }

    private Marker m;
    private Set<PoiTarget> poiTargets = new HashSet<PoiTarget>();

    class PoiTarget implements Target {
        private Marker m;

        public PoiTarget(Marker m) { this.m = m; }

        @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            m.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
            poiTargets.remove(this);
        }

        @Override public void onBitmapFailed(Drawable errorDrawable) {
            poiTargets.remove(this);
        }

        @Override public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

}
