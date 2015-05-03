package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureConfigurable;
import com.adventureframework.core.IConfigurable;
import com.adventureframework.core.enums.AttributeCategory;
import com.adventureframework.core.exceptions.InvalidAttributeConfigurationException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Attribute Object
 * <p/>
 * There is no complex constructor for this class, instead you may chain set methods from the constructor.
 * <p/>
 * The Attribute class is used to define a property attached to every Entity within minecraft to create more complex combat rules.
 * You can alter the Attribute value with additive or multiplicative methods, allowing you complete control over effects that could increase
 * or decrease this value. All Attributes implement {@link IConfigurable} which will write and load from Stats.cfg. If you extend this
 * class, make sure to expose all fields. We want to keep everything highly configurable.
 * <p/>
 * After creating and initializing an Attribute object, you will need to load it with @link AttributeController.stageAttributeFactory.
 * <p/>
 * Attributes must be unique. There cannot be 2 "Health" attributes for example.
 */
public class AttributeFactory implements IConfigurable {

    private String name = "Attribute";
    private String description = "Need Description";
    private Boolean isEnabled = true;
    private Integer weight = 0;
    private AttributeCategory category = AttributeCategory.PRIMARY;
    private Integer baseAmount = 0;

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

    public AttributeFactory(String name, String description, Boolean isEnabled, Integer weight, AttributeCategory category, Integer baseAmount) {
        this.name = name;
        this.description = description;
        this.isEnabled = isEnabled;
        this.weight = weight;
        this.category = category;
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

    public Integer getWeight() {
        return weight;
    }

    public AttributeCategory getCategory() {
        return category;
    }

    public Integer getBaseAmount() {
        return baseAmount;
    }

    public Attribute createAttribute() {
        Attribute newAttribute = new Attribute(this.name, this.baseAmount);
        newAttribute.alterBaseValue(baseAmount);
        return newAttribute;
    }

    public Map<String, AdventureConfigurable> writeConfig() {
        Map<String, AdventureConfigurable> config = new HashMap<>();

        config.put("Description", new AdventureConfigurable().setConfigValue(description).setDescription("Describes what the attribute does. Primarily used in tooltips."));
        config.put("Enabled", new AdventureConfigurable().setConfigValue(isEnabled).setDescription("Determines if this attribute should be loaded. If enabled on a server that already has gear and abilities allocated with this attribute, THEY WILL NOT BE REFUNDED. Points can be recovered with reincarnation. There will be an Admin only method of reincarnating a soul without penalty to assist with migrations. Note that any existing gear with this attribute will simply have that property permanently deleted. If you are on an existing server, make sure you have a plan before disabling attributes that have been in use."));
        config.put("Weight", new AdventureConfigurable().setConfigValue(weight).setDescription("The weight per point of this attribute when assigning properties to gear. Higher weight means this attribute appears on gear with lower values, or less combinations."));
        config.put("Category", new AdventureConfigurable().setConfigValue(category).setDescription("The category this attribute is calculated against when determining which properties to apply on gear."));
        config.put("Starting Amount", new AdventureConfigurable().setConfigValue(baseAmount).setDescription("The amount of this stat every player starts with."));

        return config;
    }

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
                    case "Weight":
                        weight = Integer.parseInt(attributeEntry.getValue());
                        break;
                    case "Category":
                        category = AttributeCategory.valueOf(attributeEntry.getValue().toUpperCase());
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