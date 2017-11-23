package com.example.mathijs.mathijsparmentierpset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TodoDatabase db;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Database
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();

        // Set Up Adapter
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new TodoAdapter(getApplicationContext(), cursor);
        listView.setAdapter(adapter);

        // Set Up onItemClickListeners
        listView.setOnItemClickListener(new listViewOnClickListener());
        listView.setOnItemLongClickListener(new listViewOnItemLongClickListener());
    }

    public void submitTask(View view) {
        TextView inputView = (TextView) findViewById(R.id.inputView);
        TodoDatabase db = TodoDatabase.getInstance(this);
        db.insert(inputView.getText().toString(), 0);
        updateData();
        inputView.setText("");
    }

    private void updateData() {
        Cursor newCursor = db.selectAll();
        adapter.swapCursor(newCursor);
    }

    private class listViewOnClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object list = parent.getItemAtPosition(position);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            int completed = checkBox.isChecked() ? 0 : 1;
            db.update(id, completed);
            updateData();
        }
    }

    private class listViewOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            db.delete(id);
            updateData();
            return true;
        }
    }
}
