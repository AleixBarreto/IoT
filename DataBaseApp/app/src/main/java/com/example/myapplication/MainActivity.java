package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnexionSQLiteHelper sqlHelper = new ConnexionSQLiteHelper(this);

        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        if(db != null)
        {
            Utils.insertBrand(db, "Brand1");
            Utils.insertBrand(db, "Brand2");
            Utils.insertBrand(db, "Brand3");

            Cursor c = Utils.selectBrands(db);

            if (c.moveToFirst()) {
                do {
                    String id= c.getString(0);
                    String name = c.getString(1);

                    System.out.println("id: " + id + ", name: " + name);
                } while(c.moveToNext());
            }
        }
    }
}
