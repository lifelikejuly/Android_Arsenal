package com.julyyu.arsenal.exercise.reflectionExercise;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by julyyu on 2018/2/23.
 */

public class GenericTest implements Serializable{

    private String               text;
    private Map<String, Integer> score;
    private int                  num;
    private Integer              num2;
    private Date                 time;
    private AsianPerson          person;
}
