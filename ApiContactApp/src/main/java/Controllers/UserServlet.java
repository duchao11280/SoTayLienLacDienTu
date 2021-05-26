package Controllers;

import Beans.*;
import Models.UserModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/User/*")
public class UserServlet extends HttpServlet {
    //region doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if(path == null){
            path = "index";
        }
        switch (path){
            case "/AddNewFee":
                doAddNewFee(request,response);
                break;
            case "/AddNewAnnouncement":
                doAddNewAnnouncement(request,response);
                break;
            default:
                break;
        }
    }

    public void doAddNewAnnouncement(HttpServletRequest request,HttpServletResponse response){

    }

    private void doAddNewFee(HttpServletRequest request, HttpServletResponse response){
        String money = request.getParameter("money");
        UserModel.addNewFee(money);
    }
    //endregion

    //region doGet
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String path = request.getPathInfo();
        if(path == null){
            path = "index";
        }

        switch (path){
            case "/GetStudent":
                doGetStudent(request,response);
                break;
            case "/GetParent":
                doGetParent(request,response);
                break;
            case "/GetAdmin":
                doGetAdmin(request,response);
                break;
            case "/GetAnnouncementStudent":
                doGetAnnouncementStudent(request,response);
                break;
            case "/GetAnnouncementParent":
                doGetAnnouncementParent(request,response);
                break;
            case "/GetDetailAnnounStudent":
                doGetDetailAnnounByStudentID(request,response);
                break;
            case "/GetDetailAnnounParent":
                doGetDetailAnnounByParentID(request,response);
                break;
            case "/GetAllAnnouncement":
                doGetAllAnnouncement(request,response);
                break;
            case "/GetAllSubjectclassID":
                doGetAllSubjectclassID(request,response);
                break;
            case "/GetAllFee":
                doGetAllFee(request,response);
                break;
            case "/GetAllSubclassByStudentID":
                doGetAllSubclassByStudentID(request,response);
                break;
            case "/GetDetailSubClass":
                doGetDetailSubClass(request,response);
                break;
            case "/GetListStudent":
                doGetListStudentBySubjectID(request,response);
                break;
            default:
                break;
        }
    }

    private void doGetListStudentBySubjectID(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String subjectID = request.getParameter("subjectID");
        String apiRespont="";
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Student> lstStudent = UserModel.getListStudentBySubjectID(subjectID);
        apiRespont = new Gson().toJson(lstStudent);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetDetailSubClass(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String apiRespont="";
        String studentID = request.getParameter("studentID");
        String subjectID = request.getParameter("subjectID");
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Subjectofstudent> lstSub = UserModel.getDetailsSubjectclass(studentID,subjectID);
        if(lstSub.size()>0) {
            apiRespont = new Gson().toJson(lstSub.get(0));
        }
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetAllSubclassByStudentID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiRespont="";
        String id = request.getParameter("studentID");
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Subjectofstudent> lstSub = UserModel.getAllSubclassByStudentID(id);
        if(lstSub.size()>0) {
            apiRespont = new Gson().toJson(lstSub);
        }
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetAllFee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiRespont="";
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Fee> lstFee = UserModel.getAllFee();
        apiRespont = new Gson().toJson(lstFee);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetAllSubjectclassID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiRespont="";
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Subjectclass> listSubclass = UserModel.getAllSubjectclass();
        if(listSubclass.size()>0){
            apiRespont = new Gson().toJson(listSubclass);
        }
        out2cal.println(apiRespont);
        out2cal.flush();

    }

    private void doGetAllAnnouncement(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String apiRespont="";
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        List<Announcement> lstAnnoun = UserModel.getAllAnnouncement();
        if(lstAnnoun.size()>0){
            apiRespont = new Gson().toJson(lstAnnoun);
        }
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiRespont="";
        String ID =request.getParameter("id");
        System.out.println(ID);
        List<Student> lstStudent = UserModel.getStudentByID(ID);
        PrintWriter out2cal = response.getWriter();

        if(lstStudent.size()>0) {
            apiRespont = new Gson().toJson(lstStudent.get(0));
        }
        System.out.println(apiRespont);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetParent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiRespont="";
        String ID =request.getParameter("id");
        System.out.println(ID);
        List<Parent> lstParent = UserModel.getParentByparentID(ID);
        PrintWriter out2cal = response.getWriter();

        if(lstParent.size()>0) {
            apiRespont = new Gson().toJson(lstParent.get(0));
        }
        System.out.println(apiRespont);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(apiRespont);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiRespont="";
        String ID =request.getParameter("id");
        List<Admin> lstAdmin = UserModel.getAdminByAdminID(ID);
        PrintWriter out2cal = response.getWriter();

        if(lstAdmin.size()>0) {
            apiRespont = new Gson().toJson(lstAdmin.get(0));
        }
        System.out.println(apiRespont);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(apiRespont);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetAnnouncementStudent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String apiRespont="";
        String ID =request.getParameter("id");
        List<Announcement> lstAnnoun = UserModel.getAllAnnounByStudentID(ID);
        if(lstAnnoun.size()>0){
            apiRespont = new Gson().toJson(lstAnnoun);
        }
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(apiRespont);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetAnnouncementParent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String apiRespont="";
        String ID =request.getParameter("id");
        List<Announcement> lstAnnoun = UserModel.getAllAnnounByParentID(ID);
        if(lstAnnoun.size()>0){
            apiRespont = new Gson().toJson(lstAnnoun);
        }
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(apiRespont);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    private void doGetDetailAnnounByStudentID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uID = request.getParameter("studentID");
        String aID = request.getParameter("announID");
        System.out.println("out: "+uID + " --- "+aID);
        String apiRespont="";
        List<Announcement> lstAnnoun = UserModel.getDetailAnnounByStudentID(uID,aID);
        if(lstAnnoun.size()>0){
            apiRespont = new Gson().toJson(lstAnnoun.get(0));
        }

        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(apiRespont);
        out2cal.println(apiRespont);
        out2cal.flush();

    }

    private void doGetDetailAnnounByParentID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uID = request.getParameter("parentID");
        String aID = request.getParameter("announID");
        String apiRespont="";
        List<Announcement> lstAnnoun = UserModel.getDetailAnnounByParentID(uID,aID);
        if(lstAnnoun.size()>0){
            apiRespont = new Gson().toJson(lstAnnoun.get(0));
        }
        PrintWriter out2cal = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(apiRespont);
        out2cal.println(apiRespont);
        out2cal.flush();
    }

    //endregion

    //region doPut
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if(path == null){
            path = "index";
        }
        switch (path){
            case "/MarkReadedStudent":
                doMarkReadedStudent(request,response);
                break;
            case "/MarkReadedParent":
                doMarkReadedParent(request,response);
                break;
            case "/MarkReadedAllStudent":
                doMardReadedAllStudent(request,response);
                break;
            case "/MarkReadedAllParent":
                doMardReadedAllParent(request,response);
                break;
            case "/UpdateIsPaid":
                doUpdateIsPaid(request,response);
                break;
            case "/UpdateInfoStudent":
                doUpdateInfoStudent(request,response);
                break;
            default:
                break;
        }
    }

    private void doUpdateInfoStudent(HttpServletRequest request, HttpServletResponse response){
        String studentID = request.getParameter("studentID");
        String phonenumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        System.out.println(studentID);
        System.out.println(phonenumber);
        System.out.println(address);
        UserModel.updateInfor(studentID,phonenumber,address);
    }

    private void doUpdateIsPaid(HttpServletRequest request,HttpServletResponse response){
        String uID = request.getParameter("studentID");
        String sID = request.getParameter("subjectID");
        System.out.println(uID + sID);
        UserModel.updateIsPaidByStudentID(uID,sID);
    }

    private void doMarkReadedStudent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uID = request.getParameter("studentID");
        String aID = request.getParameter("announID");
        UserModel.markReadedAnnounStudent(uID,aID);
    }

    private void doMarkReadedParent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uID = request.getParameter("parentID");
        String aID = request.getParameter("announID");
        UserModel.markReadedAnnounParent(uID,aID);
    }

    private void doMardReadedAllStudent(HttpServletRequest request, HttpServletResponse response){
        String studentID = request.getParameter("studentID");
        UserModel.markReadedAllStudent(studentID);
    }

    private void doMardReadedAllParent(HttpServletRequest request, HttpServletResponse response){
        String parentID = request.getParameter("parentID");
        UserModel.markReadedAllParent(parentID);
    }

    //endregion

    //region doDelete

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if(path==null){
            path="index";
        }
        switch (path){
            case "/DeleteAllAnnounStudent":
                deleteAllAnnounStudent(request,response);
                break;
            case "/DeleteAllAnnounParent":
                deleteAllAnnounParent(request,response);
                break;
            default:
                break;
        }
    }
    private void deleteAllAnnounStudent(HttpServletRequest request, HttpServletResponse response){
        String uID = request.getParameter("studentID");
        UserModel.deleteAllAnnounByStudentID(uID);
    }

    private void deleteAllAnnounParent(HttpServletRequest request,HttpServletResponse response){
        String uID = request.getParameter("parentID");
        UserModel.deleteAllAnnounByParentID(uID);
    }

    //endregion
}
