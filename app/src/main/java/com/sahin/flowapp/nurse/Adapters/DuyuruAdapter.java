package com.sahin.flowapp.nurse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Models.DuyuruModel;
import com.sahin.flowapp.nurse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DuyuruAdapter extends RecyclerView.Adapter<DuyuruAdapter.ViewHolder>{

    List<DuyuruModel> list;
    Context context;
    String shortText;

    public DuyuruAdapter(List<DuyuruModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.duyuruitemlayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        viewHolder.duyuruBaslikText.setText(list.get(position).getBaslik().toString());


        if(list.get(position).getText().length()>20)
        {
            shortText = list.get(position).getText().substring(0,20) + "...";
            viewHolder.duyurutext.setText(shortText);
        }
        else
        {
            viewHolder.duyurutext.setText(list.get(position).getText().toString());
        }

        Picasso.get().load(list.get(position).getResim()).into(viewHolder.duyuruImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
//bibak
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView duyuruBaslikText, duyurutext;
        ImageView duyuruImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            duyuruBaslikText = (TextView)itemView.findViewById(R.id.duyuruBaslikText);
            duyurutext = (TextView)itemView.findViewById(R.id.duyurutext);
            duyuruImageView = (ImageView)itemView.findViewById(R.id.duyuruImageView);

        }
    }
}
