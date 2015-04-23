package com.sinistralis.AdventureFramework.Stats.Attributes;

public class Attribute
{
    protected double totalValue;

    private float baseValue;
    private int flatModifier = 0;
    private float multiplicativeModifier = 0;

    Attribute(float baseVal)
    {
        baseValue = baseVal;
    }

    private double calculateTotalValue()
    {
        double result = Math.floor((baseValue + flatModifier) * (1 + multiplicativeModifier));
        return result >= 0 ? result : 0;
    }

    public void alterFlat(int flatAmount)
    {
        flatModifier += flatAmount;
        totalValue = calculateTotalValue();
    }

    public void alterMult(float multiplicativeAmount)
    {
        multiplicativeModifier += multiplicativeAmount;
        totalValue = calculateTotalValue();
    }

    public double getCurrentValue()
    {
        return totalValue;
    }
}
