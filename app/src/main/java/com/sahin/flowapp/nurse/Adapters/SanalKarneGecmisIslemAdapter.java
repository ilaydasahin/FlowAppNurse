package com.sahin.flowapp.nurse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Models.IslemModel;
import com.sahin.flowapp.nurse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanalKarneGecmisIslemAdapter extends RecyclerView.Adapter<SanalKarneGecmisIslemAdapter.ViewHolder>{

    List<IslemModel> list;
    Context context;

    public SanalKarneGecmisIslemAdapter(List<IslemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.sanalkarnegecmislayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir
        holder.sanalKarneIslemIsmiText.setText(list.get(position).getIslemisim()+" işlemi Yapılmıştır.");
        holder.sanalKarneGecmisIslemBilgiText.setText(list.get(position).getIslemisim()+" isimli hastanıza "
                +list.get(position).getIslemtarih()+" tarihinde "+list.get(position).getIslemisim()+" işlemi yapılmıştır.");

        Picasso.get().load(list.get(position).getHasresim().toString()).into(holder.sanalKarneGecmisIslemImage);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sanalKarneIslemIsmiText, sanalKarneGecmisIslemBilgiText;
        ImageView sanalKarneGecmisIslemImage;

        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            sanalKarneIslemIsmiText = itemView.findViewById(R.id.sanalKarneIslemIsmiText);
            sanalKarneGecmisIslemBilgiText = itemView.findViewById(R.id.sanalKarneGecmisIslemBilgiText);
            sanalKarneGecmisIslemImage = itemView.findViewById(R.id.sanalKarneGecmisIslemImage);


        }
    }
}