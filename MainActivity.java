package com.example.retime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
     Button _register_btn, _login_btn, _facebook_login_btn;
     EditText _main_password, _main_username;
     Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        _facebook_login_btn = (Button)findViewById(R.id.facebooklogin);
        _register_btn = (Button)findViewById(R.id.register_btn);
        _main_username = (EditText)findViewById(R.id.main_username);
        _main_password = (EditText)findViewById(R.id.main_password);
        _login_btn = (Button)findViewById(R.id.login_btn);

        _register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserRegister.class);
                startActivity(intent);
            }
        });

        _login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _main_username.getText().toString();
                String password = _main_password.getText().toString();
                //cursor = db.rawQuery("SELECT * FROM " +DatabaseHelper.TABLE_NAME+ "WHERE" + DatabaseHelper.COL_5 + DatabaseHelper.COL_4, new String[] {username, password});
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + " =? AND " + DatabaseHelper.COL_4 + " =? ", new String[] {username, password});
                if (cursor!=null){
                    if(cursor.getCount() > 0){
                        Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }

               // Intent intent = new Intent(MainActivity.this, login.class);
               // startActivity(intent);
            }
        });

        _facebook_login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
