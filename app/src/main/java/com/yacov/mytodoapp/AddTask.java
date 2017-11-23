package com.yacov.mytodoapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AddTask extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    EditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        database = FirebaseDatabase.getInstance();

    }

    public void addButtonClick(View view){
        editTask = (EditText) findViewById(R.id.editTaskID);

        String name = editTask.getText().toString();
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd/MM/yyy h:mm a");
        String dateString = sdf.format(date);

        myRef = database.getInstance().getReference().child("Tasks");

        DatabaseReference newTask = myRef.push();
        newTask.child("name").setValue(name);
        newTask.child("date").setValue(dateString);
    }
}
