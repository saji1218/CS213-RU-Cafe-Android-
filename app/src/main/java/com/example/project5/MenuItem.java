package com.example.project5;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * MenuItem class defines the properties of a MenuItem object.
 * The class contains the menu item's type and cost(price).
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class MenuItem implements Serializable
{
    private String itemType; // Coffee or Donut
    private double itemCost; // The total price or cost of the MenuItem with the add-ins(Coffee) or quantity(Donut)

    private static final String DECIMAL_FORMAT_STR = "#,##0.00";
    private DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_STR);
    /**
     * Parameterized Constructor to create an MenuItem object
     *
     * @param itemType String type which defines the type/name of the MenuItem
     * @param itemCost double type which represents the cost/price of the MenuItem
     */
    public MenuItem(String itemType, double itemCost)
    {
        this.itemType = itemType;
        this.itemCost = itemCost;
    }

    /**
     * A get method to access a MenuItem's cost/price also rounds up the price
     *
     * @return double which represent the cost/price of the MenuItem
     */
    public double getItemCost()
    {
        return itemCost;
    }

    /**
     * A set method to define the MenuItem's cost(price)
     *
     * @param itemCost double which represents the cost of the MenuItem
     */
    public void setItemCost(double itemCost)
    {
        this.itemCost = itemCost;
    }

    /**
     * A helper method that turns itemCost to String with $
     * @return
     */
    public String stringItemCost()
    {
        return "$" + df.format(itemCost);
    }

    /**
     * Output the MenuItem's type and cost(price)
     *
     * @return String representation of the MenuItem Object
     */
    @Override
    public String toString()
    {
        return itemType + ": $" + df.format(itemCost);
    }

    /**
     * Determines if two MenuItems are identical by comparing their type and price.
     *
     * @param obj represent the object that will compared with to determine equality
     * @return true if obj is a MenuItem object and have identical field values, otherwise false
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            MenuItem menuItem = (MenuItem) obj;
            return menuItem.itemType.equals(this.itemType) && menuItem.itemCost == this.itemCost;
        }
        return false;
    }

    /**
     * Placeholder method for subclass itemPrice methods.
     */
    public void itemPrice()
    {
    }

}
