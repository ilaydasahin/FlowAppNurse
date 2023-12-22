package com.sahin.flowapp.nurse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sahin.flowapp.nurse.Models.LoginModel;
import com.sahin.flowapp.nurse.R;
import com.sahin.flowapp.nurse.RestApi.ManagerAll;
import com.sahin.flowapp.nurse.Utils.GetSharedPreferences;
import com.sahin.flowapp.nurse.Utils.Warning;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends AppCompatActivity {

    private EditText loginMailAdres,loginPassword;
    private TextView loginText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tanimla();
        click();
        delete();
    }

    public void tanimla()
    {
        loginMailAdres = (EditText)findViewById(R.id.loginMailAdres);
        loginPassword = (EditText)findViewById(R.id.loginPassword);
        loginText = (TextView)findViewById(R.id.loginText);
        loginButton = (Button)findViewById(R.id.loginButton);
    }

    public void click()
    {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = loginMailAdres.getText().toString();
                String pass = loginPassword.getText().toString();
                login(mail,pass);
                delete();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete()
    {
        loginMailAdres.setText("");
        loginPassword.setText("");
    }

    public void login(String mailAdres,String parola)
    {
        Call<LoginModel> req = ManagerAll.getInstance().girisYap(mailAdres,parola);

        req.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    GetSharedPreferences getSharedPreferences = new GetSharedPreferences(LoginActivity.this);
                    getSharedPreferences.setSession(response.body().getId(),response.body().getUsername(),response.body().getMailadres());
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

}
