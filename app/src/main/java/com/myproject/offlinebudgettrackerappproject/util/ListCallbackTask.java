package com.myproject.offlinebudgettrackerappproject.util;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ListCallbackTask implements Runnable {
    private final ListCallback callback;
    FutureTask futureTask;

    public ListCallbackTask(Callable<List> task, ListCallback callback) {
        this.callback = callback;
        this.futureTask = new FutureTask(task);
    }

    @Override
    public void run() {
        Thread thread = new Thread(futureTask);
        thread.start();
        List result;
        try {
            result = (List) futureTask.get();
            callback.onComplete(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
            callback.onComplete(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            callback.onComplete(null);
        }
    }
}
