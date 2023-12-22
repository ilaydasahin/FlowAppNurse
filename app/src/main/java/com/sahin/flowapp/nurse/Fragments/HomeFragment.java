package com.sahin.flowapp.nurse.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sahin.flowapp.nurse.Adapters.AnswersAdapter;
import com.sahin.flowapp.nurse.Models.AnswerModel;
import com.sahin.flowapp.nurse.Models.AskQuestionModel;
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
public class HomeFragment extends Fragment {

    public View view;
    private Button button_patients,sorusorlinearlayout, cevapLayout, duyuruLinearLayout,button_calendar,button_recortCard;
    private ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String id;
    private AnswersAdapter answersAdapter;
    private List<AnswerModel> answersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        tanimla();
        action();
        return view;
    }

    public void tanimla()
    {
        button_patients = (Button)view.findViewById(R.id.button_patients);
        sorusorlinearlayout = (Button)view.findViewById(R.id.sorusorlinearlayout);
        cevapLayout = (Button)view.findViewById(R.id.cevapLayout);
        duyuruLinearLayout =(Button)view.findViewById(R.id.duyuruLinearLayout);
        button_calendar = (Button)view.findViewById(R.id.button_calendar);
        button_recortCard = (Button)view.findViewById(R.id.button_recortCard);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("id",null);
        answersList = new ArrayList<>();
    }

    public void action(){
        button_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new UserPatientFragment());
            }
        });

        sorusorlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionAlert();
            }
        });

        cevapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAnswers(id);
            }
        });

        duyuruLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new DuyuruFragment());
            }
        });

        button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new VacFragment());
            }
        });

        button_recortCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new ReportCardFragment());
            }
        });
    }

    public void openQuestionAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sorusoralertlayout,null);

        final EditText sorusoredittext = (EditText)view.findViewById(R.id.sorusoredittext);
        MaterialButton sorusorbuton = (MaterialButton)view.findViewById(R.id.sorusorbuton);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        sorusorbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soru = sorusoredittext.getText().toString();
                sorusoredittext.setText("");
                alertDialog.cancel();
                askQuestion(id,soru,alertDialog);
            }
        });
        alertDialog.show();
    }

    public void askQuestion(String hem_id, String text,final AlertDialog alr)
    {
        Call<AskQuestionModel> req = ManagerAll.getInstance().soruSor(hem_id,text);
        req.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    alr.cancel();
                }
                else
                {
                    Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AskQuestionModel> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAnswers(String hem_id)
    {
        Call<List<AnswerModel>> req = ManagerAll.getInstance().getAnswers(hem_id);
        req.enqueue(new Callback<List<AnswerModel>>() {
            @Override
            public void onResponse(Call<List<AnswerModel>> call, Response<List<AnswerModel>> response) {
                if(response.body().get(0).isTf())
                {
                    if(response.isSuccessful())
                    {

                        answersList = response.body();
                        answersAdapter = new AnswersAdapter(answersList,getContext());
                        openAnswerAlert();
                    }
                }
                else{
                    Toast.makeText(getContext(),"There is no answer...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AnswerModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openAnswerAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.cevapalertlayout,null);

        RecyclerView cevapRecylerView = (RecyclerView)view.findViewById(R.id.cevapRecylerView);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        cevapRecylerView.setLayoutManager(layoutManager);
        cevapRecylerView.setAdapter(answersAdapter);

        alertDialog.show();
    }
}
