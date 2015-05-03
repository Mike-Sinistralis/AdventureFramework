package com.adventureframework.content.stats;

import com.adventureframework.stats.attributes.AttributeController;
import com.adventureframework.stats.attributes.AttributeFactory;
import com.adventureframework.stats.attributes.DiminishingAttributeFactory;
import com.adventureframework.stats.attributes.ResourceAttributeFactory;

/**
 * A class that loads all default Stat module content into the system.
 */
public class StatLoader {

        public static void stageAttributeFactories(AttributeController attributeController)
        {
            ResourceAttributeFactory health = new ResourceAttributeFactory("Health", "The amount of damage you can take until you die", true, 20, .02);

            attributeController.stageAttributeFactory(health);

            AttributeFactory power = new AttributeFactory("Power", "Increases the amount of damage you deal with physical attacks", true, 1);

            attributeController.stageAttributeFactory(power);

            DiminishingAttributeFactory armor = new DiminishingAttributeFactory("Armor", "Decreases the amount of damage received from physical attacks", true, 0);

            attributeController.stageAttributeFactory(armor);
        }
}
