package com.muscleye.will.flyerlinks.parsers;

import com.muscleye.will.flyerlinks.model.Store;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class StoreXMLParser
{
    public static List<Store> parseFeed(String content)
    {
        try
        {
            boolean inDataItemTag = false;
            String currentTagName = "";
            Store store = null;
            List<Store> storeList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("store"))
                        {
                            inDataItemTag = true;
                            store = new Store();
                            storeList.add(store);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("store"))
                        {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        if (inDataItemTag && store != null)
                        {
                            switch (currentTagName)
                            {
                                case "name":
                                    store.setName(parser.getText());
                                    break;
                                case "address":
                                    store.setAddress(parser.getText());
                                    break;
                                case "latitude":
                                    store.setLatitude(Double.parseDouble(parser.getText()));
                                    break;
                                case "longitude":
                                    store.setLongitude(Double.parseDouble(parser.getText()));
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
            return storeList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
