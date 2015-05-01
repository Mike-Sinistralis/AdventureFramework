package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureConfigurable;
import com.adventureframework.core.enums.AttributeCategory;
import com.adventureframework.core.exceptions.InvalidAttributeConfigurationException;

import java.util.Map;

public class ResourceAttributeFactory extends AttributeFactory {

    private double baseRegen;

    protected class ResourceAttribute extends Attribute {
        private double totalRegen;
        private double currentAmount;

        private double baseRegen;
        private double flatRegen;

        private ResourceAttribute(String name, Integer baseAmount, double baseRegen) {
            super(name, baseAmount);
            this.currentAmount = baseAmount;
            this.baseRegen = baseRegen;
        }

        private void deplete(int amount) {
            currentAmount -= amount;
        }

        public void alterCurrentAmount(int amount) {
            currentAmount = (double) Math.floor(currentAmount + amount);
        }

        public void calculateTotalRegen() {
            totalRegen = baseRegen + flatRegen;
        }

        public void alterBaseRegen(double amount) {
            baseRegen += amount;
        }

        public void alterFlatRegen(double amount) {
            flatRegen += amount;
        }

        public double getTotalRegen() {
            return totalRegen;
        }

        //TODO Add Ability Arguement, this should call deplete if true
        public boolean canUse() {
            return false;
        }
    }

    public ResourceAttributeFactory(String name, String description, Boolean isEnabled, Integer weight, AttributeCategory category, Integer baseAmount, double baseRegen) {
        super(name, description, isEnabled, weight, category, baseAmount);
        this.baseRegen = baseRegen;
    }

    @Override
    public ResourceAttribute createAttribute() {
        ResourceAttribute newAttribute = new ResourceAttribute(this.getName(), this.getBaseAmount(), baseRegen);
        newAttribute.alterCurrentAmount(this.getBaseAmount());
        return newAttribute;
    }

    @Override
    public Map<String, AdventureConfigurable> writeConfig() {
        Map<String, AdventureConfigurable> config = super.writeConfig();

        config.put("Regeneration", new AdventureConfigurable().setConfigValue(baseRegen).setDescription("The base amount of this resource to regenerate every second"));

        return config;
    }

    @Override
    public void loadConfig(Map<String, String> attributeConfig) {
        super.loadConfig(attributeConfig);
        try {
            baseRegen = Double.parseDouble(attributeConfig.get("Regeneration"));
        } catch (Exception e) {
            throw new InvalidAttributeConfigurationException(e.getMessage());
        }
    }
}
