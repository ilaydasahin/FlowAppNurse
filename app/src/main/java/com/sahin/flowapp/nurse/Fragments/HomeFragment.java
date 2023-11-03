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
    private Button button_pets,button_question,button_answer,button_campaigns,button_calendar,button_recortCard;
    private ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String cust_id;
    private AnswersAdapter answersAdapter;
    private List<AnswerModel> answerModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        defineLayout();
        click();
        return view;
    }

    public void defineLayout()
    {
        button_pets = (Button)view.findViewById(R.id.button_pets);
        button_question = (Button)view.findViewById(R.id.button_question);
        button_answer = (Button)view.findViewById(R.id.button_answer);
        button_campaigns=(Button)view.findViewById(R.id.button_campaigns);
        button_calendar = (Button)view.findViewById(R.id.button_calendar);
        button_recortCard = (Button)view.findViewById(R.id.button_recortCard);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        cust_id = getSharedPreferences.getSession().getString("cust_id",null);
        answerModels = new ArrayList<>();
    }

    public void click(){
        button_pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new UserPetsFragment());
            }
        });

        button_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionAlert();
            }
        });

        button_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAnswers(cust_id);
            }
        });

        button_campaigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new CampaignsFragment());
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
        View view = layoutInflater.inflate(R.layout.alert_layout_question,null);

        final EditText edittext_question = (EditText)view.findViewById(R.id.edittext_question);
        MaterialButton button_question = (MaterialButton)view.findViewById(R.id.button_question);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        button_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = edittext_question.getText().toString();
                edittext_question.setText("");
                alertDialog.cancel();
                askQuestion(cust_id,question,alertDialog);
            }
        });
        alertDialog.show();
    }

    public void askQuestion(String cust_id, String question,final AlertDialog alr)
    {
        Call<AskQuestionModel> req = ManagerAll.getInstance().askQuestion(cust_id,question);
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

    public void getAnswers(String cust_id)
    {
        Call<List<AnswerModel>> req = ManagerAll.getInstance().getAnswers(cust_id);
        req.enqueue(new Callback<List<AnswerModel>>() {
            @Override
            public void onResponse(Call<List<AnswerModel>> call, Response<List<AnswerModel>> response) {
                if(response.body().get(0).isTf())
                {
                    if(response.isSuccessful())
                    {
                        answerModels = response.body();
                        answersAdapter = new AnswersAdapter(answerModels,getContext());
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
        View view = layoutInflater.inflate(R.layout.alert_answer_item,null);

        RecyclerView answerRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_answer);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        answerRecyclerView.setLayoutManager(layoutManager);
        answerRecyclerView.setAdapter(answersAdapter);

        alertDialog.show();
    }
}
