package com.example.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class AddMenu extends AppCompatActivity {
    private static final int GET_GALLERY_IMAGE = 200;
    private Toolbar toolbar;
    private ImageView ImageView_food;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###");
    private String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);

        // toolbar setting
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.page_add);

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
        ImageView_food = (ImageView)findViewById(R.id.img1);


        //Glide.with(this).load("http://whdvm1.dothome.co.kr/Android/uploads/20210629_0209491624931857120.png").into(this.ImageView_food);
        //Picasso.get().load("http://whdvm1.dothome.co.kr/Android/uploads/20210629_0209491624931857120.png").into(ImageView_food);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.img1:
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, GET_GALLERY_IMAGE);
                        break;
                    case R.id.btn_UploadMenu:
                        //DB에 데이터 등록
                        
                        break;
                }
            }
        };
        ImageView_food.setOnClickListener(clickListener);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddMenu.this, MenuList.class);
        startActivity(intent);
        overridePendingTransition(R.anim.horizon_enter, R.anim.none);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            ImageView_food.setImageURI(selectedImageUri);
        }
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
