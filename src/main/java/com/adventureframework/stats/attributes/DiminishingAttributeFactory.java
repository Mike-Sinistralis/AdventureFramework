package com.adventureframework.stats.attributes;

public class DiminishingAttributeFactory extends AttributeFactory {

    /**
     * Returns a factory capable of producing attribute objects with specific behavior in regards to having diminishing returns. If an attribute
     * needs to have varying levels of potency based on the entities level, then it needs created here.
     *
     * @param name Name of the attribute. This must be unique, and is not configurable. Multiple AttributeFactories of the same name will throw an exception.
     * @param description Description of the attribute, mostly used for tooltips.
     * @param isEnabled Whether the attribute should be loaded and used in-game.
     * @param baseAmount The starting amount of this attribute all entities start with before modifications.
     */
    public DiminishingAttributeFactory(String name, String description, Boolean isEnabled, Integer baseAmount) {
        super(name, description, isEnabled, baseAmount);
    }

}
