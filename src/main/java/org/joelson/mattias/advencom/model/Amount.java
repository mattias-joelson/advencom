package org.joelson.mattias.advencom.model;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Amount {

    private enum Range {
        M(6), B(9), T(12),
        AA(15), BB(18), CC(21), DD(24), EE(27), FF(30), GG(33), HH(36), II(39),
        JJ(42), KK(45), LL(48), MM(51), NN(54), OO(57), PP(60), QQ(63), RR(66),
        SS(69), TT(72), UU(75), VV(78), WW(81), XX(84), YY(87), ZZ(90);

        private static final Map<Integer, Range> BY_EXP = new HashMap<>();

        static {
            for (Range r : values()) {
                BY_EXP.put(r.getExp(), r);
            }
        }
        private int exp;
        Range(int exp) {
            this.exp = exp;
        }

        public int getExp() {
            return exp;
        }

        public static Range valueOfExp(int exp) {
            return BY_EXP.get(exp);
        }
    }

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    private final double value;

    public Amount(double value, String range)
    {
        this.value = value * Math.pow(10, rangeToValue(range));
    }

    public Amount(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return toString(value);
    }

    private int rangeToValue(String range) {
        return (range.isEmpty()) ? 0 : Range.valueOf(range.toUpperCase()).getExp();
    }

    public static Amount of(double value, String range) {
        return new Amount(value, range);
    }

    public static Amount of(double value) {
        return new Amount(value);
    }

    public static double valueOf(double value, String range) {
        return of(value, range).getValue();
    }

    public static String toString(double value) {
        if (value < 1000000) {
            return Long.toString(Math.round(value));
        }
        double log = Math.log10(value);
        int exp = (((int) Math.floor(log)) / 3) * 3;
        Range range = Range.valueOfExp(exp);
        double remainingValue = value / Math.pow(10, range.getExp());
        return DECIMAL_FORMAT.format(remainingValue) + " " + range;
    }
}
