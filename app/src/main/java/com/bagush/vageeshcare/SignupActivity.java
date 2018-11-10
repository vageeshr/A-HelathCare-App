package com.bagush.vageeshcare;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button registerB;
    TextInputEditText nameIP;
    TextInputEditText pwdIP;
    TextInputEditText ageIP;
    Spinner bloodSP;
    TextInputEditText heightIP;
    TextInputEditText weightIP;
    RadioButton maleR;
    RadioButton femaleR;
    RadioButton otherR;
    TextInputEditText emailIP;
    DBManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerB = findViewById(R.id.regSubB);
        nameIP = findViewById(R.id.nameIP);
        pwdIP = findViewById(R.id.pwdIP);
        ageIP = findViewById(R.id.ageIP);
        bloodSP = findViewById(R.id.Bspinner);
        heightIP = findViewById(R.id.heightIP);
        weightIP = findViewById(R.id.weightIP);
        maleR = findViewById(R.id.maleR);
        femaleR = findViewById(R.id.femaleR);
        otherR = findViewById(R.id.otherR);
        emailIP = findViewById(R.id.emailIP);

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,pwd, age, ht, wt, gender, email, blood;

                name = nameIP.getText().toString();
                pwd = pwdIP.getText().toString();
                age = ageIP.getText().toString();
                ht = heightIP.getText().toString();
                wt = weightIP.getText().toString();
                email = emailIP.getText().toString();
                blood = bloodSP.getSelectedItem().toString();

                gender = null;
                if(maleR.isChecked()) gender = "male";
                if(femaleR.isChecked()) gender = "female";
                if(otherR.isChecked()) gender = "other";

                Log.v("name",name);
                Log.v("pwd",pwd);
                Log.v("age",age);
                Log.v("ht",ht);
                Log.v("wt",wt);
                Log.v("email",email);
                Log.v("blood",blood);
                Log.v("gender",gender);

                if(name.matches("") || pwd.matches("") || age.matches("") || ht.matches("") || wt.matches("") || gender ==null ){
                    Toast.makeText(getApplicationContext(),"Please enter all details", Toast.LENGTH_LONG).show();
                } else{

                    database = new DBManager(getApplicationContext());
                    database.open();

                    if(database.insertUser(name, pwd, age, ht, wt, gender, blood, email) != -1){
                        Toast.makeText(getApplicationContext(),"Registered", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(),"There was an error", Toast.LENGTH_LONG).show();
                    }

                    database.close();

                }
            }
        });
    }

}
