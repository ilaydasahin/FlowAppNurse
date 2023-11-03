package com.sahin.flowapp.nurse.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sahin.flowapp.nurse.Adapters.PetAdapter;
import com.sahin.flowapp.nurse.Models.PetModel;
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

public class UserPetsFragment extends Fragment {

    public View view;
    private RecyclerView petlistrecyclerview;
    private PetAdapter petAdapter;
    private List<PetModel> petList;
    private ChangeFragments changeFragments;
    private String cust_id;
    private GetSharedPreferences getSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_user_pets, container, false);
        defineLayout();
        getPets(cust_id);

        return view;
    }

    public void defineLayout()
    {
        petList = new ArrayList<>();
        petlistrecyclerview = view.findViewById(R.id.petlistrecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        petlistrecyclerview.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        cust_id = getSharedPreferences.getSession().getString("cust_id",null);
    }

    public void getPets(String cust_id)
    {
        Call<List<PetModel>> req = ManagerAll.getInstance().getPets(cust_id);
        req.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                if(response.body().get(0).isTf())
                {
                    petList = response.body();
                    petAdapter = new PetAdapter(petList,getContext());
                    petlistrecyclerview.setAdapter(petAdapter);
                    Toast.makeText(getContext(),"You have "+petList.size()+" registered pets in the system.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Your pet is not registered in the system.",Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }



}
