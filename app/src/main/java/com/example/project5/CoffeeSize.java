package com.example.project5;

/**
 * CoffeeSize class is a Enum Class for the sizes of coffee.
 * This class is a collection of constant coffee sizes and their respective prices.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public enum CoffeeSize {

    SHORT(1.99),
    TALL(2.49),
    GRANDE(2.99),
    VENTI(3.49);

    private double price;

    /**
     * A constructor which set the price of the constants.
     *
     * @param price double value
     */
    CoffeeSize(double price) {
        this.price = price;
    }

    /**
     * A get method to access the price relative to the size.
     *
     * @return double price of the size
     */
    public double getPrice() {
        return price;
    }

}
