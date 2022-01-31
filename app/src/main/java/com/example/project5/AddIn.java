package com.example.project5;

/**
 * AddIn class is a Enum Class for the available add-ins for coffee.
 * This class is a collection of constant add-ins and their respective prices.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public enum AddIn {

    CREAM(0.20),
    SYRUP(0.20),
    MILK(0.20),
    CARAMEL(0.20),
    WHIPPED_CREAM(0.20);

    private double addInCost;

    /**
     * A constructor which set the price of the constants.
     * All of the addIn currently have the same cost.
     *
     * @param addInCost
     */
    AddIn(double addInCost)
    {
        this.addInCost = addInCost;
    }

    /**
     * A get method to access the price of the add-in.
     *
     * @return double
     */
    public double getAddInCost() {
        return addInCost;
    }

}