package edu.northeastern.numad23sp_gaganaananda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;



public class LinkCollector2 extends AppCompatActivity {

    FloatingActionButton AddLink;

     RecyclerView linkRecyclerView;

     List<Links> LinksList;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector2);



        //floating button
        AddLink = findViewById(R.id.AddLink);
        //Recycler View list
        LinksList = new ArrayList<>();
        ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        //Adding a new Data object to the personList arrayList
        //  LinksList.add(new Links("Click on the floating butting", "To add Name and the Link"));
        //Taking input through dialog box
        AddLink.setOnClickListener(view -> {
            Dialog dialog = new Dialog(LinkCollector2.this);
            dialog.setContentView(R.layout.link_collector);
            EditText Name = dialog.findViewById(R.id.Name);
            EditText link = dialog.findViewById(R.id.Link);
            Button save = dialog.findViewById(R.id.SaveLink);

            //Saving inputs from user and adding it to the list.
            save.setOnClickListener(view1 -> {
                String potentialUrl = "http://"+link.getText().toString()+"/";
                if (isValid(potentialUrl)) {
                    LinksList.add(new Links(Name.getText().toString(), potentialUrl));
                    Snackbar snackbar = Snackbar.make(constraintLayout, "Updated Item |" +
                            " Swipe to delete", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    dialog.dismiss();
                } else {
                    Snackbar snackbar2 = Snackbar.make(constraintLayout, "invalid URL"
                            , Snackbar.LENGTH_SHORT);
                    snackbar2.show();
                }
            });
            dialog.show();
        });

        linkRecyclerView = findViewById(R.id.Links_Recycler_view);

        //This defines the way in which the RecyclerView is oriented
        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        linkRecyclerView.setAdapter(new LinksAdapter(LinksList, this));

        //Swipe Delete Feature to the Recycler View
        ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                LinksList.remove(viewHolder.getAdapterPosition());
                Toast.makeText(LinkCollector2.this,"Deleted item",Toast.LENGTH_LONG).show();

            }
        };
        ItemTouchHelper itemTouchHelper1= new ItemTouchHelper(itemTouchHelper);
        itemTouchHelper1.attachToRecyclerView(linkRecyclerView);

    }
    //Validate URL function
    public static boolean isValid(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}


