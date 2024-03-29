package com.tianos.koketa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tianos.koketa.entity.Breadcrumb;
import com.tianos.koketa.entity.Category;
import com.tianos.koketa.entity.Order;
import com.tianos.koketa.entity.OrderDetail;
import com.tianos.koketa.entity.Product;
import com.tianos.koketa.entity.Profile;
import com.tianos.koketa.entity.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "koketa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creating Tables
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Breadcrumb.CREATE_TABLE);
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(Profile.CREATE_TABLE);
        db.execSQL(Category.CREATE_TABLE);
        db.execSQL(Product.CREATE_TABLE);
        db.execSQL(Order.CREATE_TABLE);
        db.execSQL(OrderDetail.CREATE_TABLE);
    }

    /**
     * Upgrading database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Breadcrumb.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Profile.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Category.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Product.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Order.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OrderDetail.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
