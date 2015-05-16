package com.muscleye.will.flyerlinks;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.muscleye.will.flyerlinks.model.Store;
import com.muscleye.will.flyerlinks.parsers.StoreJSONParser;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends ListActivity {

    Button btn;
    // GPSTracker class
    GPSTracker gps;
    List<Store> storeList;
    double myLatitude, myLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.buttonFNS);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (isOnline())
                {
                    storeList = StoreJSONParser.parseFeed();
                    getGPS();
                    storeList = AdjustStoreDistance.adjustDistance(myLatitude, myLongitude, storeList);
                    for (Store store : storeList)
                    {
                        showStore();
//                    Log.d("store name", store.getName());
//                    Log.d("store address", store.getAddress());
//                    Log.d("store latitude", String.valueOf(store.getLatitude()));
//                    Log.d("store longitude", String.valueOf(store.getLongitude()));
//                    float meters = distance(myla, mylo, store.getLatitude(), store.getLongitude());
//                    Log.d("result", myla +"\n"+ mylo +"\n"+ store.getLatitude() +"\n"+ store.getLongitude());
//                    Log.d("store meter", String.valueOf(meters));
                    }
//                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Network isn't available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra("clickedStoreUri", storeList.get(position).getUri());
        intent.putExtra("clickedStoreName", storeList.get(position).getName());
        for (int i = 0; i < 3; i++)
        {
            Store store = storeList.get(i);
            intent.putExtra("storeName"+i, store.getName());
            intent.putExtra("storeUri"+i, store.getUri());
            intent.putExtra("storeDistance"+i, store.getDistance());
        }
        startActivity(intent);
//        Toast.makeText(this, "Clicked " + store.getName(), Toast.LENGTH_SHORT).show();
    }

    public void getGPS()
    {
        // create class object
        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            myLatitude = gps.getLatitude();
            myLongitude = gps.getLongitude();

            // \n is for new line
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    public void showStore()
    {
        StoreAdapter adapter = new StoreAdapter(this, R.layout.item_store, storeList);

        Comparator<Store> comparator;
        comparator = new SortByDistance();
        Collections.sort(storeList, comparator);
//        adapter.setStoreList(storeList);
//        adapter.notifyDataSetChanged();

        setListAdapter(adapter);
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return false;
    }
}
