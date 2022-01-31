package com.example.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * DonutActivity class implements all functionality for the activity_donut xml file.
 * If the user adds a donut to the cart, then that donut will be represented as a Donut object.
 * This donut object will be sent back to MainActivity.java, where they will be added to the
 * current order.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class DonutActivity extends AppCompatActivity
{
    private ListView listView;
    private Spinner spinner;
    private Button button;
    private TextView viewDonutType;
    private TextView displayPrice;

    private Donuts donuts = new Donuts("Donut", 0.00, 0, "");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Order Donut");
        setContentView(R.layout.activity_donut);

        viewDonutType = findViewById(R.id.displaySelectedDonut);
        displayPrice = findViewById(R.id.showPrice);

        viewDonutType.setText("No Flavor Selected");
        //Create ListView for Types of donuts
        listView = (ListView) findViewById(R.id.donutSelector);
        List<String> test = new ArrayList<String>();
        test.add("The Chami Special");
        test.add("Jelly");
        test.add("Chocolate");
        test.add("Boston Creme");
        test.add("Glazed");
        test.add("Powdered");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, test);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            /**
             * This method will obtain the donut type information based on user selection
             *
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                String donutType = test.get(pos);
                viewDonutType.setText(donutType);
                donuts.setType(donutType);
            }
        });

        //Spinner for Quantity
        List<String> test1 = new ArrayList<String>();
        test1.add("0");
        test1.add("1");
        test1.add("2");
        test1.add("3");
        test1.add("4");
        test1.add("5");
        test1.add("6");

        spinner = findViewById(R.id.donutQuantitySelector);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, test1));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            /**
             * This method will listen for changes in listview selection and update the running
             * total accordingly.
             *
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                int quantity  = Integer.parseInt((String) spinner.getSelectedItem());
                donuts.setNumberOfDonuts(quantity);
                donuts.itemPrice();
                displayPrice.setText(donuts.stringItemCost());
            }
            /**
             * Listener to respond when no donut quantity is selected
             *
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * This method will send the donut that the user selected, back to MainActivity.
     *
     * @param view
     */
    public void addDonutToCart(View view)
    {
        if((donuts.getNumberOfDonuts() != 0 && donuts.getType() != ""))
        {
            Toast.makeText(getApplicationContext(), "Donuts added to cart", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("Donut", donuts);
            setResult(Activity.RESULT_OK, intent);

            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Incorrect donut selection", Toast.LENGTH_LONG).show();
        }
    }
}
