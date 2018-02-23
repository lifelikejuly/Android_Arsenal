package com.julyyu.arsenal.serializationExercise;

import java.io.Serializable;

/**
 * Created by julyyu on 2018/1/31.
 */

public class CarDrivder implements Serializable{

    DrivderParcelable drivderParcelable;
    int number;

    public CarDrivder(DrivderParcelable drivderParcelable, int number) {
        this.drivderParcelable = drivderParcelable;
        this.number = number;
    }

    @Override
    public String toString() {
        return drivderParcelable.toString() + "--" + number;
    }
}
