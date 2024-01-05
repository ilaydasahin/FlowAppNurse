package com.sahin.flowapp.nurse.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sahin.flowapp.nurse.Models.IslemModel;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IslemFragment extends Fragment {

    private View view;
    private CalendarPickerView calendarPickerView;
    private DateFormat format;
    private Calendar nextYear;
    private Date today;
    private List<IslemModel> islemList;
    private List<Date> dateList;
    private GetSharedPreferences getSharedPreferences;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_islem, container, false);
        tanimlama();
        getIslem(id);
        click_Calender();
        return view;
    }

    public void tanimlama()
    {
        calendarPickerView = view.findViewById(R.id.calendarPickerView);
        format = new SimpleDateFormat("dd/MM/yyyy");
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        today = new Date();
        calendarPickerView.init(today,nextYear.getTime());
        islemList = new ArrayList<>();
        dateList = new ArrayList<>();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("id",null);
        System.out.println("deneme"+id);
    }

    public void getIslem(String id)
    {
        Call<List<IslemModel>> req = ManagerAll.getInstance().getIslem(id);
        req.enqueue(new Callback<List<IslemModel>>() {
            @Override
            public void onResponse(Call<List<IslemModel>> call, Response<List<IslemModel>> response) {
                if(response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        islemList = response.body();
                        for (int i = 0; i < islemList.size(); i++) {
                            String dataString = response.body().get(i).getIslemtarih().toString();
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
                    Toast.makeText(getContext(),"There is no op. in the future of your patient...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<IslemModel>> call, Throwable t) {

            }
        });
    }


    public void click_Calender()
    {
        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                for(int i=0; i<dateList.size(); i++)
                {
                    if(date.toString().equals(dateList.get(i).toString()))
                    {
                        openQuestionAlert(islemList.get(i).getHasisim().toString(), islemList.get(i).getIslemtarih().toString(),
                                islemList.get(i).getIslemisim().toString(), islemList.get(i).getHasresim().toString());
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    public void openQuestionAlert(String patientName,String islemDate,String islemName,String patientImage)
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.islemtakiplayout,null);

        TextView patientIsimText = (TextView)view.findViewById(R.id.patientIsimText);
        TextView patientIslemTakipBilgiText = (TextView)view.findViewById(R.id.patientIslemTakipBilgiText);
         ImageView islemTakipImageView = (ImageView)view.findViewById(R.id.islemTakipImageView);

        patientIsimText.setText(patientName);
        patientIslemTakipBilgiText.setText("Your patinet named "+patientName+" has a " +islemName +" op. on " +islemDate+".");
        Picasso.get().load(patientImage).into(islemTakipImageView);

        //final EditText sorusoredittext = (EditText)view.findViewById(R.id.sorusoredittext);
        // MaterialButton sorusorlinearlayout = (MaterialButton)view.findVi
        // ewById(R.id.sorusorlinearlayout);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();


        alertDialog.show();
    }


}