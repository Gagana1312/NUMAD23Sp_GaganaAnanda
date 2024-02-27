package edu.northeastern.numad23sp_gaganaananda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Activity4 extends AppCompatActivity {

    private static final String TAG = "Activity4";

    private final AtomicInteger counter = new AtomicInteger(1);

    private Handler handler = new Handler();

    Boolean task=true;

    TextView num;

    TextView prime_num;

    ArrayList<Integer> numbers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        num = findViewById(R.id.textView4);
        prime_num = findViewById(R.id.textView5);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("num1",num.getText().toString());
        outState.putString("prime_num1",prime_num.getText().toString());
        outState.putBoolean("task1",false);
    }
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        prime_num.setText(savedInstanceState.getString("prime_num1"));
        task = savedInstanceState.getBoolean("task1");
        if(!task){
            counter.set(Integer.parseInt(savedInstanceState.getString("num1")));
            runnableThread thread2 = new runnableThread();
            new Thread(thread2).start();
        } else
        {
            num.setText(savedInstanceState.getString("num1"));
        }
    }
    @Override
    public void onBackPressed(){
        if(task==false){
            showExitDialog();
        } else {
            super.onBackPressed();
        }

    }
public void runOnRunnableThread(View view){
        task = false;
        counter.set(1);
        runnableThread thread = new runnableThread();
        new Thread(thread).start();

}
public void stopTask(View view){
        task= true;
        counter.set(1);
        prime_num.setText(String.valueOf(FindPrime(numbers)));
        numbers = new ArrayList<>();
}
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to terminate the search?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


class runnableThread implements Runnable{

    @Override
    public void run() {
        numbers = new ArrayList<>();
        while (!task) {
            //The handler changes the TextView running in the UI thread.
            handler.post(() -> {
                increment();
                Log.d(TAG,"number:"+numbers);
            });
            Log.d(TAG, "Running on a different thread using Runnable Interface: " + counter.get());
            try {
                Thread.sleep(1000);
                //Makes the thread sleep or be inactive for 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

    public int getValue() {
        return counter.get();
    }

    public void increment() {
        while(true) {
            int existingValue = getValue();
            int newValue = existingValue + 2;
            if(counter.compareAndSet(existingValue, newValue)) {
                num.setText(String.valueOf(counter.get()));
                numbers.add(counter.get());
                return;
            }
        }
    }
    public int FindPrime(ArrayList<Integer> arrayList){
        int largestPrime = Integer.MIN_VALUE;
        for (int number : numbers) {
            if (isPrime(number) && number > largestPrime) {
                largestPrime = number;
            }
        }
        return largestPrime;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

