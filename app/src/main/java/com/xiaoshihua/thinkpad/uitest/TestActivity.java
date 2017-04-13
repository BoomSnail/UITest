package com.xiaoshihua.thinkpad.uitest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    ContentResolver contentResolver;
    EditText word,detail;
    Button insert,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        contentResolver = getContentResolver();
        Button button = (Button) findViewById(R.id.exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        word = (EditText) findViewById(R.id.word);
        detail = (EditText) findViewById(R.id.detail);
        insert = (Button) findViewById(R.id.insert);
        search = (Button) findViewById(R.id.search);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Word = word.getText().toString().trim();
                String Detail = detail.getText().toString().trim();
                ContentValues values = new ContentValues();
                values.put(Worlds.World.WORD,Word);
                values.put(Worlds.World.DETAIL,Detail);
                contentResolver.insert(Worlds.World.CONTENT_URI1,values);

                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = word.getText().toString().trim();
                Cursor cursor = contentResolver.query(Worlds.World.CONTENT_URI1,null,"word like ? or detail like ?",
                        new String[]{"%" + key + "%","%" + key + "%"},null);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",converCursorToList(cursor));
                //创建一个intent
                Intent intent = new Intent(TestActivity.this,MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Map<String,String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String,String>> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put(Worlds.World.WORD,cursor.getString(1));
            map.put(Worlds.World.DETAIL,cursor.getString(2));
            arrayList.add(map);
        }
        return arrayList;

    }


}
