package com.sahin.flowapp.nurse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Models.HasModel;
import com.sahin.flowapp.nurse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder>{

    List<HasModel> list;
    Context context;

    public PatientAdapter(List<HasModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.patientlistitemlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.patientlayoutpatientname.setText("isim"+list.get(position).getHasisim().toString());
        holder.patientlayoutcinsname.setText(list.get(position).getHascins().toString());
        holder.patientlayoutturname.setText(list.get(position).getHastur().toString());



        Picasso.get().load(list.get(position).getHasresim()).into(holder.patientlayoutpatientimage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView patientlayoutpatientname, patientlayoutcinsname, patientlayoutturname;
        ImageView patientlayoutpatientimage;


        //itemview ile listviewin her elemanı için  layout ile oluşturduğumuz view tanımlaması işlemi gerçekleşmesi
         public ViewHolder(View itemView) {
            super(itemView);

            patientlayoutpatientname = (TextView)itemView.findViewById(R.id.patientlayoutpatientname);
            patientlayoutcinsname = (TextView)itemView.findViewById(R.id.patientlayoutturname);
            patientlayoutturname = (TextView)itemView.findViewById(R.id.patientlayoutcinsname);
            patientlayoutpatientimage = (ImageView)itemView.findViewById(R.id.patientlayoutpatienttimage);
        }
    }
}
