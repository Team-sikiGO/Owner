package com.example.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderList extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    private Toolbar toolbar;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);

        // toolbar setting
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.page_home);

        // qrcode info
        Intent intent = getIntent();
        String menu = intent.getStringExtra("orderList");

        String printMenu = "";
        String[] arr = menu.split("/");
        int size = arr.length;
        for (int i = 0; i < size - 1; i = i + 2) {
            printMenu = printMenu.concat(arr[i]);
            printMenu = printMenu.concat("\t\t\t· · · · ·\t\t\t");
            printMenu = printMenu.concat(" X");
            printMenu = printMenu.concat(arr[i+1]);
            if(i != (size - 3)) {
                printMenu = printMenu.concat("\n");
            }
        }

        txt = (TextView) findViewById(R.id.orderList);
        txt.setText(printMenu);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_exit);
                        finish();
                        return true;
                    case R.id.page_order:
                        startActivity(new Intent(getApplicationContext(), Order.class));
                        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_exit);
                        finish();
                        return true;
                    case R.id.page_add:
                        startActivity(new Intent(getApplicationContext(), Addmenu.class));
                        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_exit);
                        finish();
                        return true;
                    case R.id.page_scan:
                        startActivity(new Intent(getApplicationContext(), Scan.class));
                        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_exit);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                return true;
            case R.id.account:
                Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_LONG).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}