package com.julyyu.arsenal.exercise.serializationExercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by julyyu on 2018/1/31.
 */

public class BusDrivder implements Parcelable{

    Drivder drivder;

    int number;


    public BusDrivder(Drivder drivder, int number) {
        this.drivder = drivder;
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.drivder);
        dest.writeInt(this.number);
    }

    public BusDrivder() {
    }

    protected BusDrivder(Parcel in) {
        this.drivder = (Drivder) in.readSerializable();
        this.number = in.readInt();
    }

    public static final Creator<BusDrivder> CREATOR = new Creator<BusDrivder>() {
        @Override
        public BusDrivder createFromParcel(Parcel source) {
            return new BusDrivder(source);
        }

        @Override
        public BusDrivder[] newArray(int size) {
            return new BusDrivder[size];
        }
    };

    @Override
    public String toString() {
        return "[Person: name=" + drivder.getName() +
                " address=" + drivder.getAddress() +
                " carName=" + drivder.getCarName() +
                " number=" + number +
                "]";
    }
}
