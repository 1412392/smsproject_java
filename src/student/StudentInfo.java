/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import teacher.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import login.LoginForm;

/**
 *
 * @author vomin
 */
public class StudentInfo extends javax.swing.JFrame {

    /**
     * Creates new form StudentInfo
     */
    AccountInfo account = new AccountInfo();
    StudentDAO studentDao = new StudentDAO();
    TeacherDAO teacherDao = new TeacherDAO();
    static String MSSV = "";

    public StudentInfo() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(radioMale);
        group.add(radioFemale);
    }

    public StudentInfo(String MSSV, AccountInfo account) {
        initComponents();
        tbUpdateUser.setVisible(false);
        if (account != null && account.Email != null && account.Password != null) {
            this.account = account;
            lbInfo.setText(account.Email.split("@")[0]);
        }
        if (MSSV != null) {
            this.MSSV = MSSV;
        }
        ButtonGroup group = new ButtonGroup();
        group.add(radioMale);
        group.add(radioFemale);

        LoadStudentInfo(MSSV);
    }

    private void LoadStudentInfo(String MSSV) {
        if (MSSV != null) {
            StudentInfoModel studentinfo = teacherDao.getDetailStudentInfo(MSSV);

            if (studentinfo != null) {
                tbMSSV.setText(studentinfo.MSSV);
                tbFullName.setText(studentinfo.FullName);
                if (studentinfo.Sex.equals("Male")) {
                    radioMale.setSelected(true);
                } else {
                    radioFemale.setSelected(true);
                }
                tbDOB.setText(studentinfo.DOB.toString());
                tbOriginAddress.setText(studentinfo.OriginAddress);
                tbNowAddress.setText(studentinfo.NowAddress);
                if (studentinfo.Avatar.equals("")) {
                    lbImage.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\noavatar.gif", 150, 150));

                } else {
                    lbImage.setIcon(load(studentinfo.Avatar, 150, 150));

                }

                tbID.setText(studentinfo.ID);
                tbPhone.setText(studentinfo.Phone);
                tbEmailSV.setText(studentinfo.EmailSV);

                tbKindEducation.setText(studentinfo.KindEducation);
                tbClass.setText(studentinfo.ClassID);

                Specialized spe = teacherDao.getDetailSpecializedInfo(studentinfo.SpecializedID);

                tbMajor.setText(spe.Name);

                tbCourse.setText(Integer.toString(studentinfo.Course));
                tbReligion.setText(studentinfo.Religion);
                tbNation.setText(studentinfo.Nation);

                tbStatus.setText(studentinfo.Status);
                if (studentinfo.DateIn != null) {
                    tbDateIn.setText(studentinfo.DateIn.toString());
                }
                if (studentinfo.DateOut != null) {
                    tbDateOut.setText(studentinfo.DateOut.toString());
                }

                tbBankAccount.setText(studentinfo.BankAccount);
                if (studentinfo.UpdateUser != null) {
                    tbUpdateUser.setText(studentinfo.UpdateUser);
                } else {
                    tbUpdateUser.setText(account.Email);
                }
                tbLastLoginDate.setText(studentinfo.LastLoginDate.toString());

            }

        }

    }

    public Icon load(String linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/

        try {
            BufferedImage image = ImageIO.read(new File(linkImage));//đọc ảnh dùng BufferedImage

            int x = k;
            int y = m;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            return new ImageIcon(image.getScaledInstance(dx, dy,
                    image.SCALE_SMOOTH));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbInfo = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tbOriginAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbEmailSV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        radioMale = new javax.swing.JRadioButton();
        radioFemale = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        tbKindEducation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tbNowAddress = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tbBankAccount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tbPhone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tbFullName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tbID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tbReligion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tbNation = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tbCourse = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tbLastLoginDate = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        tbMSSV = new javax.swing.JTextField();
        tbMajor = new javax.swing.JTextField();
        tbClass = new javax.swing.JTextField();
        tbDateOut = new javax.swing.JTextField();
        tbStatus = new javax.swing.JTextField();
        tbDateIn = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tbMSSV17 = new javax.swing.JTextField();
        tbDOB = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();
        tbUpdateUser = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\admin.png",50,50));
        jLabel2.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel2.setVerifyInputWhenFocusTarget(false);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\personnalinfo.png")); // NOI18N

        lbInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbInfo.setText("Welcome, Minh Phát");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Origin Address");

        jLabel5.setText("Class");

        tbEmailSV.setEditable(false);

        jLabel6.setText("Sex");

        radioMale.setSelected(true);
        radioMale.setText("Male");

        radioFemale.setText("Female");

        jLabel7.setText("MSSV");

        tbKindEducation.setEditable(false);

        jLabel8.setText("Now Address");

        jLabel9.setText("ID");

        jLabel10.setText("Phone");

        jLabel11.setText("StudentEmail");

        tbFullName.setEditable(false);

        jLabel12.setText("FullName");

        jLabel13.setText("Kind Education");

        jLabel14.setText("Major");

        jLabel15.setText("Course");

        tbID.setEditable(false);

        jLabel16.setText("Religion");

        jLabel17.setText("Nationality");

        jLabel18.setText("Status");

        jLabel19.setText("Date out");

        jLabel20.setText("Date in");

        jLabel22.setText("Bank account");

        tbCourse.setEditable(false);

        jLabel23.setText("LastLogin");

        tbLastLoginDate.setEditable(false);

        btnUpdate.setBackground(new java.awt.Color(102, 153, 255));
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tbMSSV.setEditable(false);

        tbMajor.setEditable(false);

        tbClass.setEditable(false);

        tbDateOut.setEditable(false);

        tbStatus.setEditable(false);
        tbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbStatusActionPerformed(evt);
            }
        });

        tbDateIn.setEditable(false);

        jLabel24.setText("Bank Name");

        tbMSSV17.setEditable(false);
        tbMSSV17.setText("VIETCOMBANK");

        tbDOB.setEditable(false);

        jLabel21.setText("DOB");

        lbImage.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\noavatar.gif",150,150));
        lbImage.setText("              Avatar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(574, 574, 574))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(325, 325, 325)
                        .addComponent(tbReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(tbNation, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbID, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbKindEducation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(tbUpdateUser)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tbEmailSV, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                                    .addComponent(tbMajor)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tbLastLoginDate, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbBankAccount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tbCourse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tbDateIn, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbMSSV17, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                                    .addComponent(tbDateOut)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(tbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioMale)
                        .addGap(57, 57, 57)
                        .addComponent(radioFemale)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbDOB))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tbNowAddress))
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tbOriginAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioMale)
                    .addComponent(radioFemale)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tbUpdateUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbOriginAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbNowAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbEmailSV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbKindEducation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbNation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbDateOut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbDateIn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbBankAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbMSSV17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbLastLoginDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jButton7.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\back.png",50,50));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(160, 160, 160)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addGap(64, 64, 64))
                    .addComponent(lbInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLogout))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Do you want logout?");
        if (click == JOptionPane.YES_OPTION) {
            LoginForm home = new LoginForm();
            home.setVisible(true);
            dispose();
        }
        if (click == JOptionPane.NO_OPTION) {

        }
        if (click == JOptionPane.CANCEL_OPTION) {

        }
        if (click == JOptionPane.CLOSED_OPTION) {

        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // TODO add your handling code here:
            Home home = new Home(account);
            home.setVisible(true);
            this.dispose();
        } catch (ParseException ex) {
            Logger.getLogger(StudentInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        try {
            // TODO add your handling code here:
            Home home = new Home(account);
            home.setVisible(true);
            this.dispose();
        } catch (ParseException ex) {
            Logger.getLogger(StudentInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel5MouseClicked

    private void tbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbStatusActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        //chi cho update may thang nay
        String Phone;
        String OriginAddress;
        String NowAddress;
        String Religion;
        String Nation;
        String BankAccount;
        String UpdateUser;

        if (tbOriginAddress.getText() == null || tbOriginAddress.getText().toString().length() < 5) {
            JOptionPane.showMessageDialog(this, "OriginAddress is not valid");
            return;
        } else {
            OriginAddress = tbOriginAddress.getText();
        }
        if (tbNowAddress.getText() == null || tbNowAddress.getText().toString().length() < 5) {
            JOptionPane.showMessageDialog(this, "NowAddress is not valid");
            return;
        } else {
            NowAddress = tbNowAddress.getText();
        }

        if (tbPhone.getText() == null || tbPhone.getText().length() < 8
                || tbPhone.getText().length() > 15 || CheckPhone(tbPhone.getText())) {
            JOptionPane.showMessageDialog(this, "Phone is not valid");
            return;
        } else {
            Phone = tbPhone.getText();
        }

        if (tbReligion.getText() == null) {
            JOptionPane.showMessageDialog(this, "Religion is not valid");
            return;
        } else {
            Religion = tbReligion.getText();
        }

        if (tbNation.getText() == null) {
            JOptionPane.showMessageDialog(this, "Nation is not valid");
            return;
        } else {
            Nation = tbNation.getText();
        }

        if (tbBankAccount.getText().length() > 16) {
            JOptionPane.showMessageDialog(this, "BankAccount is not valid");
            return;
        } else {
            BankAccount = tbBankAccount.getText().toString();
        }
        UpdateUser = account.Email;
        boolean result = false;

        result = studentDao.UpdateStudent(MSSV, Phone, OriginAddress, NowAddress, Religion, Nation, BankAccount, UpdateUser);

        if (result) {
            JOptionPane.showMessageDialog(this, "Update success!");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Something wrong. Try again");
            return;
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private boolean CheckPhone(String text) {

        String pattern = "(\\d{10,13})";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(text);
        if (m.find()) {
            return false;
        } else {

            return true;

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JTextField tbBankAccount;
    private javax.swing.JTextField tbClass;
    private javax.swing.JTextField tbCourse;
    private javax.swing.JTextField tbDOB;
    private javax.swing.JTextField tbDateIn;
    private javax.swing.JTextField tbDateOut;
    private javax.swing.JTextField tbEmailSV;
    private javax.swing.JTextField tbFullName;
    private javax.swing.JTextField tbID;
    private javax.swing.JTextField tbKindEducation;
    private javax.swing.JTextField tbLastLoginDate;
    private javax.swing.JTextField tbMSSV;
    private javax.swing.JTextField tbMSSV17;
    private javax.swing.JTextField tbMajor;
    private javax.swing.JTextField tbNation;
    private javax.swing.JTextField tbNowAddress;
    private javax.swing.JTextField tbOriginAddress;
    private javax.swing.JTextField tbPhone;
    private javax.swing.JTextField tbReligion;
    private javax.swing.JTextField tbStatus;
    private javax.swing.JLabel tbUpdateUser;
    // End of variables declaration//GEN-END:variables

}
