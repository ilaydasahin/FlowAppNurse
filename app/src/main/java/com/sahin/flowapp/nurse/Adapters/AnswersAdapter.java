package com.sahin.flowapp.nurse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sahin.flowapp.nurse.Models.AnswerModel;
import com.sahin.flowapp.nurse.Models.DeleteAnswerModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.Warning;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

    List<AnswerModel> list;
    Context context;

    public AnswersAdapter(List<AnswerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cevapitemlayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {

        holder.cevapSoruText.setText("Question: "+ list.get(position).getSoru().toString());
        holder.cevapCevapText.setText("Answer: "+ list.get(position).getCevap().toString());

        holder.cevapSilbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteToService(list.get(position).getCevapid().toString(),list.get(position).getSoruid().toString(),position);
            }
        });
    }

    public void deleteToList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void deleteToService(String cevapid, String soruid, final int pos)
    {
        Call<DeleteAnswerModel> req = ManagerAll.getInstance().deleteAnswer(cevapid,soruid);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {
                if (response.body().isTf())
                {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(context,response.body().getText(),Toast.LENGTH_LONG).show();
                        deleteToList(pos);
                    }
                }
                else
                {
                    Toast.makeText(context,response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Toast.makeText(context,Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView cevapSoruText, cevapCevapText;
        MaterialButton cevapSilbuton;

        public ViewHolder(View itemView) {
            super(itemView);

            cevapSoruText = (TextView)itemView.findViewById(R.id.cevapSoruText);
            cevapCevapText = (TextView)itemView.findViewById(R.id.cevapCevapText);
            cevapSilbuton = (MaterialButton)itemView.findViewById(R.id.cevapSilbuton);
        }
    }
}
