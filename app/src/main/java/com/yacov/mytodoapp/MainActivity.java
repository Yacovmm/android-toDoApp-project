package com.yacov.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mTaskList;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTaskList = (RecyclerView) findViewById(R.id.task_list);
        mTaskList.setHasFixedSize(true);
        mTaskList.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks");

        //Adding the data values
        TextView bannerDay = (TextView) findViewById(R.id.bannerDay);
        TextView bannerDate = (TextView) findViewById(R.id.bannerDate);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        bannerDay.setText(dayOfTheWeek);

        long date = System.currentTimeMillis();
        SimpleDateFormat sdff = new SimpleDateFormat("MMM dd/MM/yyy h:mm a");
        String dayString = sdff.format(date);
        bannerDate.setText(dayString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public TaskViewHolder(View itemView){
            super(itemView);
            View mView = itemView;
        }

        public void setName(String name){
            TextView task_name = itemView.findViewById(R.id.taskNameID);
            task_name.setText(name);
        }

        public void setDate(String date){
            TextView task_time = itemView.findViewById(R.id.taskTimeID);
            task_time.setText(date);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Task, TaskViewHolder> FBRA = new FirebaseRecyclerAdapter<Task, TaskViewHolder>(
                Task.class,
                R.layout.task_row,
                TaskViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(TaskViewHolder viewHolder, Task model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setDate(model.getDate());

            }
        };
        mTaskList.setAdapter(FBRA);
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
        }else if (id == R.id.addTask){
            Intent addIntent = new Intent(MainActivity.this, AddTask.class);
            startActivity(addIntent);
        }


        return super.onOptionsItemSelected(item);
    }
}
