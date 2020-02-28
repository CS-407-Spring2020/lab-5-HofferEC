package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThirdActivity extends AppCompatActivity {

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        EditText et_note = findViewById(R.id.et_note);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);
        if (noteid != -1) {
            Note note = SecondActivity.notes.get(noteid);
            String noteContent = note.getContent();
            et_note.setText(noteContent);
        }
    }

    public void save(View v){
        EditText et_note = findViewById(R.id.et_note);
        String noteContent = et_note.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = sp.getString("u", null);
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (SecondActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, noteContent, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, noteContent);
        }

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
        finish();
    }
}
