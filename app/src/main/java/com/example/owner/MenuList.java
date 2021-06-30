package com.example.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        // toolbar setting
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.page_home);

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
                    case R.id.page_scan:
                        startActivity(new Intent(getApplicationContext(), Scan.class));
                        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_exit);
                        finish();
                        return true;
                    case R.id.page_add:
                        return true;
                }
                return false;
            }
        });

        final ListView list;
        MenuAdapter menuAdapter;
        menuAdapter = new MenuAdapter();
        list = (ListView) findViewById(R.id.main_list);
        list.setAdapter(menuAdapter);

        Intent Add_menu = new Intent(getApplicationContext(), AddMenu.class);
        Intent modify_menu = new Intent(getApplicationContext(), ModifyMenu.class);

        Button Btn = (Button)findViewById(R.id.add_menu);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Add_menu);
                overridePendingTransition(R.anim.horizon_enter, R.anim.none);
                finish();
            }
        });



        //나중에 DB에서 불러올 때 수정할 곳.
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;
        menuAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cm27014203), "음식이름", "가격") ;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                startActivity(modify_menu);
                overridePendingTransition(R.anim.horizon_enter, R.anim.none);
                finish();
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