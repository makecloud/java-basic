package com.liuyihui.common.concurrent.生产者消费者;

import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.Executors;

/**
 * 餐厅类
 */
public class Restaurant {
    /** 食物 */
    private Meal meal;
    /** 厨师 */
    private Chef chef;
    /** 消费者 */
    private WaitPerson waitPerson;

    Restaurant() {
    }

    /**
     * 消费食物
     */
    public void consumeMeal() {
        if (meal != null) {
            meal = null;
        }

        synchronized (chef) {
            chef.notifyAll();
        }
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Chef chef = new Chef(restaurant);
        WaitPerson waitPerson = new WaitPerson(restaurant);
        restaurant.setChef(chef);
        restaurant.setWaitPerson(waitPerson);

        new Thread(waitPerson).start();
        new Thread(chef).start();
    }


    //getter & setter
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    public void setWaitPerson(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }
}
