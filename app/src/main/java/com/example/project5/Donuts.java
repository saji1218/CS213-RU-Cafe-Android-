package com.example.project5;

import java.io.Serializable;

/**
 * Donuts class defines the property of the Donuts object
 * The class contain a field for the number of donuts in one order
 * It also contain the itemPrice(), which calculate the total price of the donut order.
 *
 *  @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class Donuts extends MenuItem implements Serializable
{
    private int numberOfDonuts;
    private String type;
    private static final double DONUT_PRICE = 1.39;

    /**
     * Parameterized constructor to create a Donuts object
     *
     * @param itemName String inherited from MenuItem class
     * @param itemCost double inherited from MenuItem class
     * @param numberOfDonuts int to represent the number of donuts that the user ordered
     */
    public Donuts(String itemName, double itemCost, int numberOfDonuts, String type)
    {
        super(itemName, itemCost);
        this.numberOfDonuts = numberOfDonuts;
        this.type = type;
    }

    /**
     * Get method to access the Type of the donut
     *
     * @return String type of donut
     */
    public String getType() {
        return type;
    }

    /**
     * Set method to assign the Type of Donuts
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set method to assign the quantity of donuts
     *
     * @param numberOfDonuts the quantity of donuts
     */
    public void setNumberOfDonuts(int numberOfDonuts) {
        this.numberOfDonuts = numberOfDonuts;
    }

    /**
     * A get method to return the number of donuts that the user ordered
     *
     * @return int to represent the number of donuts that the user ordered
     */
    public int getNumberOfDonuts()
    {
        return numberOfDonuts;
    }

    /**
     * toString method to return a string representation of a Donuts object
     *
     * @return String representation of a Donuts object
     */
    @Override
    public String toString()
    {
        return(super.toString() + " Type: " + type + " Quantity: " + numberOfDonuts);
    }

    /**
     * Calculates and sets the price of a Donuts object
     */
    @Override
    public void itemPrice()
    {
        this.setItemCost(numberOfDonuts * DONUT_PRICE);
    }
}
