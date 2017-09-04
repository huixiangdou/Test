package com.test.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.test.Interface.GetDataInterface;
import com.test.R;

import java.util.ArrayList;

public class BasicActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,GetDataInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onSucess(ArrayList arrayList) {

    }
}
