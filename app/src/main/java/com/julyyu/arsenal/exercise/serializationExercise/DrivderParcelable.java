package com.julyyu.arsenal.exercise.serializationExercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by julyyu on 2018/1/31.
 */

public class DrivderParcelable implements Parcelable{

    private String name;
    private String address;
    private String carName;


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCarName() {
        return carName;
    }

    public DrivderParcelable(String name, String address, String carName) {
        this.name = name;
        this.address = address;
        this.carName = carName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.carName);
    }

    protected DrivderParcelable(Parcel in) {
        this.name = in.readString();
        this.address = in.readString();
        this.carName = in.readString();
    }

    public static final Creator<DrivderParcelable> CREATOR = new Creator<DrivderParcelable>() {
        @Override
        public DrivderParcelable createFromParcel(Parcel source) {
            return new DrivderParcelable(source);
        }

        @Override
        public DrivderParcelable[] newArray(int size) {
            return new DrivderParcelable[size];
        }
    };

    @Override
    public String toString() {
        return "[Person: name=" + name +
                " address=" + address +
                " carName=" + carName +
                "]";
    }
}
