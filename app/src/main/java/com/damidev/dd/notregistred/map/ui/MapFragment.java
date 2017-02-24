package com.damidev.dd.notregistred.map.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class MapFragment extends D2MvvmFragment<FragmentMapBinding, MapViewModel> implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, MapFragView {

    public static String MapFragmnetTag = "MAP_FRAGMENT_TAG";

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

        downloadImage();

        return view;
    }

    public void downloadImage() {
        Picasso.with(getContext())
                .load(ImageUrl)
                //.placeholder(R.drawable.new)
                .into(new Target() {
                          @Override
                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                              try {
                                  // Assume block needs to be inside a Try/Catch block.
                                  String path = Environment.getExternalStorageDirectory().toString();
                                  OutputStream fOut = null;
                                  Integer counter = 0;
                                  File file = new File(path, "FitnessGirl"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
                                  fOut = new FileOutputStream(file);

                                  bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                                  fOut.flush(); // Not really required
                                  fOut.close(); // do not forget to close the stream

                                  MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
                              } catch(Exception e){
                                  e.getMessage();
                                  // some action
                              }
                          }

                          @Override
                          public void onBitmapFailed(Drawable errorDrawable) {
                          }

                          @Override
                          public void onPrepareLoad(Drawable placeHolderDrawable) {
                          }
                      }
                );
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

            addMarker(new LatLng(lat, lng), R.drawable.start, "start");
        }


        /*CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 7);
        map.animateCamera(cameraUpdate);*/
    }

    public void addMarker(final LatLng point, final @DrawableRes int iconId, final String title) {

        BitmapDescriptor img = BitmapDescriptorFactory.fromResource(R.drawable.newark_nj_1922);

        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.newark_nj_1922);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        map.setOnMarkerClickListener(this);
        map.addMarker(new MarkerOptions()
                .position(point)
                .title(title)
                .snippet("aaaaaaa")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "marker", Toast.LENGTH_SHORT).show();

        return false;
    }
}
