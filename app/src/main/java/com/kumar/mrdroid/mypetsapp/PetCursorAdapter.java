package com.kumar.mrdroid.mypetsapp;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.kumar.mrdroid.mypetsapp.data.PetContract;

/**
 * Created by mrdroid on 21/12/17.
 */

public class PetCursorAdapter extends CursorAdapter {


    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView petName, petBreed;

        petName = (TextView) view.findViewById(R.id.tv_PetName);
        petBreed = (TextView) view.findViewById(R.id.tv_PetBreed);

        int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);

        String pet_Name = cursor.getString(nameColumnIndex);
        String pet_Breed = cursor.getString(breedColumnIndex);

        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(pet_Breed)) {
            pet_Breed = context.getString(R.string.unknown_breed);
        }

        petName.setText(pet_Name);
        petBreed.setText(pet_Breed);


    }
}
