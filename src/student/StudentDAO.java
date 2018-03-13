/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.AccountInfo;
import Model.StudentInfoModel;
import Model.StudentMarkModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import teacher.TeacherDAO;

/**
 *
 * @author vomin
 */
public class StudentDAO {

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
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean UpdateLoginTime(String MSSV) throws ParseException {
        setConnection();
        try {
            sql = "UPDATE StudentInfo SET LastLoginDate=? WHERE MSSV=?";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt = conn.prepareStatement(sql);

            String DateOrigin;

            DateOrigin = sdf.format(Calendar.getInstance().getTime());
            java.util.Date date = sdf.parse(DateOrigin);

            java.sql.Date sqlDOB = new java.sql.Date(date.getTime());
            pstmt.setDate(1, sqlDOB);

            pstmt.setString(2, MSSV);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public String GetMSSVByEmail(String Email) {
        setConnection();
        try {
            String SQL = "Select * from StudentInfo where EmailSV='" + Email + "'";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {

                return rs.getString("MSSV");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on getdetailstudent Data");
            return null;
        }
        return null;
    }

    boolean UpdateStudent(String MSSV, String Phone, String OriginAddress, String NowAddress, String Religion, String Nation, String BankAccount, String UpdateUser) {

        setConnection();
        try {
            sql = "UPDATE StudentInfo SET Phone=?,OriginAddress=?,NowAddress=?,Religion=?,Nation=?, BankAccount=?, UpdateUser=? WHERE MSSV=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Phone);

            pstmt.setString(2, OriginAddress);
            pstmt.setString(3, NowAddress);
            pstmt.setString(4, Religion);
            pstmt.setString(5, Nation);
            pstmt.setString(6, BankAccount);
            pstmt.setString(7, UpdateUser);
            pstmt.setString(8, MSSV);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public ArrayList<StudentMarkModel> getListStudentMarkByMSSV(String MSSV) {
        setConnection();

        ArrayList<StudentMarkModel> lstStudentMark = new ArrayList<StudentMarkModel>();
        try {
            String SQL = "Select * from StudentMark where Status=1 AND MSSV='" + MSSV + "'";
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

    ArrayList<StudentMarkModel> getListStudentMarkByMSSVAndSemester(String MSSV, int SemsterID) {
        setConnection();

        ArrayList<StudentMarkModel> lstStudentMark = new ArrayList<StudentMarkModel>();
        try {
            String SQL = "Select * from StudentMark where Status=1 AND SemesterID=" + SemsterID + "AND MSSV='" + MSSV + "'";
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

}
