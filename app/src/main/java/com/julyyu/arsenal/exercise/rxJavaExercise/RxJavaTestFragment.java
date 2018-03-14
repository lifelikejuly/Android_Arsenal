package com.julyyu.arsenal.exercise.rxJavaExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.uilibrary.fragment.BaseFragment;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by julyyu on 2018/2/7.
 */

public class RxJavaTestFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //RxJava 1 和 RxJava 2 差别还是蛮大的  Subscription在版本2改为Disposable
        // Observable 在版本2 为抽象类 在1中是个对象类
        Observable.just(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Observable.just("Hello world")
                .subscribe(word -> {
                    System.out.println("got " + word + " @ "
                            + Thread.currentThread().getName());
                });

        Flowable.just("Hello world")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });
    }
}
