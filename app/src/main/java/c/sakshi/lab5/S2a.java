package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class S2a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2a);
        TextView tv_username = findViewById(R.id.tv_username);
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("u");
        tv_username.setText("Welcome " + username + "!");
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
                addNote();
                return true;
            case R.id.itm_logout:
                finish();
                return true;
            default:
                Log.e("MENU", "Misclassified menu item:" + item.toString());
                return false;
        }
    }

    public void addNote(){

    }
}
