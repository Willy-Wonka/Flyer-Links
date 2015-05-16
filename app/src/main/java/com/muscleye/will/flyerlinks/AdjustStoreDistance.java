package com.muscleye.will.flyerlinks;

import android.location.Location;

import com.muscleye.will.flyerlinks.model.Store;

import java.util.List;

public class AdjustStoreDistance
{
    public static List<Store> adjustDistance(double latitude, double longitude, List<Store> storeList)
    {
        for (Store store : storeList)
        {
            store.setDistance(distance(latitude, longitude, store.getLatitude(), store.getLongitude()));
        }
        return storeList;
    }

    public static float distance(double latA, double lonA, double latB, double lonB)
    {
        Location locationA = new Location("point A");

        locationA.setLatitude(latA);
        locationA.setLongitude(lonA);

        Location locationB = new Location("point B");

        locationB.setLatitude(latB);
        locationB.setLongitude(lonB);

        return locationA.distanceTo(locationB);
    }
}
