package com.example.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Response;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Order extends AppCompatActivity {
    private Toolbar toolbar;
    String clickDate[] = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // toolbar setting
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.page_order);

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
                        return true;
                    case R.id.page_add:
                        startActivity(new Intent(getApplicationContext(), MenuList.class));
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

        final ListView list;
        OrderAdapter dAdapter;
        dAdapter = new OrderAdapter();
        list = (ListView) findViewById(R.id.details_list);
        list.setAdapter(dAdapter);

        Intent detail = getIntent();
        String userID = detail.getStringExtra("userID");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        String resName = jsonObject.getString("resName");
                        String date = jsonObject.getString("date");
                        String menu = jsonObject.getString("menu");
                        int price = jsonObject.getInt("price");
                        clickDate[j] = date;

                        dAdapter.addItem(resName, date);
                        dAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
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