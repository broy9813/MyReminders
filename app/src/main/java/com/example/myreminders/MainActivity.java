package com.example.myreminders;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declare an Intent
    Intent intent;

    //declare a database handler
    DBHandler dbHandler;

    //declare a Shopping Lists Cursor Adapter
    AddReminder addReminderAdapter;

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

    public void updateItem(long id){

        // if shopping list item's has indicator isn't true
        if (dbHandler.isReminderAdded((int)id) != 0){

            // update shopping list item's has indicator to true
            dbHandler.updateReminder
            // display toast indicating shopping list item has been purchased
            Toast.makeText(this, "Reminder Added!", Toast.LENGTH_LONG).show();

            // refresh shopping list item in the ListView -
            // change the Item Purchased from false to true
            addReminderAdapter.swapCursor(dbHandler.getaddReminder((int)this.id));
            addReminderAdapter.notifyDataSetChanged();
        }

        // if all shopping list items have been purchased
        if (dbHandler.getedremindernotadded((int)this.id) == 0){

            //initialize Notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

            //set it icon, title, and text
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("MyReminder");
            builder.setContentText("Reminder is Added!");

            //initilize qa PendingIntent
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            //set the content intent of the Notification
            builder.setContentIntent(pendingIntent);

            //initialize a NotificationManger
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //have the NotificationManager send the Notification
            notificationManager.notify(2142, builder.build());
        }
    }
}
