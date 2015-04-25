package com.sinistralis.AdventureFramework.Stats.Attributes;

import org.w3c.dom.Attr;

import java.util.HashMap;
import java.util.Map;
import javax.json.*;


public abstract class Attribute {

    private String name = "Attribute";
    private String description = "Need Description";
    private boolean isEnabled = true;
    private Map<String, Integer> GainPerAttribute = new HashMap<>();
    private Map<String, Integer> InitialAttribute = new HashMap<>();
    private int attributeWeight = 0;
    private AttributeCategory category = AttributeCategory.PRIMARY;

    private float baseValue = 0;
    private int flatModifier = 0;
    private float multiplicativeModifier = 1;

    private double currentTotalValue = 0;

    private double calculateTotalValue()
    {
        double result = Math.floor((baseValue + flatModifier) * (1 + multiplicativeModifier));
        return result >= 0 ? result : 0;
    }



    public JsonObjectBuilder getConfigJson()
    {
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(new HashMap<String, Object>());

        JsonObjectBuilder attributeConfig = jsonFactory.createObjectBuilder();
        JsonObjectBuilder gainPerAttributeConfig = jsonFactory.createObjectBuilder();
        JsonObjectBuilder initialAttributeConfig = jsonFactory.createObjectBuilder();

        for(Map.Entry<String, Integer> gainConfig : GainPerAttribute.entrySet())
        {
            gainPerAttributeConfig.add(gainConfig.getKey(), gainConfig.getValue());
        }

        for(Map.Entry<String, Integer> initialConfig : InitialAttribute.entrySet())
        {
            initialAttributeConfig.add(initialConfig.getKey(), initialConfig.getValue());
        }

        attributeConfig
            .add("AttributeName", name)
            .add("AttributeDescription", description)
            .add("AttributeEnabled", isEnabled)
            .add("GainPerAttribute", gainPerAttributeConfig)
            .add("AttributeInitial", initialAttributeConfig)
            .add("AttributeEquipmentWeight", attributeWeight)
            .add("AttributeEquipmentCategory", category.name());

        return attributeConfig;
    }

    public void loadConfigJson(JsonObject attributeConfig)
    {
        JsonObject gainPerAttributeConfig;
        JsonObject initialAttributeConfig;

        if(!attributeConfig.isNull("GainPerAttribute"))
        {
            gainPerAttributeConfig = attributeConfig.getJsonObject("GainPerAttribute");

            for(Map.Entry<String, Integer> gainConfig : GainPerAttribute.entrySet())
            {
                gainConfig.setValue(gainPerAttributeConfig.getInt(gainConfig.getKey(), gainConfig.getValue()));
            }
        }

        if(!attributeConfig.isNull("AttributeInitial"))
        {
            initialAttributeConfig = attributeConfig.getJsonObject("AttributeInitial");

            for(Map.Entry<String, Integer> initialConfig : InitialAttribute.entrySet())
            {
                initialConfig.setValue(initialAttributeConfig.getInt(initialConfig.getKey(), initialConfig.getValue()));
            }
        }


        name = attributeConfig.getString("AttributeName", name);
        description = attributeConfig.getString("AttributeDescription", description);
        isEnabled = attributeConfig.getBoolean("AttributeEnabled", isEnabled);
        attributeWeight = attributeConfig.getInt("AttributeEquipmentWeight", attributeWeight);
        category = AttributeCategory.valueOf(attributeConfig.getString("AttributeEquipmentCategory", category.name()));
    }
}