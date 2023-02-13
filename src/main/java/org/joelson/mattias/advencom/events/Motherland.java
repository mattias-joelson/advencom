package org.joelson.mattias.advencom.events;

import org.joelson.mattias.advencom.model.Event;
import org.joelson.mattias.advencom.model.Industry;
import org.joelson.mattias.advencom.model.Producer;
import org.joelson.mattias.advencom.model.Researcher;
import org.joelson.mattias.advencom.model.ResearcherRarity;
import org.joelson.mattias.advencom.model.ResearcherType;

public class Motherland {

    public static Event getMotherlandEvent() {
        Industry potatoIndustry = new Industry("Potato",
                new Researcher("Mactuber", ResearcherType.POWER, ResearcherRarity.RARE, 8),
                new Researcher("The Confectioners", ResearcherType.CHANCE, ResearcherRarity.RARE, 7),
                new Researcher("Monsieur Frites", ResearcherType.BONUS, ResearcherRarity.EPIC, 4),
                new Researcher("Magdonut", ResearcherType.POWER, ResearcherRarity.RARE, 5),
                new Producer("Farmers", 3, 2,
                        new Researcher("Ringo Ringlet", ResearcherType.SPEED,ResearcherRarity.COMMON, 7)),
                new Producer("Communes", 4, 4,
                        new Researcher("Commune of Doggone", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)),
                new Producer("Freights", 5, 8,
                        new Researcher("Mad Mad Marx", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)),
                new Producer("Plantations", 6, 16,
                        new Researcher("Ricky Jay", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)),
                new Producer("Irrigations", 7, 32,
                        new Researcher("Dr. Quinny Foreman", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)),
                new Producer("Greenhouses", 8, 64,
                        new Researcher("Dogg Benson", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)),
                new Producer("Barges", 9, 128,
                        new Researcher("Ernie Abe Normal", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)),
                new Producer("Cold Storages", 10, 256,
                        new Researcher("Isaac Culkin", ResearcherType.SPEED,ResearcherRarity.COMMON, 6)));
        Industry placeboIndustry = new Industry("Placebo",
                new Researcher("Mertha Joy", ResearcherType.POWER, ResearcherRarity.RARE, 7),
                new Researcher("Nurse Temple", ResearcherType.CHANCE, ResearcherRarity.RARE, 6),
                new Researcher("Hannibal Tavius", ResearcherType.BONUS, ResearcherRarity.EPIC, 4),
                new Researcher("Misa Valentine", ResearcherType.POWER, ResearcherRarity.RARE, 0),
                new Producer("Nurses", 7, 6,
                        new Researcher("Kennie Whooser", ResearcherType.SPEED, ResearcherRarity.COMMON, 7)),
                new Producer("Clinics", 8, 12,
                        new Researcher("Patcheye", ResearcherType.SPEED, ResearcherRarity.COMMON, 7)),
                new Producer("Ambulances", 9, 24,
                        new Researcher("J.D.M.D.", ResearcherType.SPEED, ResearcherRarity.COMMON, 7)),
                new Producer("Pharmacies", 10, 48,
                        new Researcher("Howlin Mac", ResearcherType.SPEED, ResearcherRarity.COMMON, 7)),
                new Producer("Blood Banks", 11, 96,
                        new Researcher("Eleanor Lynn", ResearcherType.SPEED, ResearcherRarity.COMMON, 8)));

        return new Event("Motherland",
                new Researcher("Ratchemus Prime", ResearcherType.POWER, ResearcherRarity.SUPREME, 2),
                new Researcher("Dr Shortstack", ResearcherType.CHANCE, ResearcherRarity.SUPREME, 1),
                new Researcher("Alf Stark", ResearcherType.BONUS, ResearcherRarity.SUPREME, 1),
                potatoIndustry, placeboIndustry);
    }
}
