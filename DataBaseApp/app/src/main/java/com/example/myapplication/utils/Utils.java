package com.example.myapplication.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Utils {

    public static final String DB_NAME ="Database";
    public static final String TABLE_PRODUCTS_NAME ="Products";
    public static final String TABLE_BRANDS_NAME ="Brands";
    public static final String PRODUCTS_FIELD_ID="ID";
    public static final String PRODUCTS_FIELD_NAME="Name";
    public static final String PRODUCTS_FIELD_BRAND_ID ="BrandID";
    public static final String BRANDS_FIELD_ID="ID";
    public static final String BRANDS_FIELD_NAME="Name";

    public static final String CREATE_TABLE_BRANDS =
            String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, %s TEXT NOT NULL)",
                    TABLE_BRANDS_NAME, BRANDS_FIELD_ID, BRANDS_FIELD_NAME);

    public static final String CREATE_TABLE_PRODUCTS =
            String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, %s TEXT NOT NULL," +
                            "%s INTEGER NOT NULL, FOREIGN KEY (%s)" +
                                "REFERENCES %s(%s)" +
                                "ON DELETE CASCADE)",
                    TABLE_PRODUCTS_NAME, PRODUCTS_FIELD_ID, PRODUCTS_FIELD_NAME, PRODUCTS_FIELD_BRAND_ID,
                    PRODUCTS_FIELD_BRAND_ID, TABLE_BRANDS_NAME, BRANDS_FIELD_ID);

    public static void init(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_PRODUCTS_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_BRANDS_NAME);
        db.execSQL(Utils.CREATE_TABLE_PRODUCTS);
        db.execSQL(Utils.CREATE_TABLE_BRANDS);
    }

    public static void insertProduct(SQLiteDatabase db, String name, int brand ){
        db.rawQuery(String.format("INSERT INTO %s (%s, %s) VALUES (%s, %i);",
                TABLE_PRODUCTS_NAME, PRODUCTS_FIELD_NAME, PRODUCTS_FIELD_BRAND_ID, name, brand), null);
    }

    public static void insertBrand(SQLiteDatabase db, String name){
        db.rawQuery(String.format("INSERT INTO %s (%s) VALUES (%s);",
                TABLE_BRANDS_NAME, BRANDS_FIELD_NAME, name), null);
    }

    public static Cursor selectProducts(SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM "+ TABLE_PRODUCTS_NAME, null);
    }

    public static Cursor selectBrands(SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM "+ TABLE_BRANDS_NAME, null);
    }

    public static void removeProduct(SQLiteDatabase db, int id){
        db.rawQuery(String.format("DELETE FROM %s WHERE %s = %i;",
                TABLE_PRODUCTS_NAME, PRODUCTS_FIELD_ID, id), null);
    }

    public static void removeBrand(SQLiteDatabase db, int id){
        db.rawQuery(String.format("DELETE FROM %s WHERE %s = %i;",
                TABLE_BRANDS_NAME, BRANDS_FIELD_ID, id), null);
    }

    public static void updateProduct(SQLiteDatabase db, int id, String name, int brand){
        db.rawQuery(String.format("UPDATE %s SET %s = %s, %s = %i WHERE %s = %i;",
                TABLE_PRODUCTS_NAME, PRODUCTS_FIELD_NAME, name, PRODUCTS_FIELD_BRAND_ID, brand, PRODUCTS_FIELD_ID, id), null);
    }

    public static void updateBrand(SQLiteDatabase db, int id, String name){
        db.rawQuery(String.format("UPDATE %s SET %s = %s, %s = %i WHERE %s = %i;",
                TABLE_BRANDS_NAME, BRANDS_FIELD_NAME, name, BRANDS_FIELD_ID, id), null);
    }
}