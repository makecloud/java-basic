package com.liuyihui.common.concurrent.生产者消费者;

/**
 * 客户<br>
 * 消费者
 */
public class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //客户发现餐厅没有食物,则等待食物
                while (restaurant.getMeal() == null) {
                    synchronized (this) {
                        System.out.println("顾客: " + "餐厅没有食物,等待...");
                        wait();
                    }
                }
                System.out.println("WaitPerson take a meal: " + restaurant.getMeal());
                restaurant.consumeMeal();//消费食物
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
