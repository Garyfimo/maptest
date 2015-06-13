package com.platanitos.mapstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Garyfimo on 6/13/15.
 */
public class Fragmentos extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String IMAGEVIEW = "image";
    private static final String PRICE = "precio";
    private static final String CATEGORY = "categoria";
    private static final String NAME = "nombre";

    private int section_number;
    private int imageview;
    private Double price;
    private String category;
    private String name;

    public static Fragmentos newInstance(int sectionNumber,Producto producto){
        Fragmentos fragment = new Fragmentos();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER,sectionNumber);
        args.putInt(IMAGEVIEW, producto.getImagen());
        args.putDouble(PRICE, producto.getPrecio());
        args.putString(CATEGORY, producto.getCategoria());
        args.putString(NAME,producto.getNombre());
        fragment.setArguments(args);
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.section_number = getArguments().getInt(ARG_SECTION_NUMBER);
            this.imageview = getArguments().getInt(IMAGEVIEW);
            this.price = getArguments().getDouble(PRICE);
            this.category = getArguments().getString(CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_layout,container,false);

        ImageView prd_image = (ImageView) rootView.findViewById(R.id.imageView);
        prd_image.setImageResource(imageview);

        TextView prd_precio = (TextView) rootView.findViewById(R.id.textView);
        TextView prd_categoria = (TextView) rootView.findViewById(R.id.textView2);

        prd_precio.setText(price.toString());
        prd_categoria.setText(category);

        return rootView;
    }
}
