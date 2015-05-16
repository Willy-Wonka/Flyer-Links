package com.muscleye.will.flyerlinks.parsers;


import com.muscleye.will.flyerlinks.model.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StoreJSONParser
{
//    public static List<Store> parseFeed(String content)
    static String content = "[{\"name\":\"Walmart\",\"address\":\"1055 Hillside Drive, Suite 100\nKamloops, BC V2E 2S5\",\"latitude\":50.66569,\"longitude\":-120.370521," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/walmart-west-flyer-april-2-to-8/all\"}," +
        "{\"name\":\"Save-On-Foods\",\"address\":\"1210 Summit Dr\nColumbia Place Shopping Centre\nKamloops, BC V2C 6M1\",\"latitude\":50.66489,\"longitude\":-120.35185," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/save-on-foods-flyer-april-3-to-9/all\"}," +
        "{\"name\":\"Real Canadian Superstore\",\"address\":\"910 Columbia Street West\nKamloops, BC V2C 1L2\",\"latitude\":50.6687794,\"longitude\":-120.3561004," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/atlantic-superstore-flyer-april-2-to-8/all\"}," +
        "{\"name\":\"Safeway\",\"address\":\"945 W Columbia St\nKamloops, BC V2C 1L5\",\"latitude\":50.669356,\"longitude\":-120.352461," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/safeway-flyer-april-3-to-9/all\"}," +
        "{\"name\":\"Costco\",\"address\":\"1675 Versatile Dr\nKamloops, BC V1S 1W7\",\"latitude\":50.655171,\"longitude\":-120.38737," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/costco-weekly-savings-bc-ab-sk-mb-march-30-to-april-5/all\"}," +
        "{\"name\":\"Canadian Tire\",\"address\":\"1441 Hillside Drive\nKamloops, BC V2E 1A9\",\"latitude\":50.6544555,\"longitude\":-120.3803775," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/canadian-tire-west-flyer-april-3-to-9/all\"}," +
        "{\"name\":\"Cooper's Foods\",\"address\":\"450 Lansdowne St\nLansdowne, BC V2C 1Y3\",\"latitude\":50.677082,\"longitude\":-120.327488," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/coopers-foods-flyer-april-3-to-9/all\"}," +
        "{\"name\":\"EB Games\",\"address\":\"1055 Hillside Drive\nKamloops Smartcentre\nKamloops, BC V2E 2S5\",\"latitude\":50.6640674,\"longitude\":-120.3688737," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/eb-games-flyer-april-3-to-9/all\"}," +
        "{\"name\":\"JYSK\",\"address\":\"Summit Shopping Centre\n1180 Columbia St W #105\nKamloops, BC V2C 6R6\",\"latitude\":50.664771,\"longitude\":-120.35579," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/jysk-flyer-april-2-to-8/all\"}," +
        "{\"name\":\"Sleep Country\",\"address\":\"#640 - 1055 Hillside Drive\nKamloops Smartcentre\nKamloops, BC V2E 2S5\",\"latitude\":50.663541,\"longitude\":-120.370502," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/sleep-country-flyer-april-1-to-19/all\"}," +
        "{\"name\":\"T & T Supermarket\",\"address\":\"Central City Shopping Centre\n10153 King George Hwy\nSurrey, BC V3T 5X4\",\"latitude\":49.1867555,\"longitude\":-122.8485167," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/tt-supermarket-bc-flyer-april-3-to-9/all\"}," +
        "{\"name\":\"Target\",\"address\":\"945 Columbia St W\nKamloops, BC V2C 1L5\",\"latitude\":50.6682,\"longitude\":-120.3521," +
        "\"uri\":\"http://flyers.smartcanucks.ca/canada/target-canada-final-days-april-2-to-12/all\"}]";

    public static List<Store> parseFeed()
    {
        try {
            JSONArray ar = new JSONArray(content);
            List<Store> storeList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++)
            {
                JSONObject obj = ar.getJSONObject(i);
                Store store = new Store();

                store.setName(obj.getString("name"));
                store.setAddress(obj.getString("address"));
                store.setLatitude(obj.getDouble("latitude"));
                store.setLongitude(obj.getDouble("longitude"));
                store.setUri(obj.getString("uri"));

                storeList.add(store);
            }

            return  storeList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
