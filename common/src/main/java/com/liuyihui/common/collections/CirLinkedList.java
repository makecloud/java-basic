package com.liuyihui.common.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CirLinkedList<T> extends ConcurrentLinkedQueue<T> {
    Iterator<T> iterator;
    Iterator<T> latterIterator;
    T next;

    public CirLinkedList() {
        super();
        this.iterator = iterator();
        this.latterIterator = iterator();
    }


    public T next() {
        if (!latterIterator.hasNext()) {
            latterIterator = iterator();
            //两个iter同时到头时，later往后挪一下
            if (!iterator.hasNext()) {
                latterIterator.next();
            }
        }
        if (!iterator.hasNext()) {
            iterator = iterator();
            System.out.println("new iter");
        }

        T a = iterator.next();
        next = latterIterator.next();
        return a;
    }

    public T peekLatter() {
        return next;
    }


    public static void main(String[] args) {
        CirLinkedList<Integer> cirLinkedList = new CirLinkedList<>();

        cirLinkedList.add(1);
        cirLinkedList.add(2);
        cirLinkedList.add(3);
        cirLinkedList.add(4);
        cirLinkedList.add(5);

        int i = 0;

        while (true) {

            try {
                Thread.sleep(1000);
                i++;

                /*if (i == 3) {
                    cirLinkedList.add(78875);
                    cirLinkedList.add(32);
                    cirLinkedList.add(82564);
                    cirLinkedList.add(362);
                }*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(cirLinkedList.next() + "," + cirLinkedList.peekLatter());
        }
    }


}