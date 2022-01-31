package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Coffee class is defines the Property of the Coffee Object.
 * Coffee is a subclass of the MenuItem Class.
 * The class contain two fields, size of the coffee and the add-ins.
 * It also contain the itemPrice(), which calculate the total price of the coffee with size and add-ins.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class Coffee extends MenuItem implements Customizable, Serializable
{
    private CoffeeSize size;
    private ArrayList<AddIn> addIns;

    /**
     * Parameterized Constructor to create a Coffee object.
     *
     * @param itemName String inherited from MenuItem class
     * @param itemCost double inherited from MenuItem class
     * @param size CoffeeSize represents the size of the Coffee Object
     */
    public Coffee(String itemName, double itemCost, CoffeeSize size)
    {
        super(itemName, itemCost);
        this.size = size;
        ArrayList<AddIn> addInList = new ArrayList<>();
        this.addIns = addInList;
    }

    /**
     * Set method that set the size of the Coffee object
     *
     * @param size CoffeeSize
     */
    public void setSize(CoffeeSize size)
    {
        this.size = size;
    }

    /**
     * A helper method that add the total cost of the Add-ins
     *
     * @return double represents the total cost of the add-ins
     */
    private double addInSum()
    {
        double sum = 0;
        for (AddIn addIn : addIns)
        {
            sum += addIn.getAddInCost();
        }
        return sum;
    }

    /**
     * A toString method that output the string representation from the MenuItem class and the size and add-ins.
     *
     * @return String output represents the String representation of the Coffee object
     */
    @Override
    public String toString()
    {
        return super.toString() + " Quantity: 1" + "\n" + "Size: " + size + ", Add-ins: " + addIns.toString();
    }

    /**
     * Determines if two Coffee are identical by comparing their size and add-ins.
     *
     * @param obj represent the object that will compared with to determine equality
     * @return true if obj is a Coffee object and have identical field values, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Coffee)
        {
            Coffee coffee = (Coffee) obj;
            return super.equals(obj) && coffee.size == this.size && coffee.addIns.equals(this.addIns);
        }
        return false;
    }

    /**
     * Calculates and sets the price of coffee. This also rounds the the item price to two decimal places
     */
    @Override
    public void itemPrice()
    {
        double total = size.getPrice() + addInSum();
        double t = (double) Math.round(total * 100) / 100;
        this.setItemCost(t);
    }

    /**
     * Adds an add-in in the Add-In ArrayList.
     *
     * @param obj the add-in that being added
     * @return true if the add-in object is added successfully, false otherwise
     */
    @Override
    public boolean add(Object obj)
    {
        if(obj instanceof AddIn)
        {
            addIns.add((AddIn) obj);
            return true;
        }
        return false;
    }

    /**
     * Removes an add-in in the Add-In ArrayList.
     *
     * @param obj the add-in that being removed
     * @return true if the add-in object is removed successfully, false otherwise
     */
    @Override
    public boolean remove(Object obj)
    {
        if(obj instanceof AddIn)
        {
            addIns.remove(obj);
            return true;
        }
        return false;
    }

}
