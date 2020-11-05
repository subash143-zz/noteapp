package com.subash.projects.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditNoteActivity extends AppCompatActivity implements View.OnClickListener {

    int noteId;
    Button btnCancel, btnSave, btnDelete;
    EditText etNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        etNote = findViewById(R.id.etNote);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        noteId = getIntent().getIntExtra("NoteId", -1);
        if(noteId != -1){
            btnSave.setText("Edit Note");
            etNote.setText(MainActivity.notes.get(noteId));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSave:
                    if(noteId != -1){
                        MainActivity.notes.set(noteId, etNote.getText().toString());
                    }
                    else{
                        MainActivity.notes.add(etNote.getText().toString());
                    }
                    finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnDelete:
                    MainActivity.notes.remove(noteId);
                    finish();
                break;
            default:
                break;
        }
    }
}