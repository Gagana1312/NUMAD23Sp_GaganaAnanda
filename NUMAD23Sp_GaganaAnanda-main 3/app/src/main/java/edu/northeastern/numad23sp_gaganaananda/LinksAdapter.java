package edu.northeastern.numad23sp_gaganaananda;


import static java.net.Proxy.Type.HTTP;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * This is a recyclerview adapter class, the purpose of this class is to act as a bridge between the
 * collection (arraylist) and the view (recyclerview). This class provides 3 methods that are
 * utilised for binding the data to the view. The explanation of each method is provided in comments
 * within the methods.
 */
public class LinksAdapter extends RecyclerView.Adapter<LinksViewHolder> {

    private final List<Links> list;
    private final Context context;

    /**
     * Creates a PersonAdapter with the provided arraylist of Person objects.
     *
     * @param list    arraylist of person object.
     * @param context   context of the activity used for inflating layout of the viewholder.
     */
    public LinksAdapter(List<Links> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create an instance of the viewholder by passing it the layout inflated as view and no root.

        return new LinksViewHolder(LayoutInflater.from(context).inflate(R.layout.linkrecyclerview, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LinksViewHolder holder, int position) {
        // sets the name of the person to the name textview of the viewholder.
        holder.name.setText(list.get(position).getName());
        // sets the age of the person to the age textview of the viewholder.
        holder.link.setText(String.valueOf(list.get(position).getLink()));
        // set a click event on the whole itemView (every element of the recyclerview).
        holder.itemView.setOnClickListener(view -> {
            String URL =list.get(position).getLink();
            Uri uri = Uri.parse(URL);
            Intent openURI = new Intent(Intent.ACTION_VIEW,uri);
            context.startActivity(openURI);
//            Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }
    public void deleteItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return list.size();
    }
}