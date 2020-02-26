package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class S2a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2a);
        TextView tv_username = findViewById(R.id.tv_username);
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("u");
        tv_username.setText("Hello " + username);
    }
}
