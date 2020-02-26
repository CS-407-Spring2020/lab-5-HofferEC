package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class S2a extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2a);
        sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = sp.getString("u", null);
        if (username == null) {
            Log.e("LOGIN", "Trying to launch S2a with null username in SP.");
            logout();
        }

        TextView tv_username = findViewById(R.id.tv_username);
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

    public void addNote(){

    }
}
