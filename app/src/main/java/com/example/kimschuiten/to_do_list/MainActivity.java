package com.example.kimschuiten.to_do_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addToArray;
    EditText addToDo;
    ListView toDoListView;
    ArrayList<String> addArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize EditText, Button and ListView
        addToDo = (EditText) findViewById(R.id.listAddEditText);
        addToArray = (Button) findViewById(R.id.addButton);
        toDoListView = (ListView) findViewById(R.id.ListView1);

        // set Button OnClickListener
        addToArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from input field
                String getInput = addToDo.getText().toString();

                // If getInput exists, don't add
                if (addArray.contains(getInput)) {
                    Toast.makeText(getBaseContext(), "You already have this on your TODO list", Toast.LENGTH_LONG).show();
                }
                // if getInput is empty, don't add but prompt for input
                else if (getInput == null || getInput.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "You can't add an empty task", Toast.LENGTH_LONG).show();
                }
                // Add getInput to array
                else {
                    addArray.add(getInput);
                    // create Array adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, addArray);
                    // set Array adapter
                    toDoListView.setAdapter(adapter);
                    ((EditText) findViewById(R.id.listAddEditText)).setText(" ");

                    // We notify the data model is changed
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // Add a longclick to delete items
        toDoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Delete rows when on clicked
                addArray.remove(position);

                // create Array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, addArray);

                // set Array adapter
                toDoListView.setAdapter(adapter);

                // We notify the data model is changed
                adapter.notifyDataSetChanged();

                // confirm deletion
                String taskCompleted = "You deleted this task";
                Toast.makeText(MainActivity.this, taskCompleted, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
