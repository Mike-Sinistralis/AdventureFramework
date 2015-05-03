package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureConfigurable;
import com.adventureframework.core.enums.AttributeCategory;
import com.adventureframework.core.exceptions.InvalidAttributeConfigurationException;

import java.util.Iterator;
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
    }

    /**
     * Returns a factory capable of producing attribute objects that are designed to deplete and restore based on a maximum value. These tend to deal with
     * regeneration and consumption checks.
     *
     * @param name Name of the attribute. This must be unique, and is not configurable. Multiple AttributeFactories of the same name will throw an exception.
     * @param description Description of the attribute, mostly used for tooltips.
     * @param isEnabled Whether the attribute should be loaded and used in-game.
     * @param baseAmount The starting amount of this attribute all entities start with before modifications.
     * @param baseRegen The amount that attributes this factory creates regenerate every second before modification.
     */
    public ResourceAttributeFactory(String name, String description, Boolean isEnabled, Integer baseAmount, double baseRegen) {
        super(name, description, isEnabled, baseAmount);
        this.baseRegen = baseRegen;
    }

    @Override
    public ResourceAttribute createAttribute() {
        ResourceAttribute newAttribute = new ResourceAttribute(this.getName(), this.getBaseAmount(), baseRegen);
        newAttribute.alterCurrentAmount(this.getBaseAmount());
        return newAttribute;
    }

    /**
     * Generates a data structure to be used by AdventureControllers to create config values so this attribute can be configured outside of the code base.
     *
     * @return A Hashmap of field:value/description entries to generate a config file from.
     */
    @Override
    public Map<String, AdventureConfigurable> writeConfig() {
        Map<String, AdventureConfigurable> config = super.writeConfig();

        config.put("Regeneration", new AdventureConfigurable().setConfigValue(baseRegen).setDescription("The base amount of this resource to regenerate every second"));

        return config;
    }

    @Override
    protected Iterator<Map.Entry<String, String>> safeConfig(Iterator<Map.Entry<String, String>> attributeConfig)
    {
        Iterator<Map.Entry<String, String>> mutatedConfig = super.safeConfig(attributeConfig);
        try {
            while(mutatedConfig.hasNext()) {
                Map.Entry<String, String> attributeEntry = attributeConfig.next();

                switch (attributeEntry.getKey()) {
                    case "Regeneration":
                        baseRegen = Double.parseDouble(attributeEntry.getValue());
                        break;
                }
                attributeConfig.remove();
            }
        }
        catch (Exception e) {
            throw new InvalidAttributeConfigurationException(e.getMessage());
        }
        return attributeConfig;
    }
}
