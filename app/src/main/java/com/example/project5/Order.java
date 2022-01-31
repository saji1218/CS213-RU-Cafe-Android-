package com.example.project5;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Order class defines the properties of the Order object.
 * The class contains fields for order number, a list of ordered items, the sub-total, tax,
 * and the total cost of all ordered items.
 * The class also contains the calculateFinalTotal() which calculates the price of an order, given the ordered items.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class Order implements Customizable, Serializable
{

    private int orderNumber;
    private ArrayList<MenuItem> orderedItems;
    private double subTotal;
    private double tax;
    private double finalCost;

    private static final double NJ_TAX = 0.06625;
    private static final String DECIMAL_FORMAT_STR = "#,##0.00";

    /**
     * Parameterized constructor to create an Order object
     *
     * @param orderNumber identifier for user's order
     */
    public Order(int orderNumber)
    {
        this.orderNumber = orderNumber;
        ArrayList<MenuItem> orderItems = new ArrayList<>();
        this.orderedItems = orderItems;
        this.subTotal = 0;
        this.tax = 0;
        this.finalCost = 0;
    }

    /**
     * A get method to access the MenuItems of an Order
     *
     * @return ObservableList of MenuItems
     */
    public ArrayList<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    /**
     * A get method to get the Order number of an Order object
     *
     * @return int OrderNUmber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Adds a MenuItem into the orderedItems ObservableList.
     *
     * @param obj the MenuItem that being added
     * @return true if the MenuItem object is added successfully, false otherwise
     */
    public boolean add(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            orderedItems.add((MenuItem) obj);
            return true;
        }
        return false;
    }

    /**
     * Removes a MenuItem from the orderedItems ObservableList.
     *
     * @param obj the MenuItem that being removed
     * @return true if the MenuItem object is removed successfully, false otherwise
     */
    public boolean remove(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            orderedItems.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Helper Method that calculates and sets the price of the order in calculateFinalCost()
     */
    private void calculateSubTotal()
    {
        double sum = 0;
        for (MenuItem menuItem : this.orderedItems)
        {
            sum += menuItem.getItemCost();
        }
        this.subTotal = sum;
    }

    /**
     * Helper Method that calculates and set the tax of the order in calculateFinalCost()
     */
    private void calculateTax()
    {
        calculateSubTotal();
        this.tax = this.subTotal * NJ_TAX;
    }

    /**
     *  Method that calculates and set the final cost of the order,
     *  this method also calculates and set the subtotal and taxes.
     */
    public void calculateFinalCost()
    {
        calculateSubTotal();
        calculateTax();

        this.finalCost = this.subTotal + this.tax;
    }

    /**
     * A method that display the sub-total, tax and the total cost of the Order object
     *
     * @return String representation
     */
    public String priceString(){
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_STR);
        return "Sub-Total: $" + df.format(this.subTotal) + "\n" +
                "Tax: $" + df.format(this.tax) + "\n" +
                "Total: $" + df.format(this.finalCost) + "\n";
    }

    /**
     * A toString method that outputs the string representation of an Order object.
     *
     * @return String output represents the String representation of the Order object
     */
    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_STR);
        String orderOutput = "";
        for (MenuItem menuItem : orderedItems)
        {
            orderOutput = orderOutput + menuItem.toString() + "\n";
        }
        return "Order #" + this.orderNumber + "\n" +
                "Sub-Total: $" + df.format(this.subTotal) + "\n" +
                "Tax: $" + df.format(this.tax) + "\n" +
                "Total: $" + df.format(this.finalCost) + "\n" +
                orderOutput + "\n";
    }
}
