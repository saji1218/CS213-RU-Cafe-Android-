package com.example.project5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CoffeeActivity class implements all functionality for the activity_coffee xml file.
 * If the user adds a coffee to the cart, then that coffee will be represented as a Coffee object.
 * This coffee object will be sent back to MainActivity.java, where they will be added to the
 * current order.
 *
 * @author Arun Joseph (aj602), Masami Tajima (mst111)
 */
public class CoffeeActivity extends AppCompatActivity
{
    private Spinner mySpinner;
    private CheckBox milk, syrup, cream, caramel, whippedCream;
    private TextView coffeeCost;

    //Defult coffee order, size SHORT and no add-ins
    private Coffee coffee = new Coffee("Coffee", CoffeeSize.SHORT.getPrice(), CoffeeSize.SHORT);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Order Coffee");
        setContentView(R.layout.activity_coffee);

        mySpinner = findViewById(R.id.sizeSelector);
        mySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CoffeeSize.values()));

        coffeeCost = findViewById(R.id.orderTotal);
        coffeeCost.setText("Item Cost: " + coffee.stringItemCost());

        // Listener and handler for the spinner which holds the options for the Coffee size
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            /**
             * Listener to detect when a coffee size is selected.
             *
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                CoffeeSize size  = (CoffeeSize) mySpinner.getSelectedItem();
                coffee.setSize(size);
                coffee.itemPrice();
                coffeeCost.setText("Item Cost: " + coffee.stringItemCost());
            }

            /**
             * Listener to respond when no coffee size is selected
             *
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Handler for the AddToCart Button.
     * When triggered, it will send the Coffee Object with the current size and add-ins.
     *
     * @param view
     */
    public void addCoffeeToCart(View view) //trigger this when the button is clicked, create a coffee object
    {
        Toast.makeText(getApplicationContext(), "Coffee added to cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("Coffee", coffee);
        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    /**
     *  Listener and handler for the Add-Ins CheckBox
     *
     * @param view
     */
    public void onCheckBoxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId())
        {
            case R.id.caramelCheckBox:
                if(checked)
                {
                    coffee.add(AddIn.CARAMEL);
                }
                else
                {
                    coffee.remove(AddIn.CARAMEL);
                }

                coffee.itemPrice();
                coffeeCost.setText("Item Cost: " + coffee.stringItemCost());
                break;

            case R.id.milkCheckBox:
                if(checked)
                {
                    coffee.add(AddIn.MILK);
                }
                else
                {
                    coffee.remove(AddIn.MILK);
                }

                coffee.itemPrice();
                coffeeCost.setText("Item Cost: " + coffee.stringItemCost());
                break;

            case R.id.creamCheckBox:
                if(checked)
                {
                    coffee.add(AddIn.CREAM);
                }
                else
                {
                    coffee.remove(AddIn.CREAM);
                }

                coffee.itemPrice();
                coffeeCost.setText("Item Cost: " + coffee.stringItemCost());
                break;

            case R.id.syrupCheckBox:
                if(checked)
                {
                    coffee.add(AddIn.SYRUP);
                }
                else
                {
                    coffee.remove(AddIn.SYRUP);
                }

                coffee.itemPrice();
                coffeeCost.setText("Item Cost: " + coffee.stringItemCost());
                break;

            case R.id.whippedCreamCheckBox:
                if(checked)
                {
                    coffee.add(AddIn.WHIPPED_CREAM);
                }
                else
                {
                    coffee.remove(AddIn.WHIPPED_CREAM);
                }

                coffee.itemPrice();
                coffeeCost.setText("Item Cost: " + coffee.stringItemCost());
                break;
        }
    }
}
