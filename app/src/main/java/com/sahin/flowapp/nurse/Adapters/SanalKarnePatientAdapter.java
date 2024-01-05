package com.sahin.flowapp.nurse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Fragments.IslemDetailFragment;
import com.sahin.flowapp.nurse.Models.HasModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;
public class SanalKarnePatientAdapter extends RecyclerView.Adapter<SanalKarnePatientAdapter.ViewHolder>{

    List<HasModel> list;
    Context context;

    public SanalKarnePatientAdapter(List<HasModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.sanalkarnepatientlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir
        holder.reportcardPatientNameText.setText(list.get(position).getHasisim().toString());
        holder.reportcardPatientInfoText.setText(list.get(position).getHasisim().toString() + " isimli " + list.get(position).getHastur() + " türüne " + list.get(position).getHascins() + " cinsine ait hastanıza " +
                "ait geçmiş işlemlerini görmek için tıklayınız...");


        Picasso.get().load(list.get(position).getHasresim()).into(holder.reportcardPatientCircleImageView);

        holder.viewCardReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeFragments changeFragments=new ChangeFragments(context);
                //parametre göndermek lazım fragmentler arası parametre göndermeliyiz bunun için changefragment class ımızı uygun hale getirdik

                changeFragments.changeWithParameters(new IslemDetailFragment(),list.get(position).getHasid());

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reportcardPatientInfoText, reportcardPatientNameText;
       ImageView reportcardPatientCircleImageView;
        CardView viewCardReport;

        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            reportcardPatientInfoText = (TextView)itemView.findViewById(R.id.reportcardPatientInfoText);
            reportcardPatientNameText =(TextView) itemView.findViewById(R.id.reportcardPatientNameText);
            reportcardPatientCircleImageView = (  ImageView)itemView.findViewById(R.id.reportcardPatientCircleImageView);
            viewCardReport =(CardView) itemView.findViewById(R.id.viewCardReport);

        }
    }
}