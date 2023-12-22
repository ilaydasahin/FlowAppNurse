package com.sahin.flowapp.nurse.RestApi;

import com.sahin.flowapp.nurse.Models.AnswerModel;
import com.sahin.flowapp.nurse.Models.AskQuestionModel;
import com.sahin.flowapp.nurse.Models.DuyuruModel;
import com.sahin.flowapp.nurse.Models.DeleteAnswerModel;
import com.sahin.flowapp.nurse.Models.HasModel;
import com.sahin.flowapp.nurse.Models.LoginModel;
import com.sahin.flowapp.nurse.Models.RegisterPojo;
import com.sahin.flowapp.nurse.Models.VacModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterPojo> kayitOl(String mail , String kadi, String parola)
    {
        Call<RegisterPojo> x = getRestApi().registerUser(mail,kadi,parola);
        return  x ;
    }

    public Call<LoginModel> girisYap(String mail , String parola)
    {
        Call<LoginModel> x = getRestApi().loginUser(mail,parola);
        return  x ;
    }

    public Call<List<HasModel>> getHasta(String id)
    {
        Call<List<HasModel>> x = getRestApi().getHasta(id);
        return  x ;
    }

    public Call<AskQuestionModel> soruSor(String id , String soru)
    {
        Call<AskQuestionModel> x = getRestApi().soruSor(id,soru);
        return  x ;
    }

    public Call<List<AnswerModel>> getAnswers(String id)
    {
        Call<List<AnswerModel>> x = getRestApi().getAnswers(id);
        return  x ;
    }

    public Call<List<DuyuruModel>> getDuyuru()
    {
        Call<List<DuyuruModel>> x = getRestApi().getDuyuru();
        return  x ;
    }

    public Call<DeleteAnswerModel> deleteAnswer(String cevap , String soru)
    {
        Call<DeleteAnswerModel> x = getRestApi().deleteAnswer(cevap,soru);
        return  x ;
    }

    public Call<List<VacModel>> getVac(String custid)
    {
        Call<List<VacModel>> x = getRestApi().getVac(custid);
        return  x ;
    }

    public Call<List<VacModel>> getPastVac(String custid,String petid)
    {
        Call<List<VacModel>> x = getRestApi().getPastVac(custid,petid);
        return  x ;
    }
}
