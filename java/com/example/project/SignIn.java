package com.example.project;

import android.app.Application;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn  extends AppCompatActivity {
    Button submit;
    TextView alert;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://signindetails-df952-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference().child("users").child("users");
    EditText name,number,email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        submit=(Button) findViewById(R.id.signin);
        alert=findViewById(R.id.alert);
        name=findViewById(R.id.username);
        number=findViewById(R.id.number);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0){
                    Toast.makeText(SignIn.this,"Enter valid email",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateUser(name);
                }
            }});
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUser(name)){
                    Toast.makeText(SignIn.this, "username accepted", Toast.LENGTH_SHORT).show();
                    if(validateEmail(email)){
                        Toast.makeText(SignIn.this, " email accepted", Toast.LENGTH_SHORT).show();
                        if(validateNumber(number)) {
                            Toast.makeText(SignIn.this, "number accepted", Toast.LENGTH_SHORT).show();
                            if (validatePassword(pass)) {
                                Toast.makeText(SignIn.this, "password accepted", Toast.LENGTH_SHORT).show();
                                String name1 = name.getText().toString();
                                String email1 = email.getText().toString();
                                String num1 = number.getText().toString();
                                String password = pass.getText().toString();
                                ((Variable) getApplication()).setSomeVariable(name1);
                                Toast.makeText(SignIn.this, "Name: " + name1 + "email: " + email1 + "number :" + num1, Toast.LENGTH_SHORT).show();
                                addData(name1, num1, email1, password);
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(SignIn.this,"Enter valid username ",Toast.LENGTH_SHORT).show();
                }
                //if(validateEmail(email) && validateNumber(number) && validateUser(name) ){

                //}
            }
        });
    }
    private boolean validateEmail(EditText e2){
        String mail=e2.getText().toString();
        if (!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            Toast.makeText(SignIn.this,"Email accepted",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(SignIn.this,"Enter valid email ",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private boolean validateNumber(EditText e) {
        String input = e.getText().toString().trim(); // Trim any leading or trailing spaces
        if (input.isEmpty()) {
            Toast.makeText(SignIn.this, "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (input.length() != 10) {
            Toast.makeText(SignIn.this, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                Toast.makeText(SignIn.this, "Phone number can only contain digits", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    private boolean validateUser(EditText e){
        String n=e.getText().toString();
        boolean flag=true;
        if(n.length()==0){
            Toast.makeText(SignIn.this,"Enter valid username ",Toast.LENGTH_SHORT).show();
            return false;
        }
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(n)) {
                    Toast.makeText(SignIn.this,"Username alread exists take other username",Toast.LENGTH_SHORT).show();
                    alert.setText("Username alread exists take other username");
                }
                else{
                    alert.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return flag;
    }
    private boolean validatePassword(EditText e){
       String password=e.getText().toString();
       if(password.length()==0){
           Toast.makeText(SignIn.this,"Enter valid password",Toast.LENGTH_SHORT).show();
           return false;
       }
       return true;
    }
    public void addData(String username,String num,String email,String password){
        Users user = new Users(username,num,email,password);
        myRef.child(username).setValue(user);
    }

}

