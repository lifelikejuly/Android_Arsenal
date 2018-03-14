package com.julyyu.arsenal.exercise.threadExercise;

/**
 * Created by julyyu on 2018/2/5.
 */

public class CountManager {

    public static CountManager instance;

    public int num = 0;

    public static CountManager getInstance(){
        if (instance == null){
            synchronized (CountManager.class){
                if(instance == null){
                    instance = new CountManager();
                }
            }
        }
        return instance;
    }

    public CountManager() {
        num = 0;
    }

    public synchronized void addNum(){
        if(num < 10){
            num++;
        }
    }

    public synchronized void decNum(){
        num--;
    }

    public synchronized int getNum(){
        return num;
    }


}
