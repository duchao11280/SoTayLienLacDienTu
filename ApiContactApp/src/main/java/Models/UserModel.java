package Models;

import Beans.*;
import Utilties.DBUtils;
import org.sql2o.Connection;

import java.util.Date;
import java.util.List;

public class UserModel {
    public static List<Student> getStudentByID(String ID){
        final String sql = "Select * from student where studentID=:studentID";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("studentID",ID).executeAndFetch(Student.class);
        }
    }

    public static List<Parent> getParentByparentID(String ID){
        final String sql = "Select * from parent where parentID=:parentID";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("parentID",ID).executeAndFetch(Parent.class);
        }
    }
    public static List<Admin> getAdminByAdminID(String ID){
        final String sql = "Select * from admin where adminID=:adminID";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("adminID",ID).executeAndFetch(Admin.class);
        }
    }

    public static List<Announcement> getAllAnnounByStudentID(String ID){
        final String sql = "call sp_getAllAnnounByStudentID(:studentID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("studentID",ID).executeAndFetch(Announcement.class);
        }
    }

    public static List<Announcement> getAllAnnounByParentID(String ID){
        final String sql = "call sp_getAllAnnounByParentID(:parentID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("parentID",ID).executeAndFetch(Announcement.class);
        }
    }

    public static void markReadedAnnounStudent(String uID, String aID){
        final String sql = "Update student_announcement set isRead = true where studentID=:studentID and announID=:announID";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("studentID",uID)
                    .addParameter("announID",aID)
                    .executeUpdate();
        }
    }
    public static void markReadedAnnounParent(String uID, String aID){
        final String sql = "Update parent_announcement set isRead = true where parentID=:parentID and announID=:announID";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("parentID",uID)
                    .addParameter("announID",aID)
                    .executeUpdate();
        }
    }

    public static void markReadedAllStudent(String studentID){
        final String sql = "Update student_announcement set isRead = true where studentID=:studentID ";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .executeUpdate();
        }
    }

    public static void markReadedAllParent(String parentID){
        final String sql = "Update parent_announcement set isRead = true where parentID=:parentID ";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("parentID",parentID)
                    .executeUpdate();
        }
    }

    public static List<Announcement> getDetailAnnounByStudentID(String studentID, String announID){
        final String sql = "call sp_getDetailAnnounByStudentID(:studentID,:announID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .addParameter("announID",announID)
                    .executeAndFetch(Announcement.class);
        }
    }

    public static List<Announcement> getDetailAnnounByParentID(String parentID, String announID){
        final String sql = "call sp_getDetailAnnounByParentID(:parentID,:announID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("parentID",parentID)
                    .addParameter("announID",announID)
                    .executeAndFetch(Announcement.class);
        }
    }

    public static void deleteAllAnnounByStudentID(String studentID){
        final String sql = "Delete from student_announcement where studentID = :studentID";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .executeUpdate();
        }
    }

    public static void deleteAllAnnounByParentID(String parentID){
        final String sql = "Delete from parent_announcement where parentID = :parentID";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("parentID",parentID)
                    .executeUpdate();
        }
    }

    public static List<Announcement> getAllAnnouncement(){
        final String sql = "select * from announcement";
        try(Connection con = DBUtils.getConnection()) {
            return  con.createQuery(sql).executeAndFetch(Announcement.class);
        }
    }

    public static List<Subjectclass> getAllSubjectclass(){
        final String sql = "select * from subjectclass ";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql).executeAndFetch(Subjectclass.class);
        }
    }

    public static List<Fee> getAllFee(){
        final String sql = "select * from fee";
        try (Connection con = DBUtils.getConnection()){
            return con.createQuery(sql).executeAndFetch(Fee.class);
        }
    }

    public static List<Subjectofstudent> getAllSubclassByStudentID(String studentID){
        final String sql = "call sp_getAllSubjectByStudentID(:studentID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .executeAndFetch(Subjectofstudent.class);
        }
    }

    public static void updateIsPaidByStudentID(String studentID, String subjectID){
        final String sql ="call sp_UpdatePay(:studentID,:subjectID)";
        try (Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .addParameter("subjectID",subjectID)
                    .executeUpdate();

        }
    }
    public static void updateIsOffByTimetableID(int timetableID){
        final String sql ="call sp_UpdateOff(:timetableID)";
        try (Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("timetableID",timetableID)
                    .executeUpdate();
        }
    }
    public static List<Subjectclass> getSubjetclassBySubjectID(String sbid){
        final String sql = "SELECT * FROM subjectclass WHERE subjectID = :sbid";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("sbid",sbid)
                    .executeAndFetch(Subjectclass.class);
        }
    }

    public static void addNewFee(String money){
        final String sql = "call sp_addNewFee(:money)";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("money",money)
                    .executeUpdate();
        }
    }

    public static List<Subjectofstudent> getDetailsSubjectclass(String studentID, String subjectID){
        final String sql = "call sp_getDetailSubclass(:studentID,:subjectID)";
        try (Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .addParameter("subjectID",subjectID)
                    .executeAndFetch(Subjectofstudent.class);
        }
    }

    public static List<Student> getListStudentBySubjectID(String subjectID){
        final String sql = "call sp_getAllStudentBySubjectID(:subjectID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("subjectID",subjectID)
                    .executeAndFetch(Student.class);
        }
    }

    public static void updateInfor(String studentID, String phonenumber, String address){
        final String sql = "update student set phonenumber=:phonenumber,address=:address where studentID=:studentID";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("phonenumber",phonenumber)
                    .addParameter("address",address)
                    .addParameter("studentID",studentID)
                    .executeUpdate();
        }
    }
    public static void updateGrades(int studentID, String subjectID, float midGrades, float finalGrades){
        final String sql = "Update study set scoreMidTerm=:midGrades,scoreFinalTerm = :finalGrades  where studentID = :studentID and subjectID = :subjectID";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("midGrades",midGrades)
                    .addParameter("finalGrades",finalGrades)
                    .addParameter("studentID",studentID)
                    .addParameter("subjectID",subjectID)
                    .executeUpdate();
        }
    }
    public static List<Subjectofstudent> getAllSubclassByParentIDofStudentID(String parentID){
        final String sql = "call sp_getAllSubjectByParentOfStudentID(:parentID)";
        try(Connection con = DBUtils.getConnection()){
            return con.createQuery(sql)
                    .addParameter("parentID",parentID)
                    .executeAndFetch(Subjectofstudent.class);
        }
    }

    public static void addNewAnnouncement(int announID,String title,String content,String sender,String receiver){
        final String sql = "INSERT INTO announcement VALUES(:announID,:title,:content,:sender,NOW(),:receiver)";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("announID",announID)
                    .addParameter("title",title)
                    .addParameter("content",content)
                    .addParameter("sender",sender)
                    .addParameter("receiver",receiver)
                    .executeUpdate();
        }
    }

    public static List<String> getAllParentID(){
        final String sql = "select parentID from parent";
        try(Connection con = DBUtils.getConnection()) {
            return con.createQuery(sql).executeAndFetch(String.class);
        }
    }

    public static List<String> getAllStudentID(){
        final String sql = "select studentID from student";
        try(Connection con = DBUtils.getConnection()) {
            return con.createQuery(sql).executeAndFetch(String.class);
        }
    }

    public static void addStudentAnnoun(int announID,String studentID){
        final String sql = "insert into student_announcement values(:studentID,:announID,false)";
        try(Connection con = DBUtils.getConnection()){
            con.createQuery(sql)
                    .addParameter("studentID",studentID)
                    .addParameter("announID",announID)
                    .executeUpdate();
        }
    }

    public static void addParentAnnoun(int announID, String parentID){
        final String sql = "insert into parent_announcement values(:parentID,:announID,false)";
        try(Connection con = DBUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("parentID",parentID)
                    .addParameter("announID",announID)
                    .executeUpdate();
        }
    }
    public static List<Schedule> getScheduleByStudentIdandDate(int id, String dt) {
        final String sql = "call sp_getScheduleByStudentAndDate(:idd,:dt)";
        try (Connection con = DBUtils.getConnection()) {
            return con.createQuery(sql)
                    .addParameter("idd", id)
                    .addParameter("dt", dt)
                    .executeAndFetch(Schedule.class);
        }
    }
    public static int getStudentIdByParentID(int id) {
        final String sql = "SELECT studentID FROM student WHERE parentID =:id LIMIT 1";

        try (Connection con = DBUtils.getConnection()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(int.class);

        }
    }
    public static List<Timetable> getTimetableBySubjectId(String id){
        final String sql = "SELECT * FROM timetable Where subjectID=:id";

        try (Connection con = DBUtils.getConnection()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Timetable.class);

        }
    }

}
