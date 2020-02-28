package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        if (sp.contains("u")) login(sp.getString("u", null));
        et_username = findViewById(R.id.et_username);
    }

    public void onButtonClick(View view){
        login(et_username.getText().toString());
    }

    public void login(String u) {
        if (u == null) {
            Log.e("LOGIN", "Error, trying to login with null username");
            return;
        }
        Intent intent = new Intent(this, SecondActivity.class);
        sp.edit().putString("u",u).apply();
        startActivity(intent);
        finish();
    }
}
