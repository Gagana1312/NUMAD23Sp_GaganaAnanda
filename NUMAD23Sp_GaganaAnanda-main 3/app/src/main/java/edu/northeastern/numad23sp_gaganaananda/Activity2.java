package edu.northeastern.numad23sp_gaganaananda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    private Button btA;
    private Button btB;
    private Button btC;
    private Button btD;
    private Button btE;
    private Button btF;
    TextView Pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Pressed = findViewById(R.id.Pressed);
        btA = findViewById(R.id.A);
        btB = findViewById(R.id.B);
        btC = findViewById(R.id.C);
        btD = findViewById(R.id.D);
        btE = findViewById(R.id.E);
        btF = findViewById(R.id.F);
        btA.setOnClickListener(this);
        btB.setOnClickListener(this);
        btC.setOnClickListener(this);
        btD.setOnClickListener(this);
        btE.setOnClickListener(this);
        btF.setOnClickListener(this);
//
//        btA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle.setText(" A ");
//            }
//        });
//        btB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle.setText(" B ");
//            }
//        });
//        btC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle.setText(" C ");
//            }
//        });
//        btD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle.setText(" D ");
//            }
//        });
//        btE.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle.setText(" E ");
//            }
//        });
//        btF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle.setText(" F ");
//            }
//        });
//
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.A:
                Pressed.setText("Pressed: A ");
                break;
            case R.id.B:
                Pressed.setText("Pressed: B ");
                break;
            case R.id.C:
                Pressed.setText("Pressed: C ");
                break;
            case R.id.D:
                Pressed.setText("Pressed: D ");
                break;
            case R.id.E:
                Pressed.setText("Pressed: E ");
                break;
            case R.id.F:
                Pressed.setText("Pressed: F ");
                break;
            default:
                Pressed.setText("Pressed: - ");
        }

    }

}