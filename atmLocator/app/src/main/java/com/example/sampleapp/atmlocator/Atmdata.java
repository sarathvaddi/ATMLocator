package com.example.sampleapp.atmlocator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sarath vaddi on 9/14/15.
 */
public class Atmdata implements Parcelable{

    private String state;
    private String locType;
    private String label;
    private String address;
    private String city;
    private String zip;
    private String name;
    private String lat;
    private String lng;
    private String bank;
    private String phone;
    private String distance;
    private String None = "None";

    public Atmdata() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(state==null)
            state = None;
        this.state = state;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        if(locType==null)
            locType = None;
        this.locType = locType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        if(label==null)
            label = None;
        this.label = label;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address==null)
            address = None;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city==null)
            city = None;
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if(zip==null)
            zip=None;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null)
            name=None;
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone==null)
            phone = None;
        this.phone = phone;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(state);
        dest.writeString(locType);
        dest.writeString(label);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(zip);
        dest.writeString(name);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(bank);
        dest.writeString(phone);
        dest.writeString(distance);


    }

    public static final Parcelable.Creator<Atmdata> CREATOR = new Creator<Atmdata>() {
        public Atmdata createFromParcel(Parcel source) {
            Atmdata mBook = new Atmdata();
            mBook.state = source.readString();
            mBook.locType = source.readString();
            mBook.label = source.readString();
            mBook.address = source.readString();
            mBook.city = source.readString();
            mBook.zip = source.readString();
            mBook.name = source.readString();
            mBook.lat = source.readString();
            mBook.lng = source.readString();
            mBook.bank = source.readString();
            mBook.phone = source.readString();
            mBook.distance = source.readString();
            return mBook;
        }
        public Atmdata[] newArray(int size) {
            return new Atmdata[size];
        }
    };

}
