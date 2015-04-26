package com.sinistralis.AdventureFramework.Stats.Attributes;


public class ResourceAttribute extends Attribute
{
    float totalRegen;
    int currentAmount;

    float baseRegen;
    float flatRegen;

    ResourceAttribute()
    {

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
