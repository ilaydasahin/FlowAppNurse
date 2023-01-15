package com.sahin.flowapp.nurse.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Adapters.DuyuruAdapter;
import com.sahin.flowapp.nurse.Models.DuyuruModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.ChangeFragments;
import com.sahin.flowapp.nurse.Utils.Warning;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuyuruFragment extends Fragment {

    private View view;
    private RecyclerView duyuruRecView;
    private ChangeFragments changeFragments;
    private DuyuruAdapter duyuruAdapter;
    private List<DuyuruModel> duyuruList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_duyuru, container, false);
        tanimla();
        getDuyuru();
        return view;
    }

    public void tanimla()
    {
        duyuruRecView = (RecyclerView)view.findViewById(R.id.duyuruRecView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        duyuruRecView.setLayoutManager(layoutManager);
        changeFragments = new ChangeFragments(getContext());
        duyuruList = new ArrayList<>();
    }

    public void getDuyuru()
    {
        final Call<List<DuyuruModel>> req = ManagerAll.getInstance().getDuyuru();
        req.enqueue(new Callback<List<DuyuruModel>>() {
            @Override
            public void onResponse(Call<List<DuyuruModel>> call, Response<List<DuyuruModel>> response) {
                if(response.body().get(0).isTf())
                {
                    duyuruList = response.body();
                    duyuruAdapter = new DuyuruAdapter(duyuruList,getContext());
                    duyuruRecView.setAdapter(duyuruAdapter);
                }
                else
                {
                    Toast.makeText(getContext(),"There are no announcement.",Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<DuyuruModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

}
