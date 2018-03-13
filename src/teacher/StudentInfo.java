/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import Model.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
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
    TeacherDAO teacherDao = new TeacherDAO();

    public StudentInfo() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);

        ButtonGroup group = new ButtonGroup();
        group.add(radioMale);
        group.add(radioFemale);
    }

    public StudentInfo(AccountInfo account) {
        initComponents();
        if (account != null && account.Email != null && account.Password != null) {
            this.account = account;
            tbInfo.setText("Welcome, " + account.Email.split("@")[0]);
        }

        this.setLocationRelativeTo(this);
        this.setResizable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(radioMale);
        group.add(radioFemale);

        tbInputSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tbInputSearch.getText().equals("Find by MSSV or Name...")) {
                    tbInputSearch.setText("");

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tbInputSearch.getText().isEmpty()) {
                    tbInputSearch.setText("Find by MSSV or Name...");
                }
            }
        });

        //load list student
        LoadDataToTable();

        //load list specialized (major)
        ArrayList<Specialized> Specializedlst = new ArrayList<Specialized>();
        Specializedlst = teacherDao.getListSpecialized();
        LoadDataToCbbSpecialized(Specializedlst);

        //load list  (class)
        ArrayList<StudyClass> Classlst = new ArrayList<StudyClass>();
        Classlst = teacherDao.getListClass();
        LoadDataToCbbClass(Classlst);

        //add event table
        tableStudentInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableStudentInfo.rowAtPoint(evt.getPoint());
                int col = tableStudentInfo.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    getSelectedRow();
                }
            }
        });

    }

    public void getSelectedRow() {

        int row = tableStudentInfo.getSelectedRow();
        String MSSV = tableStudentInfo.getModel().getValueAt(row, 0).toString();

        StudentInfoModel current = new StudentInfoModel();
        current = teacherDao.getDetailStudentInfo(MSSV);

        tbUpdateUser.setText(current.UpdateUser);
        tbMSSV.setText(current.MSSV);
        tbFullName.setText(current.FullName);
        if (current.Sex.equals("Male")) {
            radioMale.setSelected(true);

        } else {
            radioFemale.setSelected(true);
        }

        tbDOB.setDate(current.DOB);
        tbOriginAddress.setText(current.OriginAddress);
        tbNowAddress.setText(current.NowAddress);
           
        if (current.Avatar.equals("")) {
            lbImage.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectSMS\\content\\image\\noavatar.gif", 150, 150));

        } else {
            //thay doi duong dan
            String newpath=current.Avatar.replace("\\\\", "\\");
            
 lbImage.setIcon(load(newpath, 150, 150));
        }

        tbID.setText(current.ID);
        tbPhone.setText(current.Phone);
        tbEmail.setText(current.EmailSV);

        if (current.KindEducation.equals("Chính Quy")) {
            tbKindEducation.setSelectedIndex(0);
        }
        if (current.KindEducation.equals("Liên Thông")) {
            tbKindEducation.setSelectedIndex(1);
        }
        if (current.KindEducation.equals("Tại Chức")) {
            tbKindEducation.setSelectedIndex(2);
        }
        if (current.KindEducation.equals("Hoàn Chỉnh")) {
            tbKindEducation.setSelectedIndex(3);
        }

        ArrayList<StudyClass> classlist = teacherDao.getListClass();
        for (int i = 0; i < classlist.size(); i++) {
            if (classlist.get(i).ID.equals(current.ClassID)) {
                cbbClass.setSelectedIndex(i);
                break;
            }
        }

        ArrayList<Specialized> major = teacherDao.getListSpecialized();
        for (int i = 0; i < major.size(); i++) {
            if (major.get(i).ID.equals(current.SpecializedID)) {
                cbbMajor.setSelectedIndex(i);
                break;
            }
        }

        tbCourse.setText(Integer.toString(current.Course));
        tbReligion.setText(current.Religion);
        tbNation.setText(current.Nation);

        if (current.Status.equals("Đang học")) {
            tbStatus.setSelectedIndex(0);
        }
        if (current.Status.equals("Đã tốt nghiệp")) {
            tbStatus.setSelectedIndex(1);
        }
        if (current.Status.equals("Tạm Hoãn")) {
            tbStatus.setSelectedIndex(2);
        }
        if (current.Status.equals("Đình Chỉ")) {
            tbStatus.setSelectedIndex(3);
        }

        tbDateIn.setDate(current.DateIn);
        tbDateOut.setDate(current.DateOut);
        tbBankAccount.setText(current.BankAccount);
        tbUpdateUser.setText(current.UpdateUser);
        tbLastLogin.setText(current.LastLoginDate.toString());

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
        tbInfo = new javax.swing.JLabel();
        btnlogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStudentInfo = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        tbInputSearch = new javax.swing.JTextField();
        cbbClass2 = new javax.swing.JComboBox();
        cbbMajor2 = new javax.swing.JComboBox();
        btnsearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tbOriginAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        radioMale = new javax.swing.JRadioButton();
        radioFemale = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        tbMSSV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tbNowAddress = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tbBankAccount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tbPhone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tbFullName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tbKindEducation = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cbbClass = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        cbbMajor = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        tbID = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tbReligion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tbNation = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tbStatus = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        tbDateOut = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        tbDateIn = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        tbUpdateUser = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tbCourse = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tbLastLogin = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        btnaddupdate = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        tbDOB = new com.toedter.calendar.JDateChooser();
        lbImage = new javax.swing.JLabel();
        btnchangefile = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectSMS\\content\\image\\admin.png",50,50));
        jLabel2.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel2.setVerifyInputWhenFocusTarget(false);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));

        tbInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbInfo.setText("Welcome, Minh Phát");

        btnlogout.setText("Logout");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        tableStudentInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MSSV", "FullName", "Class", "Major", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableStudentInfo);

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

        tbInputSearch.setText("Find by MSSV or Name...");
        tbInputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbInputSearchActionPerformed(evt);
            }
        });

        btnsearch.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectSMS\\content\\image\\search.png",40,40));
        btnsearch.setText("SEARCH");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Origin Address");

        jLabel5.setText("Class");

        jLabel6.setText("Sex");

        radioMale.setSelected(true);
        radioMale.setText("Male");

        radioFemale.setText("Female");

        jLabel7.setText("MSSV");

        tbMSSV.setEditable(false);

        jLabel8.setText("Now Address");

        jLabel9.setText("ID");

        jLabel10.setText("Phone");

        jLabel11.setText("Email");

        jLabel12.setText("FullName");

        tbKindEducation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chính Quy", "Liên Thông", "Tại Chức", "Hoàn Chỉnh" }));
        tbKindEducation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbKindEducationActionPerformed(evt);
            }
        });

        jLabel13.setText("Kind Education");

        cbbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbClassActionPerformed(evt);
            }
        });

        jLabel14.setText("Major");

        cbbMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMajorActionPerformed(evt);
            }
        });

        jLabel15.setText("Course");

        jLabel16.setText("Religion");

        jLabel17.setText("Nationality");

        jLabel18.setText("Status");

        tbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang học", "Đã tốt nghiệp", "Tạm Hoãn", "Đình Chỉ" }));
        tbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbStatusActionPerformed(evt);
            }
        });

        jLabel19.setText("Date out");

        jLabel20.setText("Date in");

        jLabel21.setText("UpdateUser");

        tbUpdateUser.setEditable(false);

        jLabel22.setText("Bank account");

        jLabel23.setText("LastLogin");

        tbLastLogin.setEditable(false);

        btndelete.setBackground(new java.awt.Color(255, 102, 102));
        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnaddupdate.setBackground(new java.awt.Color(102, 153, 255));
        btnaddupdate.setText("ADD/UPDATE");
        btnaddupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddupdateActionPerformed(evt);
            }
        });

        jLabel24.setText("DOB");

        lbImage.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectSMS\\content\\image\\noavatar.gif",150,150));

        btnchangefile.setText("Change");
        btnchangefile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchangefileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnaddupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(264, 264, 264))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbNowAddress)
                                    .addComponent(tbOriginAddress))
                                .addGap(38, 38, 38)
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnchangefile, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tbBankAccount))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbDateIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tbReligion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tbDateOut, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbbMajor, 0, 227, Short.MAX_VALUE)
                                            .addComponent(tbNation)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(tbID, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tbKindEducation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tbEmail))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tbUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tbLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(tbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tbFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioFemale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tbDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbDOB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioMale)
                        .addComponent(radioFemale)
                        .addComponent(tbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tbFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbOriginAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbNowAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnchangefile)
                                .addGap(25, 25, 25)))))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbKindEducation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbNation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbDateIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbDateOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbBankAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnaddupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jButton7.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectSMS\\content\\image\\back.png",50,50));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnlogout)
                        .addGap(64, 64, 64))
                    .addComponent(tbInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbClass2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMajor2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tbInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnlogout))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbClass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMajor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
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
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        String keyword;
        if (tbInputSearch.getText().equals("Find by MSSV or Name...")) {
            keyword = "";
        } else {
            keyword = tbInputSearch.getText();
        }

        String Speci;
        String classSearch;

        if (cbbClass2.getSelectedItem().toString().equals("Choose Class")) {
            classSearch = null;
        } else {
            classSearch = cbbClass2.getSelectedItem().toString();

        }
        if (cbbMajor2.getSelectedItem().toString().equals("Choose Specialized")) {
            Speci = null;

        } else {
            Speci = cbbMajor2.getSelectedItem().toString().split("-")[0];
        }

        ArrayList<StudentInfoModel> studentlist = teacherDao.SearchStudentInfo(keyword, Speci, classSearch);

        LoadDataToTable(studentlist);


    }//GEN-LAST:event_btnsearchActionPerformed

    private void tbInputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbInputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbInputSearchActionPerformed

    private void cbbMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMajorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMajorActionPerformed

    private void tbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbStatusActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Home home = new Home(account);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cbbClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbClassActionPerformed

    private void tbKindEducationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbKindEducationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbKindEducationActionPerformed

    private void btnaddupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddupdateActionPerformed
        // TODO add your handling code here:
        StudentInfoModel student = new StudentInfoModel();
        if (tbFullName.getText() == null || tbFullName.getText().toString().length() < 5) {
            JOptionPane.showMessageDialog(this, "FullName is not valid");
            return;
        } else {
            student.FullName = tbFullName.getText();
        }

        if (radioMale.isSelected()) {
            student.Sex = radioMale.getText();
        } else {
            student.Sex = radioFemale.getText();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        if (tbDOB.getDate() == null) {
            date = null;
        } else {
            date = sdf.format(tbDOB.getDate().getTime());
        }

        //ngay sinh phai nho hon ngay hien tai
        try {
            if (date == null || sdf.parse(date).after(Calendar.getInstance().getTime())) {
                JOptionPane.showMessageDialog(this, "DateOfBirth is not valid");
                return;
            } else {
                student.DOB = sdf.parse(date);

            }
        } catch (ParseException ex) {
            Logger.getLogger(StudentInfo.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        if (tbOriginAddress.getText() == null || tbOriginAddress.getText().toString().length() < 5) {
            JOptionPane.showMessageDialog(this, "OriginAddress is not valid");
            return;
        } else {
            student.OriginAddress = tbOriginAddress.getText();
        }
        if (tbNowAddress.getText() == null || tbNowAddress.getText().toString().length() < 5) {
            JOptionPane.showMessageDialog(this, "NowAddress is not valid");
            return;
        } else {
            student.NowAddress = tbNowAddress.getText();
        }

        if (lbImage.getText() == null) {
            student.Avatar = "";

        } else {
            student.Avatar = lbImage.getText();
        }

        if (tbID.getText() == null || tbID.getText().length() < 8 || tbID.getText().length() > 9 || CheckID(tbID.getText())) {
            JOptionPane.showMessageDialog(this, "ID is not valid");
            return;
        } else {
            student.ID = tbID.getText();
        }
        if (tbPhone.getText() == null || tbPhone.getText().length() < 8
                || tbPhone.getText().length() > 15 || CheckPhone(tbPhone.getText())) {
            JOptionPane.showMessageDialog(this, "Phone is not valid");
            return;
        } else {
            student.Phone = tbPhone.getText();
        }

        if (!tbEmail.getText().contains("@") || tbEmail == null) {
            JOptionPane.showMessageDialog(this, "Email is not valid");
            return;
        } else {
            if (!teacherDao.CheckEmailDuplicate(tbEmail.getText())) {
                JOptionPane.showMessageDialog(this, "Email was exist.Please choose another Email!");
                return;
            } else {
                student.EmailSV = tbEmail.getText();
            }

        }

        student.KindEducation = tbKindEducation.getSelectedItem().toString();
        student.ClassID = cbbClass.getSelectedItem().toString();
        student.SpecializedID = cbbMajor.getSelectedItem().toString().split("-")[0];
        try {
            student.Course = Integer.parseInt(tbCourse.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Course is only number");
            return;

        }
        if (tbReligion.getText() == null) {
            JOptionPane.showMessageDialog(this, "Religion is not valid");
            return;
        } else {
            student.Religion = tbReligion.getText();
        }

        if (tbNation.getText() == null) {
            JOptionPane.showMessageDialog(this, "Nation is not valid");
            return;
        } else {
            student.Nation = tbNation.getText();
        }

        student.Status = tbStatus.getSelectedItem().toString();

        if (tbDateIn.getDate() == null) {
            JOptionPane.showMessageDialog(this, "You must choose Datein");
            return;
        } else {
            String datein = sdf.format(tbDateIn.getDate().getTime());
            try {
                student.DateIn = sdf.parse(datein);
            } catch (ParseException ex) {
                Logger.getLogger(StudentInfo.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Parse DateIn wrong!");
                return;
            }
        }

        if (tbDateOut.getDate() == null) {
            student.DateOut = null;
        } else {
            String dateout = sdf.format(tbDateOut.getDate().getTime());
            try {
                student.DateOut = sdf.parse(dateout);

            } catch (ParseException ex) {
                Logger.getLogger(StudentInfo.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Parse Dateout wrong!");
                return;
            }

        }

        if (tbBankAccount.getText().length() > 16) {
            JOptionPane.showMessageDialog(this, "BankAccount is not valid");
            return;
        } else {
            student.BankAccount = tbBankAccount.getText().toString();
        }
        student.UpdateUser = account.Email;
        boolean result = false;
        if (tbMSSV.getText() == null || tbMSSV.getText().equals("")) {
            result = teacherDao.AddStudent(student);
            clearBoxField();
        } else {
            student.MSSV = tbMSSV.getText();

            result = teacherDao.UpdateStudent(student);
        }
        if (result) {
            JOptionPane.showMessageDialog(this, "Operation was success");

            LoadDataToTable();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Something wrong. Try again");
            return;
        }

    }//GEN-LAST:event_btnaddupdateActionPerformed

    private void btnchangefileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchangefileActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();

        FileTypeFilter filter = new FileTypeFilter(".jpg", "JPEG Images");
        FileTypeFilter filter2 = new FileTypeFilter(".png", "PNG Images");

        file.addChoosableFileFilter(filter);
        file.addChoosableFileFilter(filter2);

        file.showOpenDialog(null);

        File f = file.getSelectedFile();

        String filepath = f.getAbsolutePath();

        lbImage.setIcon(load(filepath, 150, 150));
        lbImage.setText(filepath);


    }//GEN-LAST:event_btnchangefileActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        String MSSV = tbMSSV.getText();
        if (MSSV == null) {
            JOptionPane.showMessageDialog(this, "Please choose Student !");
        } else {

            boolean result = teacherDao.DeleteStudent(MSSV, account.Email, Calendar.getInstance().getTime());
            if (result) {

                JOptionPane.showMessageDialog(this, "Delete success");
                clearBoxField();

                LoadDataToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Something wrong. Try again");
            }

        }
    }//GEN-LAST:event_btndeleteActionPerformed

    public void clearBoxField() {
        tbMSSV.setText(null);
        tbFullName.setText(null);

        tbDOB.setDate(null);
        tbOriginAddress.setText(null);
        tbNowAddress.setText(null);

        lbImage.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectSMS\\content\\image\\noavatar.gif", 150, 150));

        tbID.setText(null);
        tbPhone.setText(null);
        tbEmail.setText(null);

        tbKindEducation.setSelectedIndex(0);

        cbbClass.setSelectedIndex(0);

        cbbMajor.setSelectedIndex(0);

        tbCourse.setText(null);
        tbReligion.setText(null);
        tbNation.setText(null);

        tbStatus.setSelectedIndex(0);

        tbDateIn.setDate(null);
        tbDateOut.setDate(null);
        tbBankAccount.setText(null);
        tbUpdateUser.setText(null);
        tbLastLogin.setText(null);
    }

    private void LoadDataToTable() {
        ArrayList<StudentInfoModel> studentlst = new ArrayList<StudentInfoModel>();
        studentlst = teacherDao.getListStudentInfo();
        DefaultTableModel model = (DefaultTableModel) tableStudentInfo.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // noti

        String[] cols = {"MSSV", "FullName", "Class", "Major", "Status"};

        for (int i = 0; i < studentlst.size(); i++) {
            StudentInfoModel a = studentlst.get(i);

            Object[] data = {a.MSSV, a.FullName, a.ClassID, a.SpecializedID, a.Status};
            model.addRow(data);
        }
    }

    private void LoadDataToTable(ArrayList<StudentInfoModel> studentlst) {

        DefaultTableModel model = (DefaultTableModel) tableStudentInfo.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // noti

        String[] cols = {"MSSV", "FullName", "Class", "Major", "Status"};

        for (int i = 0; i < studentlst.size(); i++) {
            StudentInfoModel a = studentlst.get(i);

            Object[] data = {a.MSSV, a.FullName, a.ClassID, a.SpecializedID, a.Status};
            model.addRow(data);
        }
    }

    private void LoadDataToCbbSpecialized(ArrayList<Specialized> Specializedlst) {
        cbbMajor2.addItem("Choose Specialized");
        for (int i = 0; i < Specializedlst.size(); i++) {
            cbbMajor.addItem(Specializedlst.get(i).ID + "-" + Specializedlst.get(i).Name);
            cbbMajor2.addItem(Specializedlst.get(i).ID + "-" + Specializedlst.get(i).Name);
        }
    }

    private void LoadDataToCbbClass(ArrayList<StudyClass> Classlst) {
        cbbClass2.addItem("Choose Class");
        for (int i = 0; i < Classlst.size(); i++) {
            cbbClass.addItem(Classlst.get(i).Name);
            cbbClass2.addItem(Classlst.get(i).Name);

        }
    }

    private boolean CheckID(String text) {

        String pattern = "\\d{9}";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(text);
        if (m.find()) {
            return false;
        } else {

            return true;

        }
    }

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddupdate;
    private javax.swing.JButton btnchangefile;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnsearch;
    private javax.swing.JComboBox cbbClass;
    private javax.swing.JComboBox cbbClass2;
    private javax.swing.JComboBox cbbMajor;
    private javax.swing.JComboBox cbbMajor2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImage;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JTable tableStudentInfo;
    private javax.swing.JTextField tbBankAccount;
    private javax.swing.JTextField tbCourse;
    private com.toedter.calendar.JDateChooser tbDOB;
    private com.toedter.calendar.JDateChooser tbDateIn;
    private com.toedter.calendar.JDateChooser tbDateOut;
    private javax.swing.JTextField tbEmail;
    private javax.swing.JTextField tbFullName;
    private javax.swing.JTextField tbID;
    private javax.swing.JLabel tbInfo;
    private javax.swing.JTextField tbInputSearch;
    private javax.swing.JComboBox tbKindEducation;
    private javax.swing.JTextField tbLastLogin;
    private javax.swing.JTextField tbMSSV;
    private javax.swing.JTextField tbNation;
    private javax.swing.JTextField tbNowAddress;
    private javax.swing.JTextField tbOriginAddress;
    private javax.swing.JTextField tbPhone;
    private javax.swing.JTextField tbReligion;
    private javax.swing.JComboBox tbStatus;
    private javax.swing.JTextField tbUpdateUser;
    // End of variables declaration//GEN-END:variables

}

class FileTypeFilter extends FileFilter {

    private String extension;
    private String description;

    public FileTypeFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().toLowerCase().endsWith(extension);
    }

    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}
