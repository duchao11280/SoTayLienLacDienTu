package com.example.lopsotaylienlac.apis;

import com.example.lopsotaylienlac.beans.Admin;
import com.example.lopsotaylienlac.beans.Announcement;
import com.example.lopsotaylienlac.beans.Fee;
import com.example.lopsotaylienlac.beans.Parent;
import com.example.lopsotaylienlac.beans.Schedule;
import com.example.lopsotaylienlac.beans.Student;
import com.example.lopsotaylienlac.beans.Subjectclass;
import com.example.lopsotaylienlac.beans.Subjectofstudent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserApi {
    Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();

    UserApi apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.1:8080/ApiContactApp/")
            .client(Client.getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserApi.class);

    //region Get
    //Lấy 1 sinh viên
    @GET("User/GetStudent")
    Call<Student> getStudentByID(@Query("id") String id);

    //Lấy 1 học sinh
    @GET("User/GetParent")
    Call<Parent> getParentByParentID(@Query("id") String id);

    //Lấy 1 admin
    @GET("User/GetAdmin")
    Call<Admin> getAdminByAdminID(@Query("id") String id);

    //Lấy danh sách thông báo của 1 phụ huynh
    @GET("User/GetAnnouncementParent")
    Call<ArrayList<Announcement>> getAnnounByParentID(@Query("id") int id);

    //Lấy danh sách thông báo của 1 sinh viên
    @GET("User/GetAnnouncementStudent")
    Call<ArrayList<Announcement>> getAnnounByStudentID(@Query("id") int id);

    //Xem chi tiết 1 thông báo cho học sinh
    @GET("User/GetDetailAnnounStudent")
    Call<Announcement> getDetailAnnounByStudentID(
            @Query("studentID") int studentID,
            @Query("announID") int announID);

    //Xem chi tiết 1 thông báo cho phụ huynh
    @GET("User/GetDetailAnnounParent")
    Call<Announcement> getDetailAnnounByParentID(
            @Query("parentID") int parentID,
            @Query("announID") int announID);

    //Lấy toàn bộ thông báo
    @GET("User/GetAllAnnouncement")
    Call<ArrayList<Announcement>> getAllAnnouncement();

    //Lấy toàn bộ lớp (SubjectClass)
    @GET("User/GetAllSubjectclassID")
    Call<ArrayList<Subjectclass>> getAllSubjectclass();

    //Lay toan bo mon hoc cua 1 sinnh vien
    @GET("User/GetAllSubclassByStudentID")
    Call<ArrayList<Subjectofstudent>> getAllSubclassByStudentID(@Query("studentID") int studentID);

    //Lay toan bo mon hoc cua con phu huynh
    @GET("User/GetAllSubclassByParentID")
    Call<ArrayList<Subjectofstudent>> getAllSubclassByParentID(@Query("parentID") int parentID);

    //Xem chi tiết lớp học của 1 sinh viên (điểm, học phí,...)
    @GET("User/GetDetailSubClass")
    Call<Subjectofstudent> getDetailsSubjectclass(
            @Query("studentID") int studentID,
            @Query("subjectID") String subjectID);
    @GET("User/GetSubjetclassBySubjectID")
    Call<ArrayList<Subjectclass>> getSubjectclassBySubjectID(@Query("subjectID") String subjectID);
    //Xem danh sách sinh viên có trong 1 lớp
    @GET("User/GetListStudent")
    Call<ArrayList<Student>> getListUserBySubjectID(@Query("subjectID") String subjectID);

    //Lấy toàn bộ thông tin về học phí qua các năm
    @GET("User/GetAllFee")
    Call<ArrayList<Fee>> getAllFee();

    @GET("User/GetScheduleByStudentIdandDate")
    Call<ArrayList<Schedule>> getScheduleByStudentIdandDate(@Query("studentID")int id,
                                                            @Query("dtpk")String dtpk);
    @GET("User/GetStudentIdByParentId")
    Call<Integer> getStudentIdByParentId(@Query("parentID") int id);

    //endregion

    //region Post
    //Thêm học phí mới
    @POST("User/AddNewFee")
    Call<Void> addNewFee(@Query("money") int money);

    //LINK: http://localhost:8080/ApiContactApp/User/AddNewAnnouncement?title=tbPHLopDBMS_01CLC&sender=LDT&content=phụhuynhcóconemhọclớpB&role=2&receiver=All
    @POST("User/AddNewAnnouncement")
    Call<Void> addNewAnnouncement(
            @Query("title") String title,//Tiêu đề
            @Query("sender") String sender,//Tên người gửi
            @Query("content") String content,// nội dung thông báo
            @Query("role") int role,//role = 1 là gửi cho học sinh, role != 1 là gửi cho phụ huynh
            @Query("receiver") String receiver //Mã lớp nhận thông báo, receiver=All để gửi toàn bộ
    );

    //endregion

    //region Put
    //Đánh dấu đã đọc 1 thông báo cho sinh viên
    @PUT("User/MarkReadedStudent")
    Call<Void> markReadedStudent(@Query("studentID") int studentID, @Query("announID") int announID);

    //Đánh dấu đã đọc 1 thông báo cho PH
    @PUT("User/MarkReadedParent")
    Call<Void> markReadedParent(@Query("parentID") int parentID, @Query("announID") int announID);

    //Đánh dấu đã đọc cho toàn bộ thông báo của sinh viên
    @PUT("User/MarkReadedAllStudent")
    Call<Void> markAllAnnounReaderByStudentID(@Query("studentID") int studentID);

    //Đánh dấu đã đọc cho toàn bộ thông báo của phụ huynh
    @PUT("User/MarkReadedAllParent")
    Call<Void> markAllAnnounReaderByParentID(@Query("parentID") int parentID);

    //Đánh dấu đã đóng/chưa đóng học phí
    @PUT("User/UpdateIsPaid")
    Call<Void> updateIsPaid(@Query("studentID") int studentID,
                            @Query("subjectID") String subjectID);

    //Cập nhật thông tin cho học sinh
    @PUT("User/UpdateInfoStudent")
    Call<Void> updateInfo(
            @Query("studentID") String studentID,
            @Query("phonenumber") String phonenumber,
            @Query("address") String address
    );
    // cập nhật điểm cho học sinh
    @PUT("User/UpdateGrades")
    Call<Void> updateGrades(@Query("studentID") int studentID,
                            @Query("subjectID") String subjectID,
                            @Query("midGrades") float midGrades,
                            @Query("finalGrades") float finalGrades);
    //endregion

    //region Delete
    //Xóa tất cả thông báo của 1 sinh viên
    @DELETE("User/DeleteAllAnnounStudent")
    Call<Void> deleteAllAnnounByStudentID(@Query("studentID") int studentID);

    //Xóa tất cả thông báo của 1 phụ huynh
    @DELETE("User/DeleteAllAnnounParent")
    Call<Void> deleteAllAnnounByParentID(@Query("parentID") int parentID);
    //endregion
}
