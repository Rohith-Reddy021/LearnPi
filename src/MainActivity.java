package com.example.learnpi;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress2;
    private EditText editTextNumberPassword;
    private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextEmailAddress2=(EditText) findViewById(R.id.editTextTextEmailAddress2);
        editTextNumberPassword=(EditText) findViewById(R.id.editTextNumberPassword);
        button=(Button) findViewById(R.id.button);
        loadData();
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final String e =editTextTextEmailAddress2.getText().toString();
                final String p=editTextNumberPassword.getText().toString();
                if (TextUtils.isEmpty(e))
                    editTextTextEmailAddress2.setError("Field is required");
                else if(TextUtils.isEmpty(p))
                    editTextNumberPassword.setError("Field is required");
                 else {
                    saveData();
                    openActivity2();
                }
            }
        });
    }

    private void openActivity2() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    private void saveData() {
        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("Unm",editTextTextEmailAddress2.getText().toString() );
        Ed.putString("Psw",editTextNumberPassword.getText().toString());
        Ed.commit();
    }

    private void loadData() {
        SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);

        String unm=sp1.getString("Unm", null);
        String pass = sp1.getString("Psw", null);
        editTextTextEmailAddress2.setText(unm);
        editTextNumberPassword.setText(pass);
    }

}
