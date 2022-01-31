package com.example.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * StoreOrderActivity class implements all functionality for the activity_store_order xml file.
 * This class displays all previous orders in the listview found in the activity_store_order xml file.
 * It also provides functionality for the user to delete an item from the store order.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class StoreOrderActivity extends AppCompatActivity
{
    private ListView listView;
    AlertDialog.Builder builder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Store order");
        setContentView(R.layout.activity_store_order);

        listView = (ListView) findViewById(R.id.storeOrderList);
        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<Order>(
                this, android.R.layout.simple_list_item_1, MainActivity.storeOrders.getStoreOrderList());
        listView.setAdapter(arrayAdapter);
        builder = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            /**
             * A listener which will trigger an alert box if the user tries to remove
             * an item from store order. The user will be able to choose whether or not to proceed based
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
                             * wants to remove an item from the store order.
                             *
                             * @param dialog
                             * @param which
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                MenuItem item = order.getOrderedItems().get(pos);
//                                order.remove(item);
//                                order.calculateFinalCost();
//                                textPrices.setText(order.priceString());
                                Order order = MainActivity.storeOrders.getStoreOrderList().get(pos);
                                MainActivity.storeOrders.remove(order);
                                listView.setAdapter(arrayAdapter);
                                Toast.makeText(getApplicationContext(),"Removed Item", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            /**
                             * If the user decides not to remove the item from the store order,
                             * based on the alert box's options, then the alert box will disappear
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

}
