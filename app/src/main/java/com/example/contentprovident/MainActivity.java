package com.example.contentprovident;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit = (Button)findViewById(R.id.btn_ExitMenu);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.infbook:
                Intent intent_book = new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent_book);
                return true;
            case R.id.infauthor:
                Intent intent_author = new Intent(MainActivity.this,AuthorActivity.class);
                startActivity(intent_author);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
