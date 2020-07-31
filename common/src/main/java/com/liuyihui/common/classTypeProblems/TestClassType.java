package com.liuyihui.common.classTypeProblems;

import com.liuyihui.common.Enum.EResumeWay;
import com.liuyihui.common.classTypeProblems.entity.Container;
import com.liuyihui.common.classTypeProblems.entity.PositionAbstract;
import com.liuyihui.common.classTypeProblems.entity.PositionAbstractSelectable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.liuyihui.common.Enum.EResumeWay.WILL_RESUME_AUTO;
import static com.liuyihui.common.Enum.EResumeWay.WILL_RESUME_BY_EXTERNAL;

public class TestClassType {


    public static void main(String[] args) {
//        testCast();
        testListCast();
    }

    /**
     * 测试类名
     */
    @Test
    public void testClassName() {
        System.out.println(TestClassType.class.getPackage());
        System.out.println(TestClassType.class.getName());
    }

    /**
     * 测试枚举
     */
    @Test
    public void testSwitchEnum() {
        EResumeWay parm = WILL_RESUME_AUTO;
        switch (parm) {
            case WILL_RESUME_AUTO:
                break;
            default:
                break;
        }

//        parm = null;
        switch (parm) {
            case WILL_RESUME_AUTO:
                System.out.println(WILL_RESUME_AUTO);
            case WILL_RESUME_BY_EXTERNAL:
                System.out.println(WILL_RESUME_BY_EXTERNAL);
            default:
                System.out.println("null");
        }

        System.out.println(WILL_RESUME_AUTO);
        System.out.println(WILL_RESUME_AUTO.name());
        System.out.println(EResumeWay.valueOf("WILL_RESUME_AUTO"));
//        System.out.println(Enum.valueOf(EResumeWay.class, ""));//报错
    }
    /**
     * 父类的对象赋值给子类的引用, 类型转换失败.
     */
    public static void testCast() {
        //使用父类创建对象
        PositionAbstract positionAbstract = new PositionAbstract();
        positionAbstract.setName("媒体位1");

        //子类引用使用父类对象.即父类型对象造型为子类型对象  运行结果:这里是强转失败的,抛异常的.
        PositionAbstractSelectable positionAbstractSelectable = (PositionAbstractSelectable) positionAbstract;

        //打印结果
        System.out.println(positionAbstractSelectable.getName());
    }

    public static void testListCast() {
        List<PositionAbstract> positionAbstractList = new ArrayList<>();
        // list更是不能赋值, 转都不能转
        //        List<PositionAbstractSelectable> positionAbstractSelectableList = positionAbstractList;
    }

    /**
     * 测试 ? super 的用法
     */
    public static void testKeyWordSuper() {

        //这样可以
        Container<? super PositionAbstractSelectable> container = new Container<>();
        container.func(new PositionAbstractSelectable());

        //这也不行
        ArrayList<? extends PositionAbstract> positionList = new ArrayList<PositionAbstractSelectable>();
        //positionList.add(new PositionAbstract());

        //这也不行
        List<? super PositionAbstractSelectable> positionList1 = new ArrayList<>();
        //positionList1.add(new PositionAbstract());

        //这样行
        List<? super PositionAbstract> positionList2 = new ArrayList<>();
        //PositionAbstractSelectable不是PositionAbstract的超类(基类)啊, 怎么不报错? 难道 ?super通配子类?
        positionList2.add(new PositionAbstractSelectable());

        //这也不行
        List<PositionAbstractSelectable> positionAbstractSelectableList = new ArrayList<>();
        //positionList=positionAbstractSelectableList;

        //这样行
        List<PositionAbstract> positionAbstractList = new ArrayList<>();
        positionAbstractList.add(new PositionAbstractSelectable());

    }
}
