package com.sinistralis.AdventureFramework.Stats.Attributes;


public class ResourceAttribute extends Attribute
{
    float totalRegen;
    float currentAmount;

    float baseRegen;
    float flatRegen;

    public ResourceAttribute()
    {

    }

    private void deplete(int amount)
    {
        currentAmount -= amount;
    }

    public void alterCurrentAmount(int amount)
    {
        currentAmount = (float) Math.floor(currentAmount + amount);
    }

    public void calculateTotalRegen()
    {
        totalRegen = baseRegen + flatRegen;
    }

    public void alterBaseRegen(float amount)
    {
        baseRegen += amount;
    }

    public void alterFlatRegen(float amount)
    {
        flatRegen += amount;
    }

    public float getTotalRegen()
    {
        return totalRegen;
    }

    //TODO Add Ability Arguement, this should call deplete if true
    public boolean canUse()
    {
        return false;
    }







}
