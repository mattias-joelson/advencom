package org.joelson.mattias.advencom;

import org.joelson.mattias.advencom.model.Amount;

import java.util.Arrays;

public class AdVenComCalculator {

    public static void main(String[] args) {
        //System.out.println("Hello, world!");
        calc();
    }

    private static void calc() {
        String[] placeboNames = { "Placebo", "Nurses", "Clinics", "Ambulances", "Pharmacies", "Blood Banks" };
        String[] placeboResearcherNames = { null, "Kennie Whooser", "Patcheye", "J.D.M.D.", "Howlin Mac", "Eleanor Lynn" };
        Integer[] placeboResearcherLevels = { null, 7, 7, 7, 7, 8 };
        Integer[] placeboProduction = { null, 7, 8, 9, 10, 11 }; // j + 6
        Integer[] placeboProductionTime = { null, 6, 12, 24, 48, 96 }; // 3*2^j

        int boostPower = 2;
        String placeboPowerResearcherName = "Mertha Joy";
        int placeboPowerResearcherLevel = 7;
        String motherlandPowerResearcherName = "Ratchemus Prime";
        int motherlandPowerResearcherLevel = 2;
        double power = boostPower * Math.pow(2, placeboPowerResearcherLevel + motherlandPowerResearcherLevel);

        String placeboChanceResearcherName = "Nurse Temple";
        int placeboChanceResearcherLevel = 6;
        float placeboChance = 0.2125f; // CMH
        String motherlandChanceResearcherName = "Dr Shortstack";
        int motherlandChanceResearcherLevel = 1;
        float motherlandChance = 0.08f; // CMH
        float chance = 0.01f + placeboChance + motherlandChance;

        String placeboBonusResearcherName = "Hannibal Tavius";
        int placeboBonusResearcherLevel = 4;
        String motherlandBonusResearcherName = "Alf Stark";
        int motherlandBonusResearcherLevel = 1;
        double bonus = 2 * Math.pow(4, placeboBonusResearcherLevel + motherlandBonusResearcherLevel);

        for (int i = 1; i < placeboResearcherNames.length; i += 1) {
            System.out.printf("%s (%s): lvl %d -> speed %f%n",
                    placeboResearcherNames[i], placeboNames[i - 1], placeboResearcherLevels[i], Math.pow(2, placeboResearcherLevels[i]));
        }
        System.out.println();

        System.out.printf("Boost power: %d%n", boostPower);
        System.out.printf("%s: lvl %d -> power %f%n",
                placeboPowerResearcherName, placeboPowerResearcherLevel, Math.pow(2, placeboPowerResearcherLevel));
        System.out.printf("%s: lvl %d -> power %f%n",
                motherlandPowerResearcherName, motherlandPowerResearcherLevel, Math.pow(2, motherlandPowerResearcherLevel));
        System.out.printf("power: %f%n", power);
        System.out.println();

        System.out.printf("%s: lvl %d -> chance %f%n",
                placeboChanceResearcherName, placeboChanceResearcherLevel, placeboChance);
        System.out.printf("%s: lvl %d -> chance %f%n",
                motherlandChanceResearcherName, motherlandChanceResearcherLevel, motherlandChance);
        System.out.printf("chance: %f%n", chance);
        System.out.println();

        System.out.printf("%s: lvl %d -> bonus %f%n",
                placeboBonusResearcherName, placeboBonusResearcherLevel, Math.pow(4, placeboBonusResearcherLevel));
        System.out.printf("%s: lvl %d -> bonus %f%n",
                motherlandBonusResearcherName, motherlandBonusResearcherLevel, Math.pow(2, motherlandBonusResearcherLevel));
        System.out.printf("bonus: %f%n", bonus);
        System.out.println();

        double[] placeboProductionPerSecond = new double[placeboNames.length];
        double[] placeboProductionPerSecondProduct = new double[placeboNames.length];
        placeboProductionPerSecondProduct[0] = 1;
        for (int i = 1; i < placeboNames.length; i += 1) {
            double singleProductionPerSecond = placeboProduction[i] * power * Math.pow(2, placeboResearcherLevels[i]) * (chance * bonus - chance + 1) / placeboProductionTime[i];
            double singleProductionNoChancePerSecond = placeboProduction[i] * power * Math.pow(2, placeboResearcherLevels[i]) / placeboProductionTime[i];
            double singleProductionNoChance = placeboProduction[i] * power * Math.pow(2, placeboResearcherLevels[i]);
            System.out.printf("%s production: %f %s/s (no bonus: %f %s/s, %f %s)%n",
                    placeboNames[i], singleProductionPerSecond, placeboNames[i - 1],
                    singleProductionNoChancePerSecond, placeboNames[i - 1],
                    singleProductionNoChance, placeboNames[i - 1]);
            placeboProductionPerSecond[i] = singleProductionPerSecond;
            placeboProductionPerSecondProduct[i] = placeboProductionPerSecondProduct[i - 1] * singleProductionPerSecond;
        }

        // 556,42 SS
        // 99,93 OO -> 15,28 QQ
        // 41,02 KK -> 3,58 MM
        // 25,71 GG -> 1,26 II
        // 22,76 CC -> 621,73 DD
        // 11,74 B -> 352,86 T
        double[] placeboResources = {
                Amount.valueOf(556.42, "SS"),
                Amount.valueOf(99.93, "OO"),
                Amount.valueOf(41.02, "KK"),
                Amount.valueOf(25.71, "GG"),
                Amount.valueOf(22.76, "CC"),
                Amount.valueOf(11.74, "B")
        };

        System.out.println(Arrays.toString(placeboProductionPerSecond));
        System.out.println(Arrays.toString(placeboProductionPerSecondProduct));
        System.out.println(Arrays.toString(placeboResources));

        for (int i = 0; i < placeboProductionPerSecond.length; i += 1) {
            System.out.printf("%s:\tk %e * y %e = %e%n",
                    placeboNames[i], placeboProductionPerSecond[i], placeboResources[i],
                    placeboProductionPerSecond[i] * placeboResources[i]);
        }

        double t = 360000;
        System.out.printf("t:\t%f%n", t);
        double sum = placeboResources[0];
        System.out.printf("%s:\t%e (%s)%n", placeboNames[0], sum, Amount.of(sum));
        double tProd = 1;
        double fact = 1;
        for (int i = 1; i < placeboNames.length; i += 1) {
            tProd *= t;
            fact *= i;
            double y = placeboResources[i] * placeboProductionPerSecondProduct[i] * tProd / fact;
            System.out.printf("%s:\t%e (%s)%n", placeboNames[i], y, Amount.of(y));
            sum += y;
        }
        System.out.printf("%s:\t%e (%s)%n", placeboNames[0], sum, Amount.of(sum));

        double goal = Amount.valueOf(1.7, "TT");
        int t_a = 0;
        double y_a = calcY(t_a, placeboResources, placeboProductionPerSecondProduct);
        // if y0 > goal done
        int t_b = (int) ((goal - placeboResources[0]) / (placeboProductionPerSecond[1] * placeboResources[1]));
        double y_b = calcY(t_b, placeboResources, placeboProductionPerSecondProduct);
        System.out.printf("[(t_a, y_a), (t_b, y_b)]%n");
        int i = 0;
        while (t_b - t_a > 10) {
            System.out.printf("%d - [(%d, %s), (%d, %s)]%n",
                    i++, t_a, Amount.toString(y_a), t_b, Amount.toString(y_b));
            int t_mid = (t_a + t_b) / 2;
            double y_mid = calcY(t_mid, placeboResources, placeboProductionPerSecondProduct);
            if (y_mid < goal) {
                t_a = t_mid;
                y_a = y_mid;
            } else {
                t_b = t_mid;
                y_b = y_mid;
            }
        }
        System.out.printf("%d - [(%d, %s), (%d, %s)]%n",
                i, t_a, Amount.toString(y_a), t_b, Amount.toString(y_b));
    }

    private static double calcY(int t, double[] placeboResources, double[] placeboProductionPerSecondProduct) {
        System.out.printf("t:\t%d%n", t);
        double sum = placeboResources[0];
        System.out.printf("y0:\t%e (%s)%n", sum, Amount.of(sum));
        double tProd = 1;
        double fact = 1;
        for (int i = 1; i < placeboResources.length; i += 1) {
            tProd *= t;
            fact *= i;
            double y = placeboResources[i] * placeboProductionPerSecondProduct[i] * tProd / fact;
            System.out.printf("y%d:\t%e (%s)%n", i, y, Amount.toString(y));
            sum += y;
        }
        System.out.printf("y:\t%e (%s)%n", sum, Amount.toString(sum));

        return sum;
    }
}
