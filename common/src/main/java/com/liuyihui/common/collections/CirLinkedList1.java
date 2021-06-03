package com.liuyihui.common.collections;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 循环获取元素的队列
 *
 * @param <T>
 */
public class CirLinkedList1<T> extends ConcurrentLinkedQueue<T> {
    Iterator<T> iterator;

    public CirLinkedList1() {
        super();
        this.iterator = iterator();
    }


    public T next() {
        if (!iterator.hasNext()) {
            iterator = iterator();
            System.out.println("new iter");
        }

        if (!iterator.hasNext()) {
            System.out.println("empty list");
            return null;
        }

        return iterator.next();
    }


    public static void main(String[] args) {
        CirLinkedList1<Integer> cirLinkedList1 = new CirLinkedList1<>();

        /*cirLinkedList1.add(1);
        cirLinkedList1.add(2);
        cirLinkedList1.add(3);
        cirLinkedList1.add(4);
        cirLinkedList1.add(5);*/

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(cirLinkedList1.next());
        }
    }


}