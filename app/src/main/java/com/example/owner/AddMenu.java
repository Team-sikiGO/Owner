package com.example.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class AddMenu extends AppCompatActivity {
    private static final int GET_GALLERY_IMAGE = 200;
    private Toolbar toolbar;
    private ImageView foodImage;
    private TextView imageViewText;
    private EditText regMenu;
    private EditText regPrice;
    private Button uploadMenu;
    private String imagePath;

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
        foodImage = (ImageView)findViewById(R.id.menu_image);
        imageViewText = (TextView)findViewById(R.id.myImageViewText);
        uploadMenu = (Button)findViewById(R.id.btn_UploadMenu);
        regMenu = (EditText)findViewById(R.id.register_menu);
        regPrice = (EditText)findViewById(R.id.register_price);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.menu_image:
                        //메뉴 이미지 선택
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        /*intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, GET_GALLERY_IMAGE);*/
                        intent.setType("image/*");
                        startActivityForResult(intent, GET_GALLERY_IMAGE);
                        break;
                    case R.id.btn_UploadMenu:
                        //DB에 데이터 등록
                        String resName = "두첩분식";
                        String menu = regMenu.getText().toString();
                        String price = regPrice.getText().toString();
                        String serverUrl = "http://whdvm1.dothome.co.kr/OWNER/uploadDB.php";

                        SimpleMultiPartRequest smpr = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(AddMenu.this, "전송 성공", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AddMenu.this, "전송 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
                        smpr.addStringParam("resName", resName);
                        smpr.addStringParam("menu", menu);
                        smpr.addStringParam("price", price);
                        smpr.addFile("imagePath", imagePath);

                        RequestQueue requestQueue = Volley.newRequestQueue(AddMenu.this);
                        requestQueue.add(smpr);

                        break;
                }
            }
        };
        foodImage.setOnClickListener(clickListener);
        uploadMenu.setOnClickListener(clickListener);
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
        /*if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            ImageView_food.setImageURI(selectedImageUri);
        }*/
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if(uri != null) {
                foodImage.setImageURI(uri);
                imagePath = getRealPathFromUri(uri);
                imageViewText.setVisibility(View.INVISIBLE);

                new AlertDialog.Builder(this).setMessage(uri.toString() + "\n" + imagePath).create().show();
            }
        }
    }

    //이미지의 URL을 절대경로로 바꿔주는 메소드
    String getRealPathFromUri(Uri uri) {
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
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
