package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickOrder extends AppCompatActivity {
    Button addOnion,addCarrot,addCoriander;
    ImageButton cartBtn;
    ArrayList<String> object=new ArrayList<>();
     int[] quantity=new int[50];
     float[] price=new float[50];
     int size;
     int objno=0;
     String username;
    //String username = ((Variable) this.getApplication()).getSomeVariable();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://signindetails-df952-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference().child("users").child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickorder);
        addCoriander=(Button) findViewById(R.id.coriander);
        addOnion=(Button) findViewById(R.id.onion);
        addCarrot=(Button) findViewById(R.id.carrot);
        username = ((Variable) getApplication()).getSomeVariable();
        addCoriander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PickOrder.this, "100g coriander added to cart", Toast.LENGTH_SHORT).show();
                update("Coriander",(float)10.3);
            }
        });
        addCarrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PickOrder.this, "1kg carrot added to cart", Toast.LENGTH_SHORT).show();
                update("carrot",(float)50);
            }
        });
        addOnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PickOrder.this, "1kg Onion added to cart", Toast.LENGTH_SHORT).show();
               update("Onion",(float)51.3);
            }
        });
        cartBtn=(ImageButton) findViewById(R.id.cart);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PickOrder.this,username+"'s "+"Cart",Toast.LENGTH_SHORT).show();
                Toast.makeText(PickOrder.this,object.toString(),Toast.LENGTH_SHORT).show();
                addData(objno,object,quantity,price);
                makeIntent();
            }
        });
    }
    void update(String obj,float cost){
        if(object.contains(obj)){
            quantity[object.indexOf(obj)]+=1;
        }
        else {
            objno+=1;
            object.add(obj);
            quantity[object.indexOf(obj)] = 1;
            price[object.indexOf(obj)]=cost;
        }
    }
    public void addData(int noOfobj,ArrayList<String> object,int[] qun,float[] price){
        for(int i=0;i<objno;i++) {
            CartItem newitem= new CartItem(object.get(i),qun[i],price[i]);
            myRef.child(username).child("cartitem").push().setValue(newitem);
        }

   }
   public void makeIntent(){
       List<CartItem> cartItems = new ArrayList<>();
       Intent I1=new Intent(PickOrder.this,CartActivity.class);
       Bundle bundle=new Bundle();
       for(int i=0;i<objno;i++) {
           bundle.putString("name"+i,object.get(i));
           bundle.putInt("quantity"+i,quantity[i]);
           bundle.putFloat("price"+i,price[i]);
       }
       bundle.putInt("size",objno);
       I1.putExtra("Bundle1",bundle);
       startActivity(I1);
   }
}
