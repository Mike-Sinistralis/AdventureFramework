package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureConfigurable;
import com.adventureframework.core.IConfigurable;
import com.adventureframework.core.enums.AttributeCategory;
import com.adventureframework.core.exceptions.InvalidAttributeConfigurationException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Factory that produces Attribute objects and stores configurations for that specific attribute.
 * To register an AttributeFactory, you need to create an instance of it and load it with proper default values, and then reference
 * {@link AttributeController}.
 */
public class AttributeFactory implements IConfigurable {

    private String name = "Attribute";
    private String description = "Need Description";
    private Boolean isEnabled = true;
    private Integer weight = 0;
    private AttributeCategory category = AttributeCategory.PRIMARY;
    private Integer baseAmount = 0;

    /**
     * A small utility object that manages the current amount of the given attribute
     */
    protected class Attribute {
        private String attrName;
        private int baseValue = 0;
        private int flatModifier = 0;
        private float multiplicativeModifier = 1;

        protected Attribute(String name, Integer baseAmount)
        {
            this.attrName = name;
            this.baseValue = baseAmount;
        }

        private int currentTotalValue = 0;

        private int calculateTotalValue() {
            int result = (int) Math.floor((baseValue + flatModifier) * (multiplicativeModifier));
            return result >= 0 ? result : 0;
        }

        public void alterBaseValue(float amount) {
            baseValue += amount;
            currentTotalValue = calculateTotalValue();
        }

        public void alterFlatModifier(int amount) {
            flatModifier += amount;
            currentTotalValue = calculateTotalValue();
        }

        public void alterMultiplicativeModifier(float amount) {
            multiplicativeModifier += amount;
            currentTotalValue = calculateTotalValue();
        }

        public String getAttributeName()
        {
            return attrName;
        }

        public double getCurrentTotalValue() {
            return currentTotalValue;
        }
    }

    /**
     * Returns a factory capable of producing attribute objects based on the provided configurations. Must be registered through the {@link AttributeController}
     * before the game will recognize it.
     *
     * @param name Name of the attribute. This must be unique, and is not configurable. Multiple AttributeFactories of the same name will throw an exception.
     * @param description Description of the attribute, mostly used for tooltips.
     * @param isEnabled Whether the attribute should be loaded and used in-game.
     * @param baseAmount The starting amount of this attribute all entities start with before modifications.
     */
    public AttributeFactory(String name, String description, Boolean isEnabled, Integer baseAmount) {
        this.name = name;
        this.description = description;
        this.isEnabled = isEnabled;
        this.baseAmount = baseAmount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public Integer getBaseAmount() {
        return baseAmount;
    }

    /**
     * Create an Attribute object based on the configurations currently loaded into this factory.
     *
     * @return The Attribute object for this name type, preloaded with a base amount.
     */
    public Attribute createAttribute() {
        Attribute newAttribute = new Attribute(this.name, this.baseAmount);
        newAttribute.alterBaseValue(baseAmount);
        return newAttribute;
    }

    /**
     * Generates a data structure to be used by AdventureControllers to create config values so this attribute can be configured outside of the code base.
     *
     * @return A Hashmap of field:value/description entries to generate a config file from.
     */
    public Map<String, AdventureConfigurable> writeConfig() {
        Map<String, AdventureConfigurable> config = new HashMap<>();

        config.put("Description", new AdventureConfigurable().setConfigValue(description).setDescription("Describes what the attribute does. Primarily used in tooltips."));
        config.put("Enabled", new AdventureConfigurable().setConfigValue(isEnabled).setDescription("Determines if this attribute should be loaded. If enabled on a server that already has gear and abilities allocated with this attribute, THEY WILL NOT BE REFUNDED. Points can be recovered with reincarnation. There will be an Admin only method of reincarnating a soul without penalty to assist with migrations. Note that any existing gear with this attribute will simply have that property permanently deleted. If you are on an existing server, make sure you have a plan before disabling attributes that have been in use."));
        config.put("Starting Amount", new AdventureConfigurable().setConfigValue(baseAmount).setDescription("The amount of this stat every player starts with."));

        return config;
    }

    /**
     * Reads in a data structure to update this AttributeFactory with new configurations. Note that players will not recieve these changes until they load a
     * new attribute object.
     *
     *
     * @param attributeConfig A Hashmap of field:value entries to load into the AttributeFactory.
     */
    public void loadConfig(Map<String, String> attributeConfig) {
        safeConfig(attributeConfig.entrySet().iterator());
    }

    protected Iterator<Map.Entry<String, String>> safeConfig(Iterator<Map.Entry<String, String>> attributeConfig)
    {
        try {
            while(attributeConfig.hasNext()) {
                Map.Entry<String, String> attributeEntry = attributeConfig.next();
                boolean remove = true;

                switch (attributeEntry.getKey()) {
                    case "Description":
                        description = attributeEntry.getValue();
                        break;
                    case "Enabled":
                        isEnabled = Boolean.valueOf(attributeEntry.getValue());
                        break;
                    case "Starting Amount":
                        baseAmount = Integer.parseInt(attributeEntry.getValue().toUpperCase());
                        break;
                    default:
                        remove = false;
                        break;
                }
                if (remove)
                    attributeConfig.remove();
            }
        }
        catch (Exception e) {
            throw new InvalidAttributeConfigurationException(e.getCause() + e.getMessage());
        }
        return attributeConfig;
    }
}