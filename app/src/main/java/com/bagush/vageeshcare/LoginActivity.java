package com.bagush.vageeshcare;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button loginB;
    Button registerB;
    TextInputEditText nameIp;
    TextInputEditText pwdIp;
    DBManager database;
    ArrayList res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerB = findViewById(R.id.registerB);
        loginB = findViewById(R.id.loginB);

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameIp = findViewById(R.id.nameIP);
                pwdIp = findViewById(R.id.pwdIP);

                String name, pwd;

                name = nameIp.getText().toString();
                pwd = pwdIp.getText().toString();

                if( name.matches("") || pwd.matches("")){
                    Toast.makeText(getApplicationContext(),"Please enter all details", Toast.LENGTH_LONG).show();
                } else {
                    database = new DBManager(getApplicationContext());

                    database.open();
                    res = database.is_valid_login(name, pwd);

                    if(res == null){
                        Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_LONG).show();
                    } else {
                        Log.v("Log success ", res.toString());
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        i.putExtra("name", res.get(0).toString());
                        i.putExtra("age", res.get(1).toString());
                        i.putExtra("blood_group", res.get(2).toString());
                        i.putExtra("height", res.get(3).toString());
                        i.putExtra("weight", res.get(4).toString());
                        i.putExtra("gender", res.get(5).toString());
                        i.putExtra("email", res.get(6).toString());
                        startActivity(i);
                    }
                    database.close();
                }
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = ("vageeshcare");
            String description = ("A health app");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("vageeshcare", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
