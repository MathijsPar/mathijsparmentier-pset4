package com.example.mathijs.mathijsparmentierpset4;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Mathijs on 20/11/2017.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public TodoAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_todo, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView todo_Title = (TextView) view.findViewById(R.id.todo_Title);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        todo_Title.setText(cursor.getString(cursor.getColumnIndex("title")));
        checkBox.setChecked(cursor.getInt(cursor.getColumnIndex("completed")) == 1);
    }
}
