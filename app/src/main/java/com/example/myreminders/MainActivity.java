package com.example.myreminders;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //declare an Intent
    Intent intent;

    //declare a database handler
    DBHandler dbHandler;

    //declare a Shopping Lists Cursor Adapter
    myReminderList myReminderListAdapter;

    //declare a ListView
    ListView myReminderListView;

    /**
     * This method intializes the Action Bar and View of
     * the Main Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                // initializing an Intent for the Main Activity, starting it
                // and returning true
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_list:
                // initializing an Intent for the Create List Activity, starting it
                // and returning true
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openCreateList(View view) {
        intent = new Intent(this, CreateList.class );
        startActivity(intent);
    }
}
