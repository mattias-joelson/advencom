package org.joelson.mattias.advencom.calc;

public class ProductionCalculator {

    private final double[] productionsPerSecond;
    private final double[] productionProductsPerSecond;

    public ProductionCalculator(double[] productionsPerSecond) {
        this.productionsPerSecond = productionsPerSecond;
        this.productionProductsPerSecond = calculateProductionProductsPerSecond(this.productionsPerSecond);
    }

    private static double[] calculateProductionProductsPerSecond(double[] productionsPerSecond) {
        double[] productionProductsPerSecond = new double[productionsPerSecond.length];
        productionProductsPerSecond[0] = 1;
        for (int i = 1; i < productionsPerSecond.length; i += 1) {
            productionProductsPerSecond[i] = productionProductsPerSecond[i - 1] * productionsPerSecond[i];
        }
        return productionProductsPerSecond;
    }

    public double calcTotalProduction(int t, double[] resources) {
//        System.out.printf("t:\t%d%n", t);
        double sum = resources[0];
//        System.out.printf("y0:\t%e (%s)%n", sum, Amount.of(sum));
        double tProd = 1;
        double fact = 1;
        for (int i = 1; i < resources.length; i += 1) {
            tProd *= t;
            fact *= i;
            double y = resources[i] * productionProductsPerSecond[i] * tProd / fact;
//            System.out.printf("y%d:\t%e (%s)%n", i, y, Amount.toString(y));
            sum += y;
        }
//        System.out.printf("y:\t%e (%s)%n", sum, Amount.toString(sum));

        return sum;
    }

    public int calcTimeToGoal(double goal, double[] resources) {
        if (resources[0] >= goal) {
            return 0;
        }

        int t_a = 0;
//        double y_a = calcTotalProduction(t_a, resources);
        int t_b = (int) ((goal - resources[0]) / (resources[1] * productionsPerSecond[1]));
//        double y_b = calcTotalProduction(t_b, resources);
//        System.out.printf("[(t_a, y_a), (t_b, y_b)]%n");
//        int i = 0;
        while (t_b - t_a > 10) {
//            System.out.printf("%d - [(%d, %s), (%d, %s)]%n",
//                    i++, t_a, Amount.toString(y_a), t_b, Amount.toString(y_b));
            int t_mid = (t_a + t_b) / 2;
            double y_mid = calcTotalProduction(t_mid, resources);
            if (y_mid < goal) {
                t_a = t_mid;
//                y_a = y_mid;
            } else {
                t_b = t_mid;
//                y_b = y_mid;
            }
        }
//        System.out.printf("%d - [(%d, %s), (%d, %s)]%n",
//                i, t_a, Amount.toString(y_a), t_b, Amount.toString(y_b));
        return t_b;
    }
}
