package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     EditText taskInput;
     Button addButton;
     ListView taskList;
     ArrayList<String>tasks;
     ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.task_input);
        addButton = findViewById(R.id.add_button);
        taskList = findViewById(R.id.task_list);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tasks);
        taskList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskInput.getText().toString();
                if (!task.equals("")){
                    tasks.add(task);
                    adapter.notifyDataSetChanged();
                    taskInput.setText("");
                }
            }
        });
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete Task").setMessage("Do you want to delete this task").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedTask = tasks.get(position);
                        tasks.remove(selectedTask);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("cancel",null).create().show();
            }
        });
    }
}