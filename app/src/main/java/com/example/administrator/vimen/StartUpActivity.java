package com.example.administrator.vimen;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import db.TaskContract;
import db.TaskDBHelper;


public class StartUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        TaskDBHelper helper = new TaskDBHelper(StartUpActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values;
        values = new ContentValues();
        values.put(TaskContract.Columns.TASK, "Studentenausweis holen!");
        values.put(TaskContract.Columns.TASK, "Mensa!");
        db.insertWithOnConflict(TaskContract.TABLE, null, values,
                SQLiteDatabase.CONFLICT_IGNORE);
        updateUI();
    }
    public void onClickStart (View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateUI(){
        TaskDBHelper helper = new TaskDBHelper(StartUpActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
                null, null, null, null, null);
        ListAdapter listAdapter;
        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[] { TaskContract.Columns.TASK},
                new int[] { R.id.taskTextView},
                0
        );
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
