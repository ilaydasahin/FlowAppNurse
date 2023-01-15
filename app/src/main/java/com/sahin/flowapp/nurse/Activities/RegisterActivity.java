package com.sahin.flowapp.nurse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sahin.flowapp.nurse.Models.RegisterPojo;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.Warning;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RegisterActivity extends AppCompatActivity {

    private Button kayitOlButon;
    private EditText registerMailAdress, registerUserName, registerPassword;
    private TextView registerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tanimla();
        registerToUser();
        toLoginActivity();
    }

    public void tanimla(){
        kayitOlButon = (Button)findViewById(R.id.kayitOlButon);
        registerMailAdress = (EditText)findViewById(R.id.registerMailAdress);
        registerUserName = (EditText) findViewById(R.id.registerUserName);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerText = (TextView) findViewById(R.id.registerText);
    }

    public void registerToUser(){
        kayitOlButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = registerMailAdress.getText().toString();
                String userN = registerUserName.getText().toString();
                String pass = registerPassword.getText().toString();
                register(mail,userN,pass);
                delete();
            }
        });
    }

    public void toLoginActivity(){
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete()
    {
        registerMailAdress.setText("");
        registerUserName.setText("");
        registerPassword.setText("");
    }

    public void register(String userMailAdres, String userName, String userPass){
        Call<RegisterPojo> req = ManagerAll.getInstance().kayitOl(userMailAdres,userName,userPass);
        req.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {

                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }
}
