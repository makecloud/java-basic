package com.liuyihui.common.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入排序。在插入动作时，执行排序
 */
public class SortWhenInsert {
    static class Entity {
        int index;

        public Entity(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public void sortProcess() {
        //未排序list
        List<Entity> unSortList = new ArrayList<>();
        unSortList.add(new Entity(105));
        unSortList.add(new Entity(104));
        unSortList.add(new Entity(101));
        unSortList.add(new Entity(108));
        unSortList.add(new Entity(122));
        unSortList.add(new Entity(108));
        unSortList.add(new Entity(103));
        unSortList.add(new Entity(109));
        unSortList.add(new Entity(103));
        unSortList.add(new Entity(107));
        unSortList.add(new Entity(106));


        //排序新list
        List<Entity> sortedEntityList = new ArrayList<>();

        //遍历未排序list，排序插入新list
        for (int j = 0; j < unSortList.size(); j++) {
            Entity layerObj = unSortList.get(j);

            if (sortedEntityList.size() < 1) {
                sortedEntityList.add(layerObj);
            } else {
                for (int i = 0; i < sortedEntityList.size(); i++) {
                    //取第一个item
                    Entity layerObj1 = sortedEntityList.get(i);
                    try {
                        //取第二个item
                        Entity layerObj2 = sortedEntityList.get(i + 1);
                        //zIndex在两个item之间
                        if (layerObj.getIndex() > layerObj1.getIndex() && layerObj.getIndex() < layerObj2
                                .getIndex()) {
                            sortedEntityList.add(i + 1, layerObj);
                            break;
                        }
                        //zIndex小等于第一个item
                        if (layerObj.getIndex() <= layerObj1.getIndex()) {
                            sortedEntityList.add(i, layerObj);
                            break;
                        }

                        //zIndex等于第二个item
                        if (layerObj.getIndex() == layerObj2.getIndex()) {
                            sortedEntityList.add((i + 1) + 1, layerObj);
                            break;
                        }


                    }
                    //异常说明i到了末尾，此时跟第i个比就行
                    catch (IndexOutOfBoundsException e) {
                        //zIndex 大于第一个item
                        if (layerObj.getIndex() > layerObj1.getIndex()) {
                            sortedEntityList.add(i + 1, layerObj);
                            break;
                        }
                        //zIndex等于第一个item的
                        if (layerObj.getIndex() == layerObj1.getIndex()) {
                            sortedEntityList.add(i + 1, layerObj);
                            break;
                        }
                        //zIndex小于第一个item的
                        if (layerObj.getIndex() < layerObj1.getIndex()) {
                            sortedEntityList.add(i, layerObj);
                            break;
                        }
                    }
                }
            }
        }

        //log sorted list
        for (int i = 0; i < sortedEntityList.size(); i++) {
            System.out.println(sortedEntityList.get(i).getIndex());
        }

    }

    public static void main(String[] args) {
        new SortWhenInsert().sortProcess();
    }
}
