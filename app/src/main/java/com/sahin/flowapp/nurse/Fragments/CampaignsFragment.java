package com.sahin.flowapp.nurse.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sahin.flowapp.nurse.Adapters.CampaignsAdapter;
import com.sahin.flowapp.nurse.Models.CampaignsModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.ChangeFragments;
import com.sahin.flowapp.nurse.Utils.Warning;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignsFragment extends Fragment {

    private View view;
    private RecyclerView campaigns_recyclerview;
    private ChangeFragments changeFragments;
    private CampaignsAdapter campaignsAdapter;
    private List<CampaignsModel> campaignsModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_campaigns, container, false);
        defineLayout();
        getCampaigns();
        return view;
    }

    public void defineLayout()
    {
        campaigns_recyclerview = (RecyclerView)view.findViewById(R.id.campaigns_recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        campaigns_recyclerview.setLayoutManager(layoutManager);
        changeFragments = new ChangeFragments(getContext());
        campaignsModels = new ArrayList<>();
    }

    public void getCampaigns()
    {
        final Call<List<CampaignsModel>> req = ManagerAll.getInstance().getCampaigns();
        req.enqueue(new Callback<List<CampaignsModel>>() {
            @Override
            public void onResponse(Call<List<CampaignsModel>> call, Response<List<CampaignsModel>> response) {
                if(response.body().get(0).isTf())
                {
                    campaignsModels = response.body();
                    campaignsAdapter = new CampaignsAdapter(campaignsModels,getContext());
                    campaigns_recyclerview.setAdapter(campaignsAdapter);
                }
                else
                {
                    Toast.makeText(getContext(),"There are no campaigns.",Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<CampaignsModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

}
