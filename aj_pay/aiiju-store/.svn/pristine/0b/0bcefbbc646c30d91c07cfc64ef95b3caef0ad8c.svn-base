package com.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * TODO Comment of Client
 * @author 百恼 2013-11-16下午07:58:38
 *
 */
public class Client {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        int capacity = 10;
        ArrayBlockingQueue<Bread> queue = new ArrayBlockingQueue<Bread>(3);
        queue.put(new Bread());
//        new Thread(new Producer(queue)).start();
//        new Thread(new Producer(queue)).start();
//        new Thread(new Consumer(queue)).start();
//        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

}