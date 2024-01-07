package com.example.rentmev2.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String brand, model, imageURL, addInfo, gear; // Use lowercase for consistency
    private int seats, price;

    public Car(String brand, String model, String imageURL, String addInfo, String gear, int seats, int price) {
        this.brand = brand;
        this.model = model;
        this.imageURL = imageURL;
        this.addInfo = addInfo;
        this.gear = gear; // Corrected the field name
        this.seats = seats;
        this.price = price;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Ensure to read and write fields in the same order
    protected Car(Parcel in) {
        brand = in.readString();
        model = in.readString();
        seats = in.readInt();
        price = in.readInt();
        imageURL = in.readString();
        addInfo = in.readString();
        gear = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeInt(seats);
        dest.writeInt(price);
        dest.writeString(imageURL);
        dest.writeString(addInfo);
        dest.writeString(gear);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
