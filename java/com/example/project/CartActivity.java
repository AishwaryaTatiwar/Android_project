package com.example.project;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TableLayout cartTable;
    int a;
    List<CartItem> cartItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartpage);

        cartTable = findViewById(R.id.cart_table);

        // Sample data for demonstration
        cartItems = getCartItems();
        // Dynamically populate the table with cart items
        for (CartItem item : cartItems) {
            addRowToTable(item.getName(), String.valueOf(item.getQuantity()), String.valueOf(item.getPrice()* item.getQuantity()));
        }
        TableRow row = new TableRow(this);
        TextView totalTextView=new TextView(this);
        totalTextView.setTextSize(20);
        totalTextView.setText("Total Price");
        row.addView(totalTextView);
        TextView total=new TextView(this);
        float totalprice=calTotal();
        total.setTextSize(20);
        total.setText(String.valueOf(totalprice));
        row.addView(total);
        cartTable.addView(row);
    }


    // Method to add a row to the table
    private void addRowToTable(String itemName, String quantity, String price) {
        TableRow row = new TableRow(this);

        TextView nameTextView = new TextView(this);
        nameTextView.setText(itemName);
        nameTextView.setTextSize(20);
        row.addView(nameTextView);

        TextView quantityTextView = new TextView(this);
        quantityTextView.setText(quantity);
        nameTextView.setTextSize(20);
        row.addView(quantityTextView);

        TextView priceTextView = new TextView(this);
        priceTextView.setText((price));
        nameTextView.setTextSize(20);
        row.addView(priceTextView);

        cartTable.addView(row);
    }

    // Sample method to get cart items (replace with your own data source)
    private List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        Bundle b2 = getIntent().getBundleExtra("Bundle1");
        a = b2.getInt("size");
        for(int i=0;i<a;i++){
            String name=b2.getString("name"+i);
            int qun=b2.getInt("quantity"+i);
            float price=b2.getFloat("price"+i);
            cartItems.add(new CartItem(name,qun,price));
        }
        return cartItems;
    }
    float calTotal(){
        float total= (float) 0;
        for (CartItem item : cartItems) {
            total=total+(item.getPrice()*item.getQuantity());
        }
        return total;
    }
}

