package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY;

public abstract class BaseExplorer implements Explorer {

    private static final double SEARCH_MODIFIER = 15;

    private String name;
    private double energy;
    private Suitcase suitcase;

    protected BaseExplorer(String name, double energy) {
        setName(name);
        setEnergy(energy);
        suitcase = new Carton();
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        energy = Math.max(0, energy - SEARCH_MODIFIER);
    }

    @Override
    public boolean canSearch() {
        return energy > 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_EXPLORER_NAME, getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(FINAL_EXPLORER_ENERGY, getEnergy()));
        sb.append(System.lineSeparator());

        String output = getSuitcase().getExhibits().isEmpty() ? "None" :
                String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, getSuitcase().getExhibits());

        sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, output));

        return sb.toString().trim();
    }
}
