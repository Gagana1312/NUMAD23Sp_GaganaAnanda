package edu.northeastern.numad23sp_gaganaananda;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinksViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView link;

    public LinksViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        this.link = itemView.findViewById(R.id.link);
    }
}