package com.example.project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button signin,login,pickorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin=findViewById(R.id.signin);
        login=findViewById(R.id.login);
        pickorder=findViewById(R.id.pickorder);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,SignIn.class);
                startActivity(i1);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,Login.class);
                startActivity(i2);
            }
        });
        pickorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i3=new Intent(MainActivity.this,PickOrder.class);
                startActivity(i3);
            }
        });
    }

}