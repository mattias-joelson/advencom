package org.joelson.mattias.advencom;

import org.joelson.mattias.advencom.calc.ProductionCalculator;
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
        for (int i = 1; i < placeboNames.length; i += 1) {
            double singleProductionPerSecond = placeboProduction[i] * power * Math.pow(2, placeboResearcherLevels[i]) * (chance * bonus - chance + 1) / placeboProductionTime[i];
            double singleProductionNoChancePerSecond = placeboProduction[i] * power * Math.pow(2, placeboResearcherLevels[i]) / placeboProductionTime[i];
            double singleProductionNoChance = placeboProduction[i] * power * Math.pow(2, placeboResearcherLevels[i]);
            System.out.printf("%s production: %f %s/s (no bonus: %f %s/s, %f %s)%n",
                    placeboNames[i], singleProductionPerSecond, placeboNames[i - 1],
                    singleProductionNoChancePerSecond, placeboNames[i - 1],
                    singleProductionNoChance, placeboNames[i - 1]);
            placeboProductionPerSecond[i] = singleProductionPerSecond;
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
        System.out.println(Arrays.toString(placeboResources));

        for (int i = 0; i < placeboProductionPerSecond.length; i += 1) {
            System.out.printf("%s:\tk %e * y %e = %e%n",
                    placeboNames[i], placeboProductionPerSecond[i], placeboResources[i],
                    placeboProductionPerSecond[i] * placeboResources[i]);
        }

        ProductionCalculator productionCalculator = new ProductionCalculator(placeboProductionPerSecond);
        double calcSum = productionCalculator.calcTotalProduction(360000, placeboResources);
        System.out.printf("*2* %s:\t%e (%s)%n", placeboNames[0], calcSum, Amount.of(calcSum));

        int t_calculated = productionCalculator.calcTimeToGoal(Amount.valueOf(1.7, "TT"), placeboResources);
        System.out.printf("*[2]* %d%n", t_calculated);

    }
}
