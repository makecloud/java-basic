package com.liuyihui.common.dataType;

/**
 * 测试java的值传递引用传递
 */
public class ParamPassTest {

    class PlayObj {
        long planId;
        long screenId;
        String matId;
        String url;
        String matMD5;

        public void setPlanId(long planId) {
            this.planId = planId;
        }

        /**
         * @return
         */
        @Override
        public String toString() {
            return String.format("%s,%s,%s,%s,%s", planId, screenId, matId, url, matMD5);
        }
    }


    public void prepareAdMaterial(final PlayObj playObj) {

        playObj.setPlanId(1L);
        //复制到另一个变量
        PlayObj currentPlayObj = playObj;

        //本意是改变另一个变量的字段。但是这是误区，改的还是参数playObj的
        currentPlayObj.url = "aaa";
    }


    public void Test() {
        PlayObj playObj = new PlayObj();
        playObj.planId = 324L;
        playObj.screenId = 55L;
        playObj.matId = "djkf";
        playObj.url = "04230sfj";
        playObj.matMD5 = "JLDHF#@&%";

        System.out.println(playObj);

        prepareAdMaterial(playObj);

        //playObj对象的字段已经 上面方法改掉了，上面方法没有传进去一个拷贝对象，而是playObj本身
        System.out.println(playObj);


    }

    public static void main(String[] args) {
        new ParamPassTest().Test();
    }


}
