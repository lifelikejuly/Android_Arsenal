package com.julyyu.arsenal.serializationExercise;

import java.io.Serializable;

/**
 * Created by julyyu on 2018/1/31.
 */

public class Drivder implements Serializable{
    private String name;
    private String address;
    private String carName;

    public Drivder(String name, String address, String carName) {
        this.name = name;
        this.address = address;
        this.carName = carName;
    }

    @Override
    public String toString() {
        return "[Person: name=" + name +
        " address=" + address +
                " carName=" + carName + "]";
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCarName() {
        return carName;
    }
}
