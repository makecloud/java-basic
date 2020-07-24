package com.liuyihui.common.util;

import sun.tools.jconsole.Plotter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 字节单位智能转化显示工具
 */
public final class ByteUnitConversionUtil {

    private static BigDecimal kb = new BigDecimal("1000");
    private static BigDecimal kib = new BigDecimal("1024");
    private static BigDecimal base = new BigDecimal("1");
    private static List<BigDecimal> kbValue = new ArrayList<BigDecimal>();
    private static List<BigDecimal> kibValue = new ArrayList<BigDecimal>();
    private static String DEFAULT_FORMAT = "%.0f";//.后面代表保留几位小数

    public static enum Units {
        B(0, false),
        KB(1, false),
        KiB(1, true),
        MB(2, false),
        MiB(2, true),
        GB(3, false),
        GiB(3, true),
        TB(4, false),
        TiB(4, true),
        PB(5, false),
        PiB(5, true),
        EB(6, false),
        EiB(6, true),
        ZB(7, false),
        ZiB(7, true),
        YB(8, false),
        YiB(8, true),
        BB(9, false),
        BiB(9, true),
        NB(10, false),
        NiB(10, true),
        DB(11, false),
        DiB(11, true);
        private int index;
        private boolean isKib;

        private Units(int index, boolean isKib) {
            this.index = index;
            this.isKib = isKib;
        }

        public int value() {
            return this.index;
        }

        public boolean isKib() {
            return this.isKib;
        }

        public boolean equals(Units other) {
            return this.value() == other.value();
        }
    }

    private ByteUnitConversionUtil() {
    }

    static {
        init();
    }

    private static void init() {
        for (Units units : Units.values()) {
            if (units.equals(Units.B)) {
                kbValue.add(units.value(), base);
                kibValue.add(units.value(), base);
            } else if (units.isKib()) {
                kibValue.add(units.value(), kibValue.get(units.value() - 1).multiply(kib));
            } else {
                kbValue.add(units.value(), kbValue.get(units.value() - 1).multiply(kb));
            }
        }
    }

    public static String convert(int byteCount, Units unit) {
        return convert(byteCount, unit, DEFAULT_FORMAT);
    }

    public static String convert(int byteCount, Units unit, String format) {
        return convert(new BigDecimal(String.valueOf(byteCount)), unit, format);
    }

    public static String convert(long byteCount, Units unit) {
        return convert(byteCount, unit, DEFAULT_FORMAT);
    }

    public static String convert(long byteCount, Units unit, String format) {
        return convert(new BigDecimal(String.valueOf(byteCount)), unit, format);
    }

    public static String convert(BigDecimal byteCount, Units unit) {
        return convert(byteCount, unit, DEFAULT_FORMAT);
    }

    public static String convert(BigDecimal byteCount, Units unit, String format) {
        return doConvert(byteCount, unit, format);
    }

    private static String doConvert(BigDecimal byteCount, Units unit, String format) {
        if (unit.equals(Units.B)) {
            return byteCount.toString().split("\\.", 2)[0] + unit;
        } else {
            BigDecimal divisor = unit.isKib() ?
                    kibValue.get(unit.value()) :
                    kbValue.get(unit.value());

            return String.format(format, byteCount.divide(divisor).doubleValue());
        }
    }

    /**
     * 字节单位智能转换。大于1024转为高一个单位. fixme 方法未达到实际想要效果
     *
     * @param byteCount
     * @return
     */
    public static String smartCovert(long byteCount) {
        String mbStr = convert(byteCount, Units.MiB);
        System.out.println("smartConvert:" + mbStr);
        if (Float.parseFloat(mbStr) >= 1024) {
            return String.format("%.18s", convert(byteCount, Units.GiB));
        }
        return String.format("%.5sMB", mbStr);
    }

    public static void main(String[] args) {
        System.out.println(convert(2090047009L, Units.GiB));
        System.out.println(smartCovert(2090047009L));
        System.out.println(smartCovert(2411724800L));
    }
}