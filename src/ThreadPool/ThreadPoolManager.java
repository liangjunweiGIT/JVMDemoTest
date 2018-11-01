package ThreadPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:一个简单的线程池创建
 * @Author Created by liangjunwei on 2018/8/14 11:20
 */
public class ThreadPoolManager {

    private int threadCount; //启动的线程数
    private WorkThread[] handlers; //线程数组
    private final ArrayList<Runnable> taskVector = new ArrayList<Runnable>(); //任务队列

    ThreadPoolManager(int threadCount) {
        this.threadCount = threadCount;
        for (int i = 0; i < threadCount; i++) {
            handlers[i] = new WorkThread();
            handlers[i].start();
        }
    }

    void shutdown() {
        synchronized (taskVector) {
            while (!taskVector.isEmpty())
                taskVector.remove(0); //清空任务队列
        }

        for (int i = 0; i < threadCount; i++) {
            handlers[i] = new WorkThread();
            handlers[i].interrupt(); //结束线程
        }
    }

    void execute(Runnable newTask) { //增加新任务
        synchronized (taskVector) {
            taskVector.add(newTask);
            taskVector.notifyAll();
            Map map = new HashMap<>();
            map.put(null,null);
            map = new ConcurrentHashMap();
            map.put(null,null);
        }
    }

    private class WorkThread extends Thread {
        public void run() {
            Runnable task = null;
            for (; ; ) {
                synchronized (taskVector) {//获取一个新任务
                    if (taskVector.isEmpty())
                        try {
                            taskVector.wait();
                            task = taskVector.remove(0);
                        } catch (InterruptedException e) {
                            break;
                        }
                }
                assert task != null;
                task.run();
            }
        }
    }
}
