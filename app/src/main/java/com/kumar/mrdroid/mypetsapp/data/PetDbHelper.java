package com.kumar.mrdroid.mypetsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kumar.mrdroid.mypetsapp.data.PetContract.PetEntry;

/**
 * Created by mrdroid on 13/12/17.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    public final static int DATABASE_VERSION= 1;
    public final static String DATABASE_NAME= "petsShelter.db";



    public PetDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PETS_TABLE ="CREATE TABLE " + PetEntry.TABLE_NAME+ "(" +
                PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "+
                PetEntry.COLUMN_PET_BREED + " TEXT, "+
                PetEntry.COLUMN_PET_GENDER + " INTEGER, "+
                PetEntry.COLUMN_PET_WEIGHT + " INTEGER" + ")" ;

        db.execSQL(CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
