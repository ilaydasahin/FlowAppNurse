package com.sahin.flowapp.nurse.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sahin.flowapp.nurse.Adapters.SanalKarneGecmisIslemAdapter;
import com.sahin.flowapp.nurse.Models.IslemModel;
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


public class IslemDetailFragment extends Fragment {

    private View view;
    private String hemid;
    private String hasId;
    private GetSharedPreferences getSharedPreferences;
    private RecyclerView islemDetayRecView;
    private SanalKarneGecmisIslemAdapter adapter;
    private List<IslemModel> list;
    private ChangeFragments changeFragments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_islem_detay, container, false);
        tanimla();
        getGecmisIslem();
        return view;
    }

    public void tanimla() {

        hasId = getArguments().getString("hasid");
        getSharedPreferences = new GetSharedPreferences(getActivity());
        hemid = getSharedPreferences.getSession().getString("id", null);
        islemDetayRecView = view.findViewById(R.id.islemDetayRecView);
        islemDetayRecView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        list = new ArrayList<>();

        changeFragments = new ChangeFragments(getContext());


    }

    public void getGecmisIslem() {
//id,pet_id
        Call<List<IslemModel>> req = ManagerAll.getInstance().getGecmisIslem(hemid, hasId);
        req.enqueue(new Callback<List<IslemModel>>() {
            @Override
            public void onResponse(Call<List<IslemModel>> call, Response<List<IslemModel>> response) {
                // Log.i("gecmisasilar",response.body().toString());
                if (response.body().get(0).isTf()) {

                    list = response.body();
                    adapter=new SanalKarneGecmisIslemAdapter(list,getContext());
                    islemDetayRecView.setAdapter(adapter);
                    Toast.makeText(getContext(), "Hastanıza ait " + list.size() + " adet geçmişte yapılan işlem bulunmaktadır.", Toast.LENGTH_LONG).show();
                } else {
                  //ChangeFragments changeFragments=new ChangeFragments(getContext());
                    changeFragments.change(new SanalKarnePatientFragment());
                    Toast.makeText(getContext(), "Hastanıza ait geçmişte yapılan herhangi bir işlem bulunmamaktadır.", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<IslemModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warning.internetProblemText, Toast.LENGTH_LONG).show();
            }
        });
    }

}