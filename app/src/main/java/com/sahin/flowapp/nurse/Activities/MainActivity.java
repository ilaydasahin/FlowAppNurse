package com.sahin.flowapp.nurse.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sahin.flowapp.nurse.Fragments.HomeFragment;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.Utils.ChangeFragments;
import com.sahin.flowapp.nurse.Utils.GetSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    private ChangeFragments changeFragments;
    private BottomNavigationView bottomNavigationView;
    private ImageView aramaYapButon,anasayfaButon,cikisYap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        tanimla();
        kontrol();
        action();
    }



    private void getFragment() {
        changeFragments=new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }
    @Override
    public void onBackPressed() {
    }

    public  void tanimla(){

        preferences=new GetSharedPreferences(MainActivity.this);
        getSharedPreferences= preferences.getSession();
        anasayfaButon=findViewById(R.id.anasayfaButon);
        aramaYapButon=findViewById(R.id.aramaYapButon);
        cikisYap=findViewById(R.id.cikisYap);


    }

    public void action(){
        anasayfaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new HomeFragment());
            }
        });
        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   preferences.getsession().edit().clear().commit();//sharedpreferances temizleme böylede olur
                preferences.deleteToSession();
                Intent ıntent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(ıntent);
            }
        });
        aramaYapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent=new Intent(Intent.ACTION_VIEW);
                ıntent.setData(Uri.parse("tel:05313143770"));
                startActivity(ıntent);
            }
        });
    }

    public void kontrol()
    {

        if(getSharedPreferences.getString("id",null) == null && getSharedPreferences.getString("mailadres",null) == null &&
                getSharedPreferences.getString("username",null) == null)
        {

            Intent ıntent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(ıntent);
            finish();
        }
    }
}
