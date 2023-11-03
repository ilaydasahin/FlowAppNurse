package com.sahin.flowapp.nurse.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sahin.flowapp.nurse.Models.PetModel;
import com.sahin.flowapp.nurse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder>{

    List<PetModel> list;
    Context context;

    public PetAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pet_list_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.petLayoutName.setText(list.get(position).getPetName().toString());
        viewHolder.petLayoutGenus.setText(list.get(position).getPetGenus().toString());
        viewHolder.petLayoutKind.setText(list.get(position).getPetKind().toString());



        Picasso.get().load(list.get(position).getPetImage()).into(viewHolder.petLayoutImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView petLayoutName, petLayoutGenus, petLayoutKind;
        ImageView petLayoutImage;

        public ViewHolder(View itemView) {
            super(itemView);

            petLayoutName = (TextView)itemView.findViewById(R.id.pet_layout_petname);
            petLayoutGenus = (TextView)itemView.findViewById(R.id.pet_layout_petgenus);
            petLayoutKind = (TextView)itemView.findViewById(R.id.pet_layout_petkind);
            petLayoutImage = (ImageView)itemView.findViewById(R.id.pet_layout_petimage);
        }
    }
}
