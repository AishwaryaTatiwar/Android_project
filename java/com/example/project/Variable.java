package com.example.project;

import android.app.Application;

public class Variable extends Application {
    public String userglobal="logedout";

    public String getSomeVariable() {
        return userglobal;
    }

    public void setSomeVariable(String someVariable) {
        this.userglobal = someVariable;
    }
}
