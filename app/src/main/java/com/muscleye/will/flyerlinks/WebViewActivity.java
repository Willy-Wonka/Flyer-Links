package com.muscleye.will.flyerlinks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.muscleye.will.flyerlinks.model.Store;

import java.util.ArrayList;
import java.util.List;


public class WebViewActivity extends ActionBarActivity {

    private WebView mWebView;
    List<Store> storeList;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView .getSettings().setLoadWithOverviewMode(true);
        mWebView .getSettings().setUseWideViewPort(true);

        //get clicked store's uri
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            displayStoreNameAndItsFlyers(extras.getString("clickedStoreName"), extras.getString("clickedStoreUri"));
            storeList = new ArrayList<>();
            for (int i = 0; i < 3; i++)
            {
                store = new Store();
                store.setName(extras.getString("storeName" + i));
                store.setUri(extras.getString("storeUri" + i));
                store.setDistance(extras.getFloat("storeDistance" + i));
                storeList.add(store);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        for (int i = 0; i < 3; i++)
        {
            menu.add(0, i, i, storeList.get(i).getName() +
                    " "+ unitDistance(storeList.get(i).getDistance()));
        }
//        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("id", String.valueOf(item.getItemId()));
        if (isOnline())
        {
            displayStoreNameAndItsFlyers(storeList.get(item.getItemId()).getName(),
                    storeList.get(item.getItemId()).getUri());
        }
        else
        {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
        return false;
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

    private void displayStoreNameAndItsFlyers(String storeName, String storeUri)
    {
        setTitle(storeName + " Flyers");
        mWebView.loadUrl(storeUri);
    }

    private String unitDistance(double distance)
    {
        String result;
        if (distance >= 1000)
        {
            distance /= 1000;
            result = String.valueOf((double)(Math.round(distance*10)/10.0)) + "km";
        }
        else
        {
            result = (int)distance + "m";
        }
        return result;
    }
//    private void requestData(int uri)
//    {
//        MyTask task = new MyTask();
//        task.execute(uri);//not Parallel processing
//        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "p1", "p2", "p3");//Parallel
//    }
//
//    private class MyTask extends AsyncTask<Integer, String, String>
//    {
//        @Override
//        protected void onPreExecute()
//        {
////            updateDisplay("Starting task");
//
//            if (tasks.size() != 0)
//            {
//                tasks.clear();
//            }
//            tasks.add(this);
//        }
//
//        @Override
//        protected String doInBackground(Integer... params) {
//            String storeURI = URIManager.getStoreURI(params[0]);
//
//            return URIManager.getStoreURI(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String storeURI)
//        {
//            // able to go to the main thread
//            mWebView.loadUrl(storeURI);
//            //StoreList = StoreJSONParser.parseFeed(result);
//            tasks.remove(this);
//        }
//    }
}
