package com.holo.support.mvp.model;

import com.holo.support.bean.Data;
import com.holo.support.bean.DrugsBean;
import com.holo.support.bean.DrugsRightBean;
import com.holo.support.bean.HomepagerBanner;
import com.holo.support.bean.HomepagerHealthzxBean;
import com.holo.support.bean.HomepagerWzzxBean;
import com.holo.support.bean.LoginBean;
import com.holo.support.bean.SymptomsrightBean;
import com.holo.support.bean.WardmateListBean;
import com.holo.support.bean.WardmateTopBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyAPI  {
    //banner
    @GET("share/v1/bannersShow")
    Observable<Data<List<HomepagerBanner>>> bannershow();
    //login
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Data<LoginBean>> getlogin(@Field("email") String email,
                                         @Field("pwd") String pwd);
    //问诊咨询  //常见病症
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Data<List<HomepagerWzzxBean>>> wzzxshow();
    //健康咨询
    @GET("share/information/v1/findInformationList")
    Observable<Data<List<HomepagerHealthzxBean>>> healthzxshow(@Query("plateId") int plateId,
                                                               @Query("page") int page,
                                                               @Query("count") int count);
    //科室
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Data<List<WardmateTopBean>>> wardmatetop();
    //列表详情
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<Data<List<WardmateListBean>>> wardmatelist(@Query("departmentId") int departmentId,
                                                          @Query("page") int page,
                                                          @Query("count") int count);

    //常见病症右
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Data<List<SymptomsrightBean>>> symptomsright(@Query("departmentId") int departmentId);
    //常见药品
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Data<List<DrugsBean>>> Drugsleft();
    //常见药品右
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Data<List<DrugsRightBean>>> DrugsRight(@Query("drugsCategoryId") int drugsCategoryId,
                                                      @Query("page") int page,
                                                      @Query("count") int count);
}
