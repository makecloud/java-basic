package com.liuyihui.common.collections;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 可以查看下一个的，循环获取元素队列
 *
 * @param <T>
 */
public class CirLinkedList2<T> extends ConcurrentLinkedQueue<T> {
    Iterator<T> iterator;
    Iterator<T> latterIterator;
    T next;

    public CirLinkedList2() {
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
        CirLinkedList2<Integer> cirLinkedList2 = new CirLinkedList2<>();

        cirLinkedList2.add(1);
        cirLinkedList2.add(2);
        cirLinkedList2.add(3);
        cirLinkedList2.add(4);
        cirLinkedList2.add(5);

        int i = 0;

        while (true) {

            try {
                Thread.sleep(1000);
                i++;

                /*if (i == 3) {
                    cirLinkedList2.add(78875);
                    cirLinkedList2.add(32);
                    cirLinkedList2.add(82564);
                    cirLinkedList2.add(362);
                }*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(cirLinkedList2.next() + "," + cirLinkedList2.peekLatter());
        }
    }


}