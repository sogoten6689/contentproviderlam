package com.example.contentprovident;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText edtMa , edtTen, edtAddress,edtEmail;
    Button btnSave,btnSelect,btnExit,btnUpdate,btnDelete;
    GridView gvDisplay;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        edtMa = (EditText)findViewById(R.id.edt_Ma);
        edtTen = (EditText)findViewById(R.id.edt_Name);
        edtAddress = (EditText)findViewById(R.id.edt_Address);
        edtEmail = (EditText)findViewById(R.id.edt_Email);
        gvDisplay = (GridView)findViewById(R.id.gv_author);
        dbHelper = new DBHelper(this);

        btnSave = (Button)findViewById(R.id.btn_Save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(edtMa.getText().toString()));
                author.setName(edtTen.getText().toString());
                author.setAddress(edtAddress.getText().toString());
                author.setEmail(edtEmail.getText().toString());

                if(dbHelper.inserAuthor(author)>0)
                    Toast.makeText(getApplicationContext(),"Đã luu thành công ",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Luu k thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnSelect = (Button)findViewById(R.id.btn_SelectAu);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_string = new ArrayList<>();
                ArrayList<Author> list_author = new ArrayList<>();
                list_author = dbHelper.getAllAuthor();
                for(Author a : list_author){
                    list_string.add(a.getId_author()+"");
                    list_string.add(a.getName()+"");
                    list_string.add(a.getAddress()+"");
                    list_string.add(a.getEmail()+"");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthorActivity.this,android.R.layout.simple_list_item_1,list_string);
                gvDisplay.setAdapter(adapter);
            }
        });
    }
}
