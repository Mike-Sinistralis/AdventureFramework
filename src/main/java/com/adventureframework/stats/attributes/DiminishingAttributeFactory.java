package com.adventureframework.stats.attributes;

import com.adventureframework.core.enums.AttributeCategory;

public class DiminishingAttributeFactory extends AttributeFactory {

    public DiminishingAttributeFactory(String name, String description, Boolean isEnabled, Integer weight, AttributeCategory category, Integer baseAmount) {
        super(name, description, isEnabled, weight, category, baseAmount);
    }

}
