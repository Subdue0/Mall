package me.myshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.myshop.R;


public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String uid = getIntent().getStringExtra("uid");
        String password = getIntent().getStringExtra("password");

        TextView tv_uid = findViewById(R.id.tv_uid);
        TextView tv_password = findViewById(R.id.tv_password);

        tv_uid.setText(uid);
        tv_password.setText(password);
    }
}
