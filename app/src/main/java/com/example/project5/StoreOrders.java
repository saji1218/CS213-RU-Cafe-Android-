package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * StoreOrders class defines the properties of the StoreOrders Object.
 * The StoreOrders object is a list of Order objects
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class StoreOrders implements Customizable, Serializable
{
    private ArrayList<Order> orderList;

    /**
     * Default constructor to create an empty store order
     */
    public StoreOrders()
    {
        ArrayList<Order> storeOrders = new ArrayList<>();
        this.orderList = storeOrders;
    }

    /**
     * A get method to access orderList
     *
     * @return an ArrayList<Order> representation of store orders
     */
    public ArrayList<Order> getStoreOrderList() {
        return orderList;
    }

    /**
     * Adds a Order into the orderList ArrayList.
     *
     * @param obj the Order that being added
     * @return true if the Order object is added successfully, false otherwise
     */
    public boolean add(Object obj)
    {
        if(obj instanceof Order)
        {
            orderList.add((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * Removes a Order from the orderList ArrayList.
     *
     * @param obj the Order that being removed
     * @return true if the Order object is removed successfully, false otherwise
     */
    public boolean remove(Object obj)
    {
        if(obj instanceof Order)
        {
            orderList.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * A toString method that outputs the string representation of an StoreOrders object.
     *
     * @return String output represents the String representation of the StoreOrders object
     */
    @Override
    public String toString()
    {
        String orderOutput = "";
        for (Order order : orderList)
        {
            orderOutput = orderOutput + order.toString();
        }
        return orderOutput;
    }
}
