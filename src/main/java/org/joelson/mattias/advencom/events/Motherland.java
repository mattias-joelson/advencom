package org.joelson.mattias.advencom.events;

import org.joelson.mattias.advencom.model.Event;
import org.joelson.mattias.advencom.model.Industry;
import org.joelson.mattias.advencom.model.Producer;
import org.joelson.mattias.advencom.model.Researcher;
import org.joelson.mattias.advencom.model.ResearcherRarity;
import org.joelson.mattias.advencom.model.ResearcherType;

public class Motherland {

    public static Event getMotherlandEvent() {
        Industry potatoIndustry = new Industry("Placebo",
                new Researcher("Mertha Joy", ResearcherType.POWER, ResearcherRarity.RARE, 7),
                new Researcher("Nurse Temple", ResearcherType.CHANCE, ResearcherRarity.RARE, 6),
                new Researcher("Hannibal Tavius", ResearcherType.BONUS, ResearcherRarity.EPIC, 4),
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
                potatoIndustry);
    }
}
