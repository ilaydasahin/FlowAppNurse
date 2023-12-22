package com.sahin.flowapp.nurse.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Adapters.PatientAdapter;
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

public class UserPatientFragment extends Fragment {

    public   View view;
    private RecyclerView patientlistrecyclerview;
    private PatientAdapter patientAdapter;
    private List<HasModel> patientList;
    private ChangeFragments changeFragments;
    private String hem_id;
    private GetSharedPreferences getSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_user_patient, container, false);
        tanimla();
        getHasta(hem_id);

        return view;
    }


    public void tanimla()
    {
        patientList = new ArrayList<>();
        patientlistrecyclerview = view.findViewById(R.id.patientlistrecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        patientlistrecyclerview.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        hem_id = getSharedPreferences.getSession().getString("id",null);
    }

    public void getHasta(String hem_id)
    {
        Call<List<HasModel>> req = ManagerAll.getInstance().getHasta(hem_id);
        req.enqueue(new Callback<List<HasModel>>() {
            @Override
            public void onResponse(Call<List<HasModel>> call, Response<List<HasModel>> response) {
                if(response.body().get(0).isTf())
                {
                    Log.i("liste",response.body().toString());
                    patientList = response.body();
                    patientAdapter = new PatientAdapter(patientList,getContext());
                    patientlistrecyclerview.setAdapter(patientAdapter);
                    Toast.makeText(getContext(),"You have "+ patientList.size()+" registered patient in the system.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Your patient is not registered in the system.",Toast.LENGTH_LONG).show();
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
