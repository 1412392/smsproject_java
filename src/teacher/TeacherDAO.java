/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import Model.*;
import javax.swing.JOptionPane;

/**
 *
 * @author vomin
 */
public class TeacherDAO {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    String sql;

    public void setConnection() {
        try {
            String user = "sa";
            String password = "1";
            String url = "jdbc:sqlserver://localhost;database=SMS";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //region StudentInfo
    //load StudentList
    public ArrayList<StudentInfoModel> getListStudentInfoForGenarate() {
        setConnection();
        ArrayList<StudentInfoModel> lststudent = new ArrayList<StudentInfoModel>();
        try {
            String SQL = "Select * from StudentInfo";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudentInfoModel cm = new StudentInfoModel();
                if (rs.getString("MSSV") != null) {
                    cm.MSSV = rs.getString("MSSV");
                }

                lststudent.add(cm);

            }
            return lststudent;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }
    }

    public ArrayList<StudentInfoModel> getListStudentInfo() {
        setConnection();
        ArrayList<StudentInfoModel> lststudent = new ArrayList<StudentInfoModel>();
        try {
            String SQL = "Select * from StudentInfo where IsDeleted=0";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudentInfoModel cm = new StudentInfoModel();
                if (rs.getString("MSSV") != null) {
                    cm.MSSV = rs.getString("MSSV");
                }
                if (rs.getString("FullName") != null) {
                    cm.FullName = rs.getString("FullName");
                }
                if (rs.getDate("DOB") != null) {
                    cm.DOB = rs.getDate("DOB");
                }
                if (rs.getString("Sex") != null) {
                    cm.Sex = rs.getString("Sex");
                }
                if (rs.getString("OriginAddress") != null) {
                    cm.OriginAddress = rs.getString("OriginAddress");
                }
                if (rs.getString("NowAddress") != null) {
                    cm.NowAddress = rs.getString("NowAddress");
                }
                if (rs.getString("ID") != null) {
                    cm.ID = rs.getString("ID");
                }
                if (rs.getString("Avatar") != null) {
                    cm.Avatar = rs.getString("Avatar");
                }
                if (rs.getString("Phone") != null) {
                    cm.Phone = rs.getString("Phone");
                }
                if (rs.getString("EmailSV") != null) {
                    cm.EmailSV = rs.getString("EmailSV");
                }
                if (rs.getString("KindEducation") != null) {
                    cm.KindEducation = rs.getString("KindEducation");
                }
                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }
                if (rs.getString("ClassID") != null) {
                    cm.ClassID = rs.getString("ClassID");
                }
                if (rs.getInt("Course") != 0) {
                    cm.Course = rs.getInt("Course");
                }
                if (rs.getString("Religion") != null) {
                    cm.Religion = rs.getString("Religion");
                }
                if (rs.getString("Nation") != null) {
                    cm.Nation = rs.getString("Nation");
                }
                if (rs.getString("Status") != null) {
                    cm.Status = rs.getString("Status");
                }
                if (rs.getDate("DateIn") != null) {
                    cm.DateIn = rs.getDate("DateIn");
                }
                if (rs.getDate("DateOut") != null) {
                    cm.DateOut = rs.getDate("DateOut");
                }
                if (rs.getString("BankAccount") != null) {
                    cm.BankAccount = rs.getString("BankAccount");
                }
                if (rs.getString("UpdateUser") != null) {
                    cm.UpdateUser = rs.getString("UpdateUser");
                }
                if (rs.getDate("UpdateTime") != null) {
                    cm.UpdateTime = rs.getDate("UpdateTime");
                }
                if (rs.getDate("LastLoginDate") != null) {
                    cm.LastLoginDate = rs.getDate("LastLoginDate");
                }

                lststudent.add(cm);

            }
            return lststudent;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }
    }

    public ArrayList<Specialized> getListSpecialized() {
        setConnection();

        ArrayList<Specialized> lstspecialized = new ArrayList<Specialized>();
        try {
            String SQL = "Select * from Specialized where Status=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Specialized cm = new Specialized();
                if (rs.getString("SpecializedID") != null) {
                    cm.ID = rs.getString("SpecializedID");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }

                lstspecialized.add(cm);

            }
            return lstspecialized;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }

    }

    public ArrayList<StudyClass> getListClass() {
        setConnection();

        ArrayList<StudyClass> lstclass = new ArrayList<StudyClass>();
        try {
            String SQL = "Select * from Class where Status=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudyClass cm = new StudyClass();
                if (rs.getString("ClassID") != null) {
                    cm.ID = rs.getString("ClassID");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }
                if (rs.getInt("Capacity") != 0) {
                    cm.Capacity = rs.getInt("Capacity");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }
                if (rs.getInt("Course") != 0) {
                    cm.Course = rs.getInt("Course");
                }
                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }

                lstclass.add(cm);

            }
            return lstclass;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }

    }

    public boolean AddStudent(StudentInfoModel student) {
        try {
            
            sql = "INSERT INTO StudentInfo(MSSV,FullName,DOB,Sex,OriginAddress,NowAddress,ID,Avatar,Phone,EmailSV,KindEducation,SpecializedID,ClassID,Course,Religion,Nation,Status,DateIn,DateOut,BankAccount,UpdateUser,IsDeleted) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            String MSSV = GenarateMSSV();
            pstmt.setString(1, MSSV);
            pstmt.setString(2, student.FullName);

            java.sql.Date sqlDOB = new java.sql.Date(student.DOB.getTime());
            pstmt.setDate(3, sqlDOB);
            pstmt.setString(4, student.Sex);
            pstmt.setString(5, student.OriginAddress);
            pstmt.setString(6, student.NowAddress);
            pstmt.setString(7, student.ID);
            pstmt.setString(8, student.Avatar);
            pstmt.setString(9, student.Phone);
            pstmt.setString(10, student.EmailSV);
            pstmt.setString(11, student.KindEducation);
            pstmt.setString(12, student.SpecializedID);
            pstmt.setString(13, student.ClassID);
            pstmt.setInt(14, student.Course);
            pstmt.setString(15, student.Religion);
            pstmt.setString(16, student.Nation);
            pstmt.setString(17, student.Status);

            java.sql.Date sqlDateIn = new java.sql.Date(student.DateIn.getTime());
            pstmt.setDate(18, sqlDateIn);
            if (student.DateOut != null) {
                java.sql.Date sqlDateOut = new java.sql.Date(student.DateOut.getTime());
                pstmt.setDate(19, sqlDateOut);
            } else {
                pstmt.setDate(19, null);
            }

            pstmt.setString(20, student.BankAccount);
            pstmt.setString(21, student.UpdateUser);
            pstmt.setInt(22, 0);

            pstmt.executeUpdate();
            sql = "INSERT INTO Account(Email,Password,Role,Status) VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.EmailSV);
            pstmt.setString(2, student.EmailSV);
            pstmt.setInt(3, 2);
            pstmt.setInt(4, 1);

            pstmt.executeUpdate();
            
            //add account
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public boolean CheckEmailDuplicate(String email)
    {
        setConnection();

        ArrayList<String> lstEmail = new ArrayList<String>();
        try {
            String SQL = "Select * from StudentInfo where IsDeleted=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                String Email="";
                if (rs.getString("EmailSV") != null) {
                    Email = rs.getString("EmailSV");
                }

                lstEmail.add(Email);

            }
            if(lstEmail.contains(email))
            {
                return false;
            }
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return false;
        }

    }
    
    public boolean UpdateStudent(StudentInfoModel student) {
        try {
            sql = "UPDATE StudentInfo SET FullName=?,DOB=?,Sex=?,OriginAddress=?,NowAddress=?,ID=?,Avatar=?,Phone=?,EmailSV=?,KindEducation=?,SpecializedID=?,ClassID=?,Course=?,Religion=?,Nation=?,Status=?,DateIn=?,DateOut=?,BankAccount=?,IsDeleted=?,UpdateUser=? WHERE MSSV=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.FullName);
            java.sql.Date sqlDOB = new java.sql.Date(student.DOB.getTime());
            pstmt.setDate(2, sqlDOB);
            pstmt.setString(3, student.Sex);
            pstmt.setString(4, student.OriginAddress);
            pstmt.setString(5, student.NowAddress);
            pstmt.setString(6, student.ID);
            pstmt.setString(7, student.Avatar);
            pstmt.setString(8, student.Phone);
            pstmt.setString(9, student.EmailSV);
            pstmt.setString(10, student.KindEducation);
            pstmt.setString(11, student.SpecializedID);
            pstmt.setString(12, student.ClassID);
            pstmt.setInt(13, student.Course);
            pstmt.setString(14, student.Religion);
            pstmt.setString(15, student.Nation);
            pstmt.setString(16, student.Status);

            java.sql.Date sqlDateIn = new java.sql.Date(student.DateIn.getTime());
            pstmt.setDate(17, sqlDateIn);
            if (student.DateOut != null) {
                java.sql.Date sqlDateOut = new java.sql.Date(student.DateOut.getTime());
                pstmt.setDate(18, sqlDateOut);
            } else {
                pstmt.setDate(18, null);
            }

            pstmt.setString(19, student.BankAccount);
            pstmt.setInt(20, 0);
            pstmt.setString(21, student.UpdateUser);
            pstmt.setString(22, student.MSSV);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public boolean DeleteStudent(String MSSV, String user, java.util.Date time) {
        try {
            sql = "UPDATE StudentInfo SET IsDeleted=?,DeletedUser=?,DeleteTime=? WHERE MSSV=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
            java.sql.Date sqlDOB = new java.sql.Date(time.getTime());
            pstmt.setDate(3, sqlDOB);

            pstmt.setString(2, user);
            pstmt.setString(4, MSSV);
            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public StudentInfoModel getDetailStudentInfo(String MSSV) {
        setConnection();

        try {
            String SQL = "Select * from StudentInfo where MSSV=" + MSSV;
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudentInfoModel cm = new StudentInfoModel();
                if (rs.getString("MSSV") != null) {
                    cm.MSSV = rs.getString("MSSV");
                }
                if (rs.getString("FullName") != null) {
                    cm.FullName = rs.getString("FullName");
                }
                if (rs.getDate("DOB") != null) {
                    cm.DOB = rs.getDate("DOB");
                }
                if (rs.getString("Sex") != null) {
                    cm.Sex = rs.getString("Sex");
                }
                if (rs.getString("OriginAddress") != null) {
                    cm.OriginAddress = rs.getString("OriginAddress");
                }
                if (rs.getString("NowAddress") != null) {
                    cm.NowAddress = rs.getString("NowAddress");
                }
                if (rs.getString("ID") != null) {
                    cm.ID = rs.getString("ID");
                }
                if (rs.getString("Avatar") != null) {
                    cm.Avatar = rs.getString("Avatar");
                }
                if (rs.getString("Phone") != null) {
                    cm.Phone = rs.getString("Phone");
                }
                if (rs.getString("EmailSV") != null) {
                    cm.EmailSV = rs.getString("EmailSV");
                }
                if (rs.getString("KindEducation") != null) {
                    cm.KindEducation = rs.getString("KindEducation");
                }
                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }
                if (rs.getString("ClassID") != null) {
                    cm.ClassID = rs.getString("ClassID");
                }
                if (rs.getInt("Course") != 0) {
                    cm.Course = rs.getInt("Course");
                }
                if (rs.getString("Religion") != null) {
                    cm.Religion = rs.getString("Religion");
                }
                if (rs.getString("Nation") != null) {
                    cm.Nation = rs.getString("Nation");
                }
                if (rs.getString("Status") != null) {
                    cm.Status = rs.getString("Status");
                }
                if (rs.getDate("DateIn") != null) {
                    cm.DateIn = rs.getDate("DateIn");
                }
                if (rs.getDate("DateOut") != null) {
                    cm.DateOut = rs.getDate("DateOut");
                }
                if (rs.getString("BankAccount") != null) {
                    cm.BankAccount = rs.getString("BankAccount");
                }
                if (rs.getString("UpdateUser") != null) {
                    cm.UpdateUser = rs.getString("UpdateUser");
                }
                if (rs.getDate("UpdateTime") != null) {
                    cm.UpdateTime = rs.getDate("UpdateTime");
                }
                if (rs.getDate("LastLoginDate") != null) {
                    cm.LastLoginDate = rs.getDate("LastLoginDate");
                }

                return cm;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on getdetailstudent Data");
            return null;
        }
        return null;
    }

    private String GenarateMSSV() {
        ///format: 100000+{} (7 chu so)
        int MSSV = 1000000;
        MSSV = MSSV + getListStudentInfoForGenarate().size();

        return Integer.toString(MSSV);

    }

    ArrayList<StudentInfoModel> SearchStudentInfo(String keyword, String Speci, String classSearch) {
        setConnection();
        ArrayList<StudentInfoModel> lststudent = new ArrayList<StudentInfoModel>();
        try {
            String SQL = "Select * from StudentInfo where IsDeleted=0 AND (MSSV LIKE '%" + keyword + "%' OR FullName LIKE '%" + keyword + "%')";
            if (Speci != null) {
                SQL += "AND SpecializedID='" + Speci + "'";
            }
            if (classSearch != null) {
                SQL += "AND ClassID='" + classSearch + "'";
            }
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudentInfoModel cm = new StudentInfoModel();
                if (rs.getString("MSSV") != null) {
                    cm.MSSV = rs.getString("MSSV");
                }
                if (rs.getString("FullName") != null) {
                    cm.FullName = rs.getString("FullName");
                }
                if (rs.getDate("DOB") != null) {
                    cm.DOB = rs.getDate("DOB");
                }
                if (rs.getString("Sex") != null) {
                    cm.Sex = rs.getString("Sex");
                }
                if (rs.getString("OriginAddress") != null) {
                    cm.OriginAddress = rs.getString("OriginAddress");
                }
                if (rs.getString("NowAddress") != null) {
                    cm.NowAddress = rs.getString("NowAddress");
                }
                if (rs.getString("ID") != null) {
                    cm.ID = rs.getString("ID");
                }
                if (rs.getString("Avatar") != null) {
                    cm.Avatar = rs.getString("Avatar");
                }
                if (rs.getString("Phone") != null) {
                    cm.Phone = rs.getString("Phone");
                }
                if (rs.getString("EmailSV") != null) {
                    cm.EmailSV = rs.getString("EmailSV");
                }
                if (rs.getString("KindEducation") != null) {
                    cm.KindEducation = rs.getString("KindEducation");
                }
                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }
                if (rs.getString("ClassID") != null) {
                    cm.ClassID = rs.getString("ClassID");
                }
                if (rs.getInt("Course") != 0) {
                    cm.Course = rs.getInt("Course");
                }
                if (rs.getString("Religion") != null) {
                    cm.Religion = rs.getString("Religion");
                }
                if (rs.getString("Nation") != null) {
                    cm.Nation = rs.getString("Nation");
                }
                if (rs.getString("Status") != null) {
                    cm.Status = rs.getString("Status");
                }
                if (rs.getDate("DateIn") != null) {
                    cm.DateIn = rs.getDate("DateIn");
                }
                if (rs.getDate("DateOut") != null) {
                    cm.DateOut = rs.getDate("DateOut");
                }
                if (rs.getString("BankAccount") != null) {
                    cm.BankAccount = rs.getString("BankAccount");
                }
                if (rs.getString("UpdateUser") != null) {
                    cm.UpdateUser = rs.getString("UpdateUser");
                }
                if (rs.getDate("UpdateTime") != null) {
                    cm.UpdateTime = rs.getDate("UpdateTime");
                }
                if (rs.getDate("LastLoginDate") != null) {
                    cm.LastLoginDate = rs.getDate("LastLoginDate");
                }

                lststudent.add(cm);

            }
            return lststudent;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }

    }
 //endregion

    //regionSubject
    public ArrayList<Semester> getSemesterList() {

        setConnection();

        ArrayList<Semester> lstsmt = new ArrayList<Semester>();
        try {
            String SQL = "Select * from Semester where Status=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Semester cm = new Semester();
                if (rs.getString("SemesterID") != null) {
                    cm.SemesterID = rs.getInt("SemesterID");
                }
                if (rs.getString("SemesterName") != null) {
                    cm.Name = rs.getString("SemesterName");
                }
                if (rs.getInt("CourseYear") != 0) {
                    cm.CourseYear = rs.getInt("CourseYear");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }

                lstsmt.add(cm);

            }
            return lstsmt;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }

    }

    public ArrayList<Subject> getListSubject() {

        setConnection();

        ArrayList<Subject> lstsubject = new ArrayList<Subject>();
        try {
            String SQL = "Select * from Subject where Status=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Subject cm = new Subject();
                if (rs.getString("MMH") != null) {
                    cm.MMH = rs.getString("MMH");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }
                if (rs.getInt("NumberOfCredits") != 0) {
                    cm.NumberOfCredits = rs.getInt("NumberOfCredits");
                }
                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }

                lstsubject.add(cm);

            }
            return lstsubject;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }

    }

    public boolean AddSubject(Subject subject) {
        setConnection();
        try {
            sql = "INSERT INTO Subject(MMH,Name,NumberOfCredits,Status,SpecializedID) VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            String MMH = GenarateMMH();
            pstmt.setString(1, MMH);
            pstmt.setString(2, subject.Name);

            pstmt.setInt(3, subject.NumberOfCredits);
            pstmt.setInt(4, 1);
            pstmt.setString(5, subject.SpecializedID);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public boolean UpdateSubject(Subject subject) {
        setConnection();
        try {
            sql = "UPDATE Subject SET Name=?,NumberOfCredits=?,SpecializedID=? WHERE MMH=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subject.Name);

            pstmt.setInt(2, subject.NumberOfCredits);
            pstmt.setString(3, subject.SpecializedID);
            pstmt.setString(4, subject.MMH);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public ArrayList<Subject> SearchSubject(String keyword, String Speci) {
        setConnection();
        ArrayList<Subject> lstsubject = new ArrayList<Subject>();
        try {
            String SQL = "Select * from Subject where Status=1 AND Name LIKE '%" + keyword + "%'";
            if (Speci != null) {
                SQL += "AND SpecializedID='" + Speci + "'";
            }

            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Subject cm = new Subject();
                if (rs.getString("MMH") != null) {
                    cm.MMH = rs.getString("MMH");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }
                if (rs.getInt("NumberOfCredits") != 0) {
                    cm.NumberOfCredits = rs.getInt("NumberOfCredits");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }

                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }
                lstsubject.add(cm);
            }
            return lstsubject;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }
    }

    public String GenarateMMH() {

        ///format: MMH+{} (3 chu so)
        int MSSV = 1;
        MSSV = MSSV + getListSubject().size();

        String mmh = Integer.toString(MSSV);
        if (mmh.length() == 1) {
            return "MMH00" + mmh;
        } else if (mmh.length() == 2) {
            return "MMH0" + mmh;
        } else {
            return "MMH" + mmh;
        }
    }

    public boolean RegisterSubjectForStudent(String MMH, String[] lstSV, int semester) {
        setConnection();
        ArrayList<StudentInfoModel> studentinfo = getListStudentInfoForGenarate();

        ArrayList<String> mssvlst = new ArrayList<String>();

        for (int i = 0; i < studentinfo.size(); i++) {
            mssvlst.add(studentinfo.get(i).MSSV);

        }
        try {
            for (int i = 0; i < lstSV.length; i++) {
                //kiem tra ma so sinh vien co ton tai hay khong?

                if (mssvlst.contains(lstSV[i])) {

                    sql = "INSERT INTO StudentMark(MMH,MSSV,SemesterID,Status,MidPoint,FinalPoint,AVGPoint,Type) VALUES(?,?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, MMH);
                    pstmt.setString(2, lstSV[i]);

                    pstmt.setInt(3, semester);
                    pstmt.setFloat(4, 1);
                    pstmt.setFloat(5, 0);
                    pstmt.setFloat(6, 0);
                    pstmt.setFloat(7, 0);
                    pstmt.setString(8, null);

                    pstmt.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(null, lstSV[i] + " is not valid");

                    return false;

                }
            }

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    //endregion
    //region class
    public ArrayList<StudyClass> SearchClass(String keyword, String Speci, String Course) {
        setConnection();
        ArrayList<StudyClass> lstclass = new ArrayList<StudyClass>();
        try {
            String SQL = "Select * from Class where Status=1 AND Name LIKE '%" + keyword + "%'";
            if (Speci != null) {
                SQL += "AND SpecializedID='" + Speci + "'";
            }
            if (Course != null) {
                SQL += "AND Course='" + Course + "'";
            }

            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudyClass cm = new StudyClass();
                if (rs.getString("ClassID") != null) {
                    cm.ID = rs.getString("ClassID");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }
                if (rs.getInt("Capacity") != 0) {
                    cm.Capacity = rs.getInt("Capacity");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }

                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }
                if (rs.getString("Course") != null) {
                    cm.Course = rs.getInt("Course");
                }

                lstclass.add(cm);
            }
            return lstclass;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }

    }

    public boolean AddClass(StudyClass _class) {

        setConnection();
        try {
            sql = "INSERT INTO Class(ClassID,Name,Capacity,Status,SpecializedID,Course) VALUES(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            //String ClassID = GenarateClassID(_class.Course);
            String ClassID = _class.Name; //lay giong nhau

            pstmt.setString(1, ClassID);
            pstmt.setString(2, _class.Name);

            pstmt.setInt(3, _class.Capacity);
            pstmt.setInt(4, 1);
            pstmt.setString(5, _class.SpecializedID);

            pstmt.setInt(6, _class.Course);
            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public boolean UpdateClass(StudyClass _class) {

        setConnection();
        try {
            sql = "UPDATE Class SET Name=?,Capacity=?,SpecializedID=?,Course=?  WHERE ClassID=?";
            pstmt = conn.prepareStatement(sql);
            //String ClassID = GenarateClassID(_class.Course);
            //String ClassID = _class.Name; //lay giong nhau

            pstmt.setString(1, _class.Name);

            pstmt.setInt(2, _class.Capacity);

            pstmt.setString(3, _class.SpecializedID);
            pstmt.setInt(4, _class.Course);

            pstmt.setString(5, _class.ID);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public String GenarateClassID(int course) {

        //dinh dang class 2socuoiCourse+"CTT"+sothutuTu1
        String ClassID = "";
        String FinalCourse = Integer.toString(course);
        String haisocuoi = "";

        haisocuoi += FinalCourse.charAt(2) + FinalCourse.charAt(3);
        ClassID += haisocuoi + "CTT";

        int stt = 1;
        stt = stt + getListClass().size();

        String sttClass = Integer.toString(stt);

        return ClassID + sttClass;

    }
  //endregion

    //region mark
    public ArrayList<StudentMarkModel> getListStudentMark() {
        setConnection();

        ArrayList<StudentMarkModel> lstStudentMark = new ArrayList<StudentMarkModel>();
        try {
            String SQL = "Select * from StudentMark where Status=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudentMarkModel cm = new StudentMarkModel();
                if (rs.getString("MMH") != null) {
                    cm.MMH = rs.getString("MMH");
                }
                if (rs.getString("MSSV") != null) {
                    cm.MSSV = rs.getString("MSSV");
                }

                cm.Status = rs.getInt("Status");

                cm.SemesterID = rs.getInt("SemesterID");

                cm.MidPoint = rs.getFloat("MidPoint");

                cm.FinalPoint = rs.getFloat("FinalPoint");

                cm.AVGPoint = rs.getFloat("AVGPoint");

                if (rs.getString("Type") != null) {
                    cm.Type = rs.getString("Type");
                }

                lstStudentMark.add(cm);

            }
            return lstStudentMark;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }
    }

    public Boolean UpdateStudentMark(StudentMarkModel mark) {
        setConnection();
        try {
            sql = "UPDATE StudentMark SET MidPoint=?,FinalPoint=?,AVGPoint=?,Type=?  WHERE MSSV=? AND MMH=? AND SemesterID=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setFloat(1, mark.MidPoint);

            pstmt.setFloat(2, mark.FinalPoint);
            pstmt.setFloat(3, mark.AVGPoint);

            pstmt.setString(4, mark.Type);
            pstmt.setString(5, mark.MSSV);
            pstmt.setString(6, mark.MMH);

            pstmt.setInt(7, mark.SemesterID);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public ArrayList<StudentMarkModel> SearchStuentMark(String keyword, String Subject, String Class) {

        setConnection();
        ArrayList<StudentMarkModel> lststudentmark = new ArrayList<StudentMarkModel>();
        try {
            String SQL = "Select * from StudentMark where Status=1 AND MSSV LIKE '%" + keyword + "%'";

            if (Subject != null) {
                SQL += "AND MMH='" + Subject + "'";
            }
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                StudentMarkModel cm = new StudentMarkModel();
                if (rs.getString("MSSV") != null) {
                    cm.MSSV = rs.getString("MSSV");
                }
                if (rs.getString("MMH") != null) {
                    cm.MMH = rs.getString("MMH");
                }

                cm.SemesterID = rs.getInt("SemesterID");

                if (rs.getFloat("MidPoint") >= 0) {
                    cm.MidPoint = rs.getFloat("MidPoint");
                }

                if (rs.getFloat("FinalPoint") >= 0) {
                    cm.FinalPoint = rs.getFloat("FinalPoint");
                }
                if (rs.getFloat("AVGPoint") >= 0) {
                    cm.AVGPoint = rs.getFloat("AVGPoint");
                }
                if (rs.getString("Type") != null) {
                    cm.Type = rs.getString("Type");
                }

                lststudentmark.add(cm);
            }
            return lststudentmark;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }
    }

    //endregion
    public Subject getDetailSubjectInfo(String MMH) {
        setConnection();

        try {
            String SQL = "Select * from Subject where MMH='" + MMH + "'";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Subject cm = new Subject();
                if (rs.getString("MMH") != null) {
                    cm.MMH = rs.getString("MMH");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }
                if (rs.getInt("NumberOfCredits") != 0) {
                    cm.NumberOfCredits = rs.getInt("NumberOfCredits");
                }
                if (rs.getString("SpecializedID") != null) {
                    cm.SpecializedID = rs.getString("SpecializedID");
                }

                return cm;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on getdetailstudent Data");
            return null;
        }
        return null;

    }

    public Specialized getDetailSpecializedInfo(String SpecializedID) {
        setConnection();

        try {
            String SQL = "Select * from Specialized where SpecializedID='" + SpecializedID + "'";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Specialized cm = new Specialized();
                if (rs.getString("SpecializedID") != null) {
                    cm.ID = rs.getString("SpecializedID");
                }
                if (rs.getString("Name") != null) {
                    cm.Name = rs.getString("Name");
                }

                return cm;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on getdetailstudent Data");
            return null;
        }
        return null;

    }

    public Semester getDetailSemester(int SemesterID) {

        setConnection();

        try {
            String SQL = "Select * from Semester where Status=1 AND SemesterID=" + SemesterID;
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Semester cm = new Semester();
                if (rs.getString("SemesterID") != null) {
                    cm.SemesterID = rs.getInt("SemesterID");
                }
                if (rs.getString("SemesterName") != null) {
                    cm.Name = rs.getString("SemesterName");
                }
                if (rs.getInt("CourseYear") != 0) {
                    cm.CourseYear = rs.getInt("CourseYear");
                }
                if (rs.getInt("Status") != 0) {
                    cm.Status = rs.getInt("Status");
                }

                return cm;

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
            return null;
        }
        return null;

    }

}
