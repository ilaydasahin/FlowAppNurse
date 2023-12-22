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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/flowservis/kayitol.php")
    Call<RegisterPojo> registerUser(@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/flowservis/girisyap.php")
    Call<LoginModel> loginUser(@Field("mailadres") String mailAdres, @Field("sifre") String pass);

    @FormUrlEncoded
    @POST("/flowservis/hastalarim.php")
    Call<List<HasModel>> getHasta(@Field("hemid") String hem_id);


    @FormUrlEncoded
    @POST("/flowservis/sorusor.php")
    Call<AskQuestionModel> soruSor(@Field("id") String id, @Field("soru") String soru);

    @FormUrlEncoded
    @POST("/flowservis/cevapsil.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("cevap") String cevapid, @Field("soru") String soruid);

    @FormUrlEncoded
    @POST("/flowservis/cevaplar.php")
    Call<List<AnswerModel>> getAnswers(@Field("hem_id") String hem_id);

    @GET("/flowservis/duyuru.php")
    Call<List<DuyuruModel>> getDuyuru();

    @FormUrlEncoded
    @POST("/flowservis/vaccalendar.php")
    Call<List<VacModel>> getVac(@Field("custid") String custid);

    @FormUrlEncoded
    @POST("/flowservis/pastvac.php")
    Call<List<VacModel>> getPastVac(@Field("custid") String custid,@Field("petid") String petid);
}
