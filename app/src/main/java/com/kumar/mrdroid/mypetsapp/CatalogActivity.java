package com.kumar.mrdroid.mypetsapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kumar.mrdroid.mypetsapp.data.PetContract;
import com.kumar.mrdroid.mypetsapp.data.PetContract.PetEntry;
import com.kumar.mrdroid.mypetsapp.data.PetDbHelper;

public class CatalogActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PET_LOADER = 0;
    private PetCursorAdapter mCursorAdapter;

    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        ListView listView = (ListView) findViewById(R.id.list_View);

        /***
         * find and set empty view
         */
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        /***
         * set cursorAdapter to list view
         */
        mCursorAdapter= new PetCursorAdapter(this, null);
        listView.setAdapter(mCursorAdapter);

        /***
         * start cursor loader
         */
        getLoaderManager().initLoader(PET_LOADER, null, this);

    }



    /*****
     *
     * insertPet() method for generating Dummy data
     */


    private void insertPet(){
        PetDbHelper mDbHelper = new PetDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "bear");
        values.put(PetEntry.COLUMN_PET_BREED, "German shepherd");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        long newRowId = db.insert(PetEntry.TABLE_NAME, null, values);

        Log.v("Catalog Activity", "newRowId: " + newRowId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // Do nothing for now
                insertPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection= {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
        };

        return new CursorLoader(this,
                PetEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mCursorAdapter.swapCursor(null);
    }
}
