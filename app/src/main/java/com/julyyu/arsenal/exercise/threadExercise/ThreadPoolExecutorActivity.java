package com.julyyu.arsenal.exercise.threadExercise;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;
import com.julyyu.utilslibrary.util.LogUtils;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by julyyu on 2018/2/19.
 */

public class ThreadPoolExecutorActivity extends BaseToolBarTitleActivity{

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    protected void initView() {
        LogUtils.printI("initView","COUNT_BITS : " + COUNT_BITS  + "\n" +
                "CAPACITY : " + CAPACITY + "\n" +
                "RUNNING : " + RUNNING + "\n" +
                "SHUTDOWN : " + SHUTDOWN + "\n" +
                "STOP : " + STOP + "\n" +
                "TIDYING : " + TIDYING + "\n" +
                "TERMINATED : " + TERMINATED + "\n"
        );
        LogUtils.printI("initView",~CAPACITY + "");
        LogUtils.printI("initView",(-2 & ~CAPACITY )+ "");
        LogUtils.printI("initView",(0 & ~CAPACITY )+ "");
        LogUtils.printI("initView",(1 & ~CAPACITY )+ "");
        LogUtils.printI("initView",(3 & ~CAPACITY )+ "");
        /**
         *
         *
         COUNT_BITS : 29
         CAPACITY : 536870911
         RUNNING : -536870912
         SHUTDOWN : 0
         STOP :     536870912
         TIDYING : 1073741824
         TERMINATED : 1610612736

         private static int runStateOf(int c)     { return c & ~CAPACITY; }
         private static int workerCountOf(int c)  { return c & CAPACITY; }
         private static int ctlOf(int rs, int wc) { return rs | wc; }
         *
         *
         *
         */
    }

    @Override
    protected void initDate() {

        ExecutorService executorService = new ThreadPoolExecutor(3,4,2, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());

        /**
         * 线程池固定，核心线程数就是最大线程数
         */
        ExecutorService newfiexdService = Executors.newFixedThreadPool(3);
        /**
         * 最大线程数线程池
         */
        ExecutorService newCachedService = Executors.newCachedThreadPool();

        ExecutorService newScheduledService = Executors.newScheduledThreadPool(3);

        ExecutorService newSingleService = Executors.newSingleThreadExecutor();


        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.printI("executorService","dadadadada!");
            }
        });

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 2;
            }
        });
        try {
            LogUtils.printI("executorService","future: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        todoSomething(newfiexdService);
//        new Thread(new Runnable() {
//            int i = 0;
//            @Override
//            public void run() {
//                while (i< 2000){
//                    i++;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    LogUtils.printI("Thread","num: " + i);
//                }
//            }
//        }).start();
//        finish();
    }

    private void todoSomething(ExecutorService service){
        todo:
        for(;;){
            Random random = new Random();
            int num  = random.nextInt(10);
            LogUtils.printI("todoSomething ，todo", "num：" + num);
            if(num > 3){
                continue todo;
            }else{
                break todo;
            }
        }

        service.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.printI("todoSomething ", "1");
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.printI("todoSomething ", "2");
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.printI("todoSomething ", "3");
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.printI("todoSomething ", "4");
            }
        });
    }
}
