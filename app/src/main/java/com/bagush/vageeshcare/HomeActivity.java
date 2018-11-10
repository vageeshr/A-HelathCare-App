package com.bagush.vageeshcare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    TextInputEditText nameSIP;
    ImageButton searchB;
    Button tip;
    ListView myListview;
    ArrayList<String> mylist;
    ArrayAdapter<String> myAdapter;
    DBManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Bundle data = getIntent().getExtras();

        myListview = findViewById(R.id.details);
        nameSIP = findViewById(R.id.searchIP);
        searchB = findViewById(R.id.searchB);

        if (data != null){

            mylist = new ArrayList<>();
            mylist.add("Name: " + data.getString("name"));
            mylist.add("Age: " + data.getString("age"));
            mylist.add("Blood Group: " + data.getString("blood_group"));
            mylist.add("Height: " + data.getString("height"));
            mylist.add("Weight: " + data.getString("weight"));
            mylist.add("Gender: " + data.getString("gender"));
            mylist.add("Email: " + data.getString("email"));

            myAdapter =  new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mylist);

            myListview.setAdapter(myAdapter);
        }


        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameSIP.getText().toString();

                if(name.matches("")){
                    Toast.makeText(getApplicationContext(),"Please enter a name to search", Toast.LENGTH_LONG).show();
                } else {
                    database = new DBManager(getApplicationContext());
                    database.open();

                    ArrayList searchRes = database.get_user(name);

                    myAdapter.clear();
                    if(searchRes != null){
                        mylist = new ArrayList<>();
                        mylist.add("Name: " + searchRes.get(0));
                        mylist.add("Age: " + searchRes.get(1));
                        mylist.add("Blood Group: " + searchRes.get(2));
                        mylist.add("Height: " + searchRes.get(3));
                        mylist.add("Weight: " + searchRes.get(4));
                        mylist.add("Gender: " + searchRes.get(5));
                        mylist.add("Email: " + searchRes.get(6));
                        myAdapter.addAll(mylist);
//                        myAdapter =  new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, searchRes);
                        myListview.setAdapter(myAdapter);
                    } else  {
                        Toast.makeText(getApplicationContext(),"No such user with name "+name, Toast.LENGTH_LONG).show();
                    }
                    database.close();
                }
            }
        });


        AlarmManager alarmMgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 777, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 58);

        // setRepeating() lets you specify a precise custom interval--in this case,
        // 20 minutes.

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 1, alarmIntent);
    }
}
