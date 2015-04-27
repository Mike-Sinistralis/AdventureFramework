package com.adventureframework.Content.Stats;

import com.adventureframework.Core.Enums.AttributeCategory;
import com.adventureframework.Stats.Attributes.Attribute;
import com.adventureframework.Stats.Attributes.AttributeController;
import com.adventureframework.Stats.Attributes.DiminishingAttribute;
import com.adventureframework.Stats.Attributes.ResourceAttribute;

public class StatLoader {

        public static void stageAttributes(AttributeController attributeController)
        {
            ResourceAttribute health = (ResourceAttribute)new ResourceAttribute().setName("Health").setDescription("The amount of damage you can take until you die").setAttributeWeight(1).setCategory(AttributeCategory.PRIMARY).setEnabled(true);

            attributeController.stageAttribute(health);

            Attribute power = new Attribute().setName("Power").setDescription("Increases the amount of damage dealt with physical attacks.").setAttributeWeight(2).setCategory(AttributeCategory.PRIMARY).setEnabled(true);

            attributeController.stageAttribute(power);

            DiminishingAttribute armor = (DiminishingAttribute)new DiminishingAttribute().setName("Armor").setDescription("Decreases the amount of damage recieved from physical attacks").setAttributeWeight(1).setCategory(AttributeCategory.PRIMARY).setEnabled(true);

            attributeController.stageAttribute(armor);
        }
}
