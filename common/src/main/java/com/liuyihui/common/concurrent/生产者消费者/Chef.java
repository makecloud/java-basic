package com.liuyihui.common.concurrent.生产者消费者;

import com.sun.tracing.dtrace.StabilityLevel;

import javax.sound.midi.Soundbank;

/**
 * 厨师类<br>
 * 生产者
 */
public class Chef implements Runnable {
    private Restaurant restaurant;
    private int count;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                //厨师发现餐厅有食物时, 等待
                while (restaurant.getMeal() != null) {
                    synchronized (this) {
                        System.out.println("厨师: " + "餐厅食物还有,等待...");
                        wait();
                    }
                }
                if (++count == 10) {
                    //todo 停止
                }
                System.out.println("produce one meal ~");
                restaurant.setMeal(new Meal(count));//准备一份食物
                synchronized (restaurant.getWaitPerson()) {
                    restaurant.getWaitPerson().notifyAll();//通知消费者
                }
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
