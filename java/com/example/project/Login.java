package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    DatabaseReference mDatabase;
    String username,pass;
    EditText username1,password;
    Button login;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://signindetails-df952-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference().child("users").child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username1=findViewById(R.id.username1);
        password=findViewById(R.id.password1);

        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=username1.getText().toString();
                myRef.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()){
                                Toast.makeText(Login.this,"user account found",Toast.LENGTH_SHORT).show();
                                DataSnapshot data= task.getResult();
                                String newuser= data.child("username").getValue(String.class);
                                pass = data.child("password").getValue(String.class);
                                if(checkPassword(pass)){
                                    Toast.makeText(Login.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                                    ((Variable) getApplication()).setSomeVariable(newuser);
                                    Toast.makeText(Login.this,"Welcome"+((Variable)getApplication()).getSomeVariable(),Toast.LENGTH_SHORT).show();

                                }
                            }
                            else{
                                Toast.makeText(Login.this,"You don't have an account please sign in",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Login.this,SignIn.class);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(Login.this, "Failed to read", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private boolean checkPassword(String Realpassword){
        String input=password.getText().toString().trim();
        if(input.equals(Realpassword)){
            Toast.makeText(Login.this,"Correct password",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(Login.this,"Incorrect password"+input+"!="+Realpassword,Toast.LENGTH_SHORT).show();

            return false;
        }
    }
}