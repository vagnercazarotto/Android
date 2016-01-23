package com.tkcode.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        double price = calculatePrice();
        if (quantity == 0) {
            //display_value(0);
            displayMessage("");
        } else {
            //display_value(cal);
            displayMessage(createOrderSummary(price));
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private double calculatePrice(){
        double price = quantity * 3.75;
        return price;
    }


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void display_value(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }

    private void displayMessage(String string) {
        TextView messageTextView = (TextView) findViewById(R.id.message_text_view);
        messageTextView.setText(string);
    }

    ///////////////// OK,now i'll create function for increment and decrement
    public void incrementButton(View view) {
        quantity = quantity + 1;
        display(quantity);
        display_value(calculatePrice());
    }

    public void decrementButton(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            display_value(quantity);
        }
    }

    private String createOrderSummary(Double price) {
        String priceMessage = "Name: Kaptain Knual";
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage +  "\nTotal: $" + price;
        priceMessage = priceMessage + "\nTank You!";
        return priceMessage;
    }

}