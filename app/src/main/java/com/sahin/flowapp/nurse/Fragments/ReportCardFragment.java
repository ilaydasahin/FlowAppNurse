package com.sahin.flowapp.nurse.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Adapters.ReportCardAdapter;
import com.sahin.flowapp.nurse.Models.HasModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.ChangeFragments;
import com.sahin.flowapp.nurse.Utils.GetSharedPreferences;
import com.sahin.flowapp.nurse.Utils.Warning;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportCardFragment extends Fragment {

    private RecyclerView reportCardRecyclerView;
    private View view;
    private ReportCardAdapter reportCardAdapter;
    private List<HasModel> petList;
    private ChangeFragments changeFragments;
    private String cust_id;
    private GetSharedPreferences getSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report_card, container, false);
        defineLayout();
        getPets(cust_id);

        return view;
    }

    public void defineLayout()
    {
        petList = new ArrayList<>();
        reportCardRecyclerView = view.findViewById(R.id.reportcardrecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        reportCardRecyclerView.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        cust_id = getSharedPreferences.getSession().getString("cust_id",null);
    }

    public void getPets(String cust_id)
    {
        Call<List<HasModel>> req = ManagerAll.getInstance().getHasta(cust_id);
        req.enqueue(new Callback<List<HasModel>>() {
            @Override
            public void onResponse(Call<List<HasModel>> call, Response<List<HasModel>> response) {
                if(response.body().get(0).isTf())
                {
                    petList = response.body();
                    reportCardAdapter = new ReportCardAdapter(petList,getContext());
                    reportCardRecyclerView.setAdapter(reportCardAdapter);
                }
                else
                {
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<HasModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }
}
