package com.example.myreminders;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AddReminders extends CursorAdapter {

    public AddReminders(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.li_addreminder_list, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.titleTextView)).
                setText(cursor.getString(cursor.getColumnIndex("title")));
        ((TextView) view.findViewById(R.id.dateTextView)).
                setText(cursor.getString(cursor.getColumnIndex("date")));
        //get the reminder date and store it in variable
        String reminderdate = cursor.getString(cursor.getColumnIndex("date"));
        //getting the current date
        Date currentdate = new Date();
        //converting string to date
        try {
            //converting reminderdate to a string
            Date newreminderdate = new SimpleDateFormat("yyyy-MM-dd").parse(reminderdate);
            if(currentdate.compareTo(newreminderdate) <0) {
                ((TextView) view.findViewById(R.id.dateexpiredTextView)).
                        setText(("Expired ? True"));
            } else if (newreminderdate.compareTo(currentdate) < 0) {
                ((TextView) view.findViewById(R.id.dateexpiredTextView)).
                        setText(("Expired ? False"));

            }
        } catch (Exception e) {
        }


        }
    }


