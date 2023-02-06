package org.joelson.mattias.advencom.model;

public class Producer {

    private final String name;
    private final int produce;
    private final int productionTime;
    private final Researcher researcher;

    public Producer(String name, int produce, int productionTime, Researcher researcher) {
        this.name = name;
        this.produce = produce;
        this.productionTime = productionTime;
        this.researcher = researcher;
    }

    public String getName() {
        return name;
    }

    public int getProduce() {
        return produce;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public Researcher getResearcher() {
        return researcher;
    }
}
