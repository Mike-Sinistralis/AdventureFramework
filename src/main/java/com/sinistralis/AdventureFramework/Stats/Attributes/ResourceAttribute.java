package com.sinistralis.AdventureFramework.Stats.Attributes;


public class ResourceAttribute extends Attribute
{
    double totalRegen;
    double currentAmount;

    float baseRegen;
    float flatRegen;

    ResourceAttribute(float baseVal, float baseReg)
    {
        currentAmount = baseVal;
        baseRegen = baseReg;
        flatRegen = 0;
        totalRegen = baseReg;
    }

    private void deplete(int amount)
    {
        double result = currentAmount - amount;

    }

    public void restore(int amount)
    {

    }

    //TODO Add Ability Arguement, this should call deplete if true
    public boolean canUse()
    {
        return false;
    }







}
