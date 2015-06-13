package com.platanitos.mapstest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private HashMap<Marker, MarkerStore> mMarkersHashMap;
    private ArrayList<MarkerStore> mMarkerStores = new ArrayList<MarkerStore>();
    private ArrayList<Producto> lProducto;
    ViewPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    int[] imagenes = {
            R.drawable.t1,
            R.drawable.t2,
            R.drawable.t3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mMarkersHashMap = new HashMap<Marker, MarkerStore>();
        lProducto = new ArrayList<Producto>();
        lProducto.add(new Producto(13.30,R.drawable.t1,"Laptop1","COMPUTADORA"));
        lProducto.add(new Producto(23.30,R.drawable.t2,"Laptop1","COMPUTADORA"));
        lProducto.add(new Producto(33.30,R.drawable.t3,"Laptop1","COMPUTADORA"));
        mMarkerStores.add(new MarkerStore("Platanitos Lima", "Ov Grau", lProducto, -12.0464, -77.0428));

        setUpMapIfNeeded();

        setUpMap();
        plotMarkers(mMarkerStores);

        mMap.addMarker(new MarkerOptions().position(new LatLng(-12.0464, -77.0428)).title("Lima"));
        mMap.setMyLocationEnabled(true);
    }

    private void plotMarkers(ArrayList<MarkerStore> markers)
    {
        if(markers.size() > 0)
        {
            for (MarkerStore myMarker : markers)
            {

                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(myMarker.getmLatitude(), myMarker.getmLongitude()));


                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, myMarker);

                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
            }
        }
    }

    private void setUpMap()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            // Check if we were successful in obtaining the map.

            if (mMap != null)
            {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {
                    @Override
                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker)
                    {
                        marker.showInfoWindow();
                        return true;
                    }
                });
            }
            else
                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter()
        {
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            View v  = getLayoutInflater().inflate(R.layout.marker_store, null);

            MarkerStore myMarker = mMarkersHashMap.get(marker);

//            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);

            TextView markerLabel = (TextView)v.findViewById(R.id.tienda);
            TextView markerLabel2 = (TextView)v.findViewById(R.id.direccion);
            // markerIcon.setImageResource(manageMarkerIcon(myMarker.getmIcon()));
            mViewPager = (ViewPager) v.findViewById(R.id.pager);
            mSectionsPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            int contador = 0;
            for(Producto producto : myMarker.getProductos()){
                mSectionsPagerAdapter.addFragment(Fragmentos.newInstance(contador,producto));
                contador++;
            }
            mViewPager.setAdapter(mSectionsPagerAdapter);
            markerLabel.setText(myMarker.getTienda());
            markerLabel2.setText(myMarker.getDireccion());

            return v;
        }
    }

    public static class FragmentosProducto extends Fragment {

        private static final String ARG_IMAGE = "imagen";
        private int imagen;

        public static FragmentosProducto newInstance(int imagen) {
            FragmentosProducto fragment = new FragmentosProducto();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE, imagen);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments() != null) {
                imagen = getArguments().getInt(ARG_IMAGE);
            }
        }

        public FragmentosProducto() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.product_layout, container, false);

            ImageView imagenView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView txt1 = (TextView) rootView.findViewById(R.id.textView);
            TextView txt2 = (TextView) rootView.findViewById(R.id.textView3);
            TextView txt3 = (TextView) rootView.findViewById(R.id.textView2);
            imagenView.setImageResource(imagen);
            return rootView;
        }
    }

}
