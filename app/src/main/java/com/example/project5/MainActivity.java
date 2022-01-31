package com.example.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity class implements all functionality for the activity_main xml file.
 * This class contains the functionality for four buttons which will launch activities to
 * order donuts, order coffee, see current order, and see store orders.
 * This class will also update current order according to what items the user adds to the cart.
 * This current order can be added to store order as well.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class MainActivity extends AppCompatActivity {

    private static final int START_OF_ORDER_ID = 10000;
    public static int orderID = START_OF_ORDER_ID;
    public static Order order = new Order(orderID); //Creates the very first order
    public static StoreOrders storeOrders = new StoreOrders();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.coffee1);
        setTitle("Chami Cafe");
    }

    /**
     * Method to launch CoffeeActivity,
     * which also overrides onActivityResult to get Coffee data
     *
     * @param view
     */
    public void goToCoffeePage(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivityForResult(intent, 1);
        onActivityResult(1, RESULT_OK, intent);

    }

    /**
     * Method to launch DonutActivity,
     * which also overrides onActivityResult to get Donut data
     *
     * @param view
     */
    public void goToDonutPage(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivityForResult(intent, 1);
        onActivityResult(1, RESULT_OK, intent);
    }

    /**
     * Method to launch StoreOrderActivity,
     *
     * @param view
     */
    public void goToStoreOrderPage(View view) {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Method to launch CurrentOrderActivity
     *
     * @param view
     */
    public void goToCartPage(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Method to handle the received input from startActivityForResult(). This method will identify
     * whether the received object is a Coffee object or a Donut object and
     * add it to the current order.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Coffee coffee = (Coffee) data.getSerializableExtra("Coffee");
                Donuts donut = (Donuts) data.getSerializableExtra("Donut");
                if (coffee != null) {
                    order.add(coffee);
                }
                if (donut != null) {
                    order.add(donut);
                }
            }
        }
    }
}

