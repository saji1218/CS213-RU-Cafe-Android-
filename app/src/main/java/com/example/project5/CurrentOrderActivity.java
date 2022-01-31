package com.example.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CurrentOrderActivity class implements all functionality for the activity_current_order xml file.
 * This class displays the current order in the listview found in the activity_current_order xml file.
 * It also provides functionality for the user to checkout the cart or delete an item from the cart.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class CurrentOrderActivity extends AppCompatActivity {

    AlertDialog.Builder builder;

    private Button checkoutButton;
    private ListView listView;
    private TextView textOrderNum, textPrices;

    private Order order = new Order(MainActivity.orderID);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My Cart");
        setContentView(R.layout.activity_current_order);

        checkoutButton = findViewById(R.id.checkout);
        listView = (ListView) findViewById(R.id.displayCart);
        textOrderNum = findViewById(R.id.viewOrderNum);
        textPrices = findViewById(R.id.viewPrices);

        order = MainActivity.order;
        textOrderNum.setText("Order number: " + String.valueOf(order.getOrderNumber()));
        order.calculateFinalCost();
        textPrices.setText(order.priceString());

        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<MenuItem>(
                this, android.R.layout.simple_list_item_1, order.getOrderedItems());
        listView.setAdapter(arrayAdapter);
        builder = new AlertDialog.Builder(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            /**
             * A listener which will trigger an alert box if the user tries to remove
             * an item from the cart. The user will be able to choose whether or not to proceed based
             * on this alert box.
             *
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                builder.setMessage(R.string.dialog_message_item).setTitle(R.string.dialog_title_item);
                builder.setMessage("Do you want to delete item?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            /**
                             * This method will trigger the alert box. This confirms if the user
                             * wants to remove an item from the cart.
                             *
                             * @param dialog
                             * @param which
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MenuItem item = order.getOrderedItems().get(pos);
                                order.remove(item);
                                order.calculateFinalCost();
                                textPrices.setText(order.priceString());
                                listView.setAdapter(arrayAdapter);
                                Toast.makeText(getApplicationContext(),"Removed Item", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            /**
                             * If the user decides not to remove the item from the cart, based on
                             * the alert box's options, then the alert box will disappear
                             * @param dialog
                             * @param which
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    /**
     * This method will add the current order to the static store order object.
     * There will be a new order object created after this, with a new order number.
     *
     * @param view
     */
    public void addOrderToStoreOrders(View view) //trigger this when the button is clicked, create a coffee object
    {
        if(!order.getOrderedItems().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Order added to Store Orders", Toast.LENGTH_SHORT).show();

            MainActivity.storeOrders.add(order);
            MainActivity.orderID += 1;
            MainActivity.order = new Order(MainActivity.orderID);

            //launch store orders page and display MainActivity.storeOrders
            Intent intent = new Intent(this, StoreOrderActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Error: Empty Order", Toast.LENGTH_SHORT).show();
        }
    }
}



