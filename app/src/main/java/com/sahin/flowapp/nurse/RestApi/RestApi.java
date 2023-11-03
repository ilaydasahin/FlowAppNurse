package com.sahin.flowapp.nurse.RestApi;

import com.sahin.flowapp.nurse.Models.AnswerModel;
import com.sahin.flowapp.nurse.Models.AskQuestionModel;
import com.sahin.flowapp.nurse.Models.CampaignsModel;
import com.sahin.flowapp.nurse.Models.DeleteAnswerModel;
import com.sahin.flowapp.nurse.Models.LoginModel;
import com.sahin.flowapp.nurse.Models.PetModel;
import com.sahin.flowapp.nurse.Models.RegisterPojo;
import com.sahin.flowapp.nurse.Models.VacModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/flowservis/kayitol.php")
    Call<RegisterPojo> registerUser(@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/flowservis/login.php")
    Call<LoginModel> loginUser(@Field("email") String email, @Field("password") String username);

    @FormUrlEncoded
    @POST("/flowservis/mypets.php")
    Call<List<PetModel>> getPets(@Field("id") String id);

    @FormUrlEncoded
    @POST("/flowservis/askme.php")
    Call<AskQuestionModel> askQuestion(@Field("id") String id, @Field("question") String question);

    @FormUrlEncoded
    @POST("/flowservis/deleteanswer.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("answer") String answerid, @Field("question") String questionid);

    @FormUrlEncoded
    @POST("/flowservis/answer.php")
    Call<List<AnswerModel>> getAnswers(@Field("custid") String cust_id);

    @GET("/flowservis/campaigns.php")
    Call<List<CampaignsModel>> getCampaigns();

    @FormUrlEncoded
    @POST("/flowservis/vaccalendar.php")
    Call<List<VacModel>> getVac(@Field("custid") String custid);

    @FormUrlEncoded
    @POST("/flowservis/pastvac.php")
    Call<List<VacModel>> getPastVac(@Field("custid") String custid,@Field("petid") String petid);
}
