package com.muscleye.will.flyerlinks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable
{
    private String name;
    private String address;
    private double latitude;
    private double Longitude;
    private float distance;
    private String uri;

    public Store()
    {
        this.name = "";
        this.address = "";
        this.latitude = 0.0;
        this.Longitude = 0.0;
        this.distance = 0;
        this.uri = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Store(Parcel in) {
        name = in.readString();
        address = in.readString();
        latitude = in.readDouble();
        Longitude = in.readDouble();
        distance = in.readFloat();
        uri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(latitude);
        dest.writeDouble(Longitude);
        dest.writeFloat(distance);
        dest.writeString(uri);
    }
    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>()
    {
        public Store createFromParcel(Parcel in)
        {
            return new Store(in);
        }
        public Store[] newArray(int size)
        {
            return new Store[size];
        }
    };
}
