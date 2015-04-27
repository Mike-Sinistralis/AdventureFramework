package com.sinistralis.AdventureFramework.Stats.Attributes;

import com.sinistralis.AdventureFramework.Core.Enums.AttributeCategory;
import com.sinistralis.AdventureFramework.Core.IConfigurable;

import java.util.HashMap;
import javax.json.*;


public class Attribute implements IConfigurable {

    private String name = "Attribute";
    private String description = "Need Description";
    private boolean isEnabled = true;
    private int attributeWeight = 0;
    private AttributeCategory category = AttributeCategory.PRIMARY;

    private int baseValue = 0;
    private int flatModifier = 0;
    private float multiplicativeModifier = 1;

    private int currentTotalValue = 0;

    private int calculateTotalValue()
    {
        int result = (int) Math.floor((baseValue + flatModifier) * (multiplicativeModifier));
        return result >= 0 ? result : 0;
    }

    public static Attribute cloneStructure(Attribute toClone)
    {
        Attribute clone = new Attribute();
        clone.setName(toClone.name).setDescription(toClone.description).setCategory(toClone.category).setAttributeWeight(toClone.attributeWeight).setEnabled(toClone.isEnabled);
        return clone;
    }

    public String getName() {
        return name;
    }

    public Attribute setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Attribute setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Attribute setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public int getAttributeWeight() {
        return attributeWeight;
    }

    public Attribute setAttributeWeight(int attributeWeight) {
        this.attributeWeight = attributeWeight;
        return this;
    }

    public AttributeCategory getCategory() {
        return category;
    }

    public Attribute setCategory(AttributeCategory category) {
        this.category = category;
        return this;
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

    public double getCurrentTotalValue() {
        return currentTotalValue;
    }

    public JsonObjectBuilder writeConfigJson()
    {
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(new HashMap<String, Object>());

        JsonObjectBuilder attributeConfig = jsonFactory.createObjectBuilder();

        attributeConfig
            .add("AttributeName", name)
            .add("AttributeDescription", description)
            .add("AttributeEnabled", isEnabled)
            .add("AttributeEquipmentWeight", attributeWeight)
            .add("AttributeEquipmentCategory", category.name());

        return attributeConfig;
    }

    public void loadConfigJson(JsonObject attributeConfig)
    {
        name = attributeConfig.getString("AttributeName", name);
        description = attributeConfig.getString("AttributeDescription", description);
        isEnabled = attributeConfig.getBoolean("AttributeEnabled", isEnabled);
        attributeWeight = attributeConfig.getInt("AttributeEquipmentWeight", attributeWeight);
        category = AttributeCategory.valueOf(attributeConfig.getString("AttributeEquipmentCategory", category.name()));
    }
}