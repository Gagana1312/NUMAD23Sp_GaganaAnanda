package edu.northeastern.numad23sp_gaganaananda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button click;
    private Button link_collector;
    private Button aboutMe;
    private Button prime;
    Button location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aboutMe = findViewById(R.id.Button5);
        aboutMe.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Activity3.class);
            startActivity(intent);
        });
        click = findViewById(R.id.Button2);
        click.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Activity2.class);
            startActivity(intent);
        });
        link_collector = findViewById(R.id.link_collector);
        link_collector.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LinkCollector2.class);
            startActivity(intent);
        });
        prime = findViewById(R.id.Button6);
        prime.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Activity4.class);
            startActivity(intent);
        });
        location=findViewById(R.id.Location);
        location.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LocationActivity.class);
            startActivity(intent);
        });
    }
//    public void onClick(View view) {
//        Intent intent3 = new Intent(MainActivity.this,AboutMeActivity.class);
//        startActivity(intent3);
//        Toast.makeText(MainActivity.this,"Gagana Ananda \n ananda.g@northeastern.edu",Toast.LENGTH_LONG).show();
    }

