package com.platanitos.mapstest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class TabsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        ActionBar actionBar = getSupportActionBar();

        //Titulo y subtitulo del actionBar
        actionBar.setTitle("Titulo");
        actionBar.setSubtitle("Sub-Titulo");
        //actionBar.sette
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#55b849")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        //Modo Tabs del actionBar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Agregamos Tabs al actionBar
        ActionBar.Tab tab = actionBar.newTab().setText("INGRESAR").setTabListener(new TabsListener(this,"ingresar",LoginFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("REGISTRAR").setTabListener(new TabsListener(this,"registrar",RegisterFragment.class));
        actionBar.addTab(tab);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TabsListener implements ActionBar.TabListener {

        private Fragment fragment;
        private final String tag;

        public TabsListener (Activity activity, String tag, Class cls )
        {
            this.tag = tag;
            fragment = Fragment.instantiate(activity,cls.getName());
        }
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            fragmentTransaction.replace(android.R.id.content, fragment, tag);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            fragmentTransaction.remove(fragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    }
}
