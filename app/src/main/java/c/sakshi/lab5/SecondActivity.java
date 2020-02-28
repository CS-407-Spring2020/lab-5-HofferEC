package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences sp;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = sp.getString("u", null);
        if (username == null) {
            Log.e("LOGIN", "Trying to launch SecondActivity with null username in SP.");
            logout();
        }

        TextView tv_username = findViewById(R.id.tv_username);
        tv_username.setText("Welcome " + username + "!");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(username);

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes){
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.lv_notes_list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addNote(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.itm_addnote:
                addNote(-1);
                return true;
            case R.id.itm_logout:
                logout();
                return true;
            default:
                Log.e("MENU", "Misclassified menu item:" + item.toString());
                return false;
        }
    }

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        if (sp.contains("u")) {
            sp.edit().remove("u").apply();
        }
        startActivity(intent);
        finish();
    }

    public void addNote(int position) {
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        intent.putExtra("noteid", position);
        startActivity(intent);
        finish();
    }
}
