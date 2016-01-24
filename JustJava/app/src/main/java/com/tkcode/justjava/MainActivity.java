package com.tkcode.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    private int quantity = 0;
    private boolean clickBoxA = false;
    private boolean clickBoxB = false;
    private boolean clickBoxC = false;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox clickButtonA = (CheckBox) findViewById(R.id.clickBoxA);
        clickBoxA = clickButtonA.isChecked();
        CheckBox clickButtonB = (CheckBox) findViewById(R.id.clickBoxB);
        clickBoxB = clickButtonB.isChecked();
        CheckBox clickButtonC = (CheckBox) findViewById(R.id.clickBoxC);
        clickBoxC = clickButtonC.isChecked();
        Log.v("MainActivity", "Has whipped cream " + clickBoxA);
        double price = calculatePrice();
        if (quantity == 0) {
            //display_value(0);
            displayMessage("");
        } else {
            //display_value(cal);
            displayMessage(createOrderSummary(price,clickBoxA,clickBoxB,clickBoxC));
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private double calculatePrice(){
        double tempPrice = 0;
        CheckBox clickButtonA = (CheckBox) findViewById(R.id.clickBoxA);
        clickBoxA = clickButtonA.isChecked();
        CheckBox clickButtonB = (CheckBox) findViewById(R.id.clickBoxB);
        clickBoxB = clickButtonB.isChecked();
        CheckBox clickButtonC = (CheckBox) findViewById(R.id.clickBoxC);
        clickBoxC = clickButtonC.isChecked();
        if(clickBoxA == true){
                tempPrice = tempPrice + 3.75;
        }
        if(clickBoxB == true){
                tempPrice = tempPrice + 3.75;
        }
        if(clickBoxC == true){
                tempPrice = tempPrice + 3.75;
        }

        double price = quantity * tempPrice;
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

    private String getDisplayMessage() {
        TextView messageTextView = (TextView) findViewById(R.id.message_text_view);
        String stringlocal =(String) messageTextView.getText();
        return stringlocal;
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
            display(quantity);
            display_value(calculatePrice());
        }
    }

    private String readName(){
        EditText text = (EditText) findViewById(R.id.nameField);
        String name = text.getText().toString();
        return name;
    }

    private String createOrderSummary(Double price,Boolean A,boolean B,boolean C) {
        String priceMessage = "Name: " + readName();
        if(A || B || C == true){
            priceMessage = priceMessage + "\nItem List: ";
        }
        if(A == true){
            priceMessage = priceMessage + "\nWhipped Cream";
        }
        if(B == true){
            priceMessage = priceMessage + "\nChocolate";
        }
        if(C == true){
            priceMessage = priceMessage + "\nBlack Coffee";
        }
        if(A || B || C == true){
            priceMessage = priceMessage + "\nQuantity: " + quantity;
            priceMessage = priceMessage + "\nTotal: $" + price;
            priceMessage = priceMessage + "\nTank You!";
        }
        return priceMessage;
    }

    public void orderMail(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        name = readName();
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,getDisplayMessage());
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }


}