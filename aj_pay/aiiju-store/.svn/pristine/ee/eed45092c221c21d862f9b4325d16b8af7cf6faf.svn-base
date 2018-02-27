package com.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消费者
 * @author 百恼  2013-11-16下午07:42:08
 *
 */
public class Consumer implements Runnable{
    
    //容器
    private final ArrayBlockingQueue<Bread> queue;
    
    public Consumer(ArrayBlockingQueue<Bread> queue){
        this.queue = queue;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while(true){
            consume();
        }
    }
    
    public void consume(){
        /**
         * take()方法和put()方法是对应的，从中拿一个数据，如果拿不到线程挂起
         * poll()方法和offer()方法是对应的，从中拿一个数据，如果没有直接返回null
         */
        try {
        	 System.out.println("队列长度："+queue.size());
             Bread bread = queue.take();
            
            System.out.println("队列长度："+queue.size());
    
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}