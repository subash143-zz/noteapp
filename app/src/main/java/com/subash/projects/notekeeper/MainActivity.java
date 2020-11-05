package com.subash.projects.notekeeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Static field to store notes
    static List<String> notes = new ArrayList<>();
    ArrayAdapter<String> notesAdapter;
    Button btnAddNote, btnDeleteAllNotes;
    ListView listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Note Keeper");

        //UI Loading
        btnAddNote = findViewById(R.id.btnAddNote);
        btnDeleteAllNotes = findViewById(R.id.btnDeleteAllNotes);
        listNotes = findViewById(R.id.listNotes);

        //Event Listeners
        btnAddNote.setOnClickListener(this);
        btnDeleteAllNotes.setOnClickListener(this);

        //Data Initialization
        notes.add("New Note Just Created!");
        notesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        listNotes.setAdapter(notesAdapter);
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(MainActivity.this, AddEditNoteActivity.class);
                intent.putExtra("NoteId", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddNote:
                 Intent intent  = new Intent(MainActivity.this, AddEditNoteActivity.class);
                 startActivity(intent);
                break;
            case R.id.btnDeleteAllNotes:
                Log.i("Delete All Notes", "Clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Do you want to delete all notes?");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        notes.clear();
                        notesAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.notifyDataSetChanged();
    }
}