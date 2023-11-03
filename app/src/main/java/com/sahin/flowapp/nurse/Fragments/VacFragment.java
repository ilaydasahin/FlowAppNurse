package com.sahin.flowapp.nurse.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.sahin.flowapp.nurse.Models.VacModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.ChangeFragments;
import com.sahin.flowapp.nurse.Utils.GetSharedPreferences;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VacFragment extends Fragment {

    private View view;
    private CalendarPickerView calendarPickerView;
    private DateFormat format;
    private Calendar nextYear;
    private Date today;
    private List<VacModel> vacModelList;
    private List<Date> dateList;
    private GetSharedPreferences getSharedPreferences;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_vac, container, false);
        defineLayout();
        getVac(id);
        clickToCalender();
        return view;
    }

    public void defineLayout()
    {
        calendarPickerView = view.findViewById(R.id.calendarPickerView);
        format = new SimpleDateFormat("dd/MM/yyyy");
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        today = new Date();
        calendarPickerView.init(today,nextYear.getTime());
        vacModelList = new ArrayList<>();
        dateList = new ArrayList<>();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("cust_id",null);
        System.out.println("fucking"+id);
    }

    public void getVac(String custid)
    {
        Call<List<VacModel>> req = ManagerAll.getInstance().getVac(custid);
        req.enqueue(new Callback<List<VacModel>>() {
            @Override
            public void onResponse(Call<List<VacModel>> call, Response<List<VacModel>> response) {
                if(response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        vacModelList = response.body();
                        for (int i = 0; i < vacModelList.size(); i++) {
                            String dataString = response.body().get(i).getVacdate().toString();
                            try {
                                Date date = format.parse(dataString);
                                dateList.add(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        calendarPickerView.init(today, nextYear.getTime())
                                .withHighlightedDates(dateList);
                    }
                }
                else
                {
                    ChangeFragments changeFragments = new ChangeFragments(getContext());
                    changeFragments.change(new HomeFragment());
                    Toast.makeText(getContext(),"There is no vaccine in the future of your pet...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<VacModel>> call, Throwable t) {

            }
        });
    }


    public void clickToCalender()
    {
        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                for(int i=0; i<dateList.size(); i++)
                {
                    if(date.toString().equals(dateList.get(i).toString()))
                    {
                        openQuestionAlert(vacModelList.get(i).getPetname().toString(),vacModelList.get(i).getVacdate().toString(),
                                vacModelList.get(i).getVacname().toString(),vacModelList.get(i).getPetimage().toString());
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    public void openQuestionAlert(String petName,String vacDate,String vacName,String petImage)
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alert_layout_vac,null);

        TextView petNameText = (TextView)view.findViewById(R.id.petNameText);
        TextView petInfoText = (TextView)view.findViewById(R.id.petInfoText);
        CircleImageView petCircleImageView = (CircleImageView)view.findViewById(R.id.petCircleImageView);

        petNameText.setText(petName);
        petInfoText.setText("Your pet named "+petName+" has a " +vacName +" vaccine on " +vacDate+".");
        Picasso.get().load(petImage).into(petCircleImageView);

        final EditText edittext_question = (EditText)view.findViewById(R.id.edittext_question);
        MaterialButton button_question = (MaterialButton)view.findViewById(R.id.button_question);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();


        alertDialog.show();
    }


}
