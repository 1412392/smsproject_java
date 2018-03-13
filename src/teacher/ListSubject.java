/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import Model.AccountInfo;
import Model.Semester;
import Model.Specialized;
import Model.StudentInfoModel;
import Model.StudyClass;
import Model.Subject;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.LoginForm;

/**
 *
 * @author vomin
 */
public class ListSubject extends javax.swing.JFrame {

    /**
     * Creates new form StudentInfo
     */
    TeacherDAO teacherDao = new TeacherDAO();
    AccountInfo account = new AccountInfo();

    public ListSubject() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);
    }
    

    public ListSubject(AccountInfo accountinfo) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        if (accountinfo != null && accountinfo.Email != null && accountinfo.Password != null) {
            account = accountinfo;
            lbInfo.setText("Welcome, " + account.Email.split("@")[0]);
        }

        tbInputSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tbInputSearch.getText().equals("Find by name subject...")) {
                    tbInputSearch.setText("");

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tbInputSearch.getText().isEmpty()) {
                    tbInputSearch.setText("Find by name subject...");
                }
            }
        });

        //load major
        ArrayList<Specialized> Specializedlst = new ArrayList<Specialized>();
        Specializedlst = teacherDao.getListSpecialized();
        LoadDataToCbbSpecialized(Specializedlst);

        //load semester
        ArrayList<Semester> semesterdlst = new ArrayList<Semester>();
        semesterdlst = teacherDao.getSemesterList();
        LoadDataToCbbSemester(semesterdlst);

        //load list subject to table
        LoadDataToTable();

        //add event table
        tableSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableSubject.rowAtPoint(evt.getPoint());
                int col = tableSubject.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    getSelectedRow();
                }
            }
        });

    }

    public void getSelectedRow() {

        int row = tableSubject.getSelectedRow();
        String MMH = tableSubject.getModel().getValueAt(row, 0).toString();

        tbSubjectID.setText(MMH);
        tbSubjectName.setText(tableSubject.getModel().getValueAt(row, 1).toString());

        tbNumberCredit.setText(tableSubject.getModel().getValueAt(row, 2).toString());

        String SpecializedID = tableSubject.getModel().getValueAt(row, 3).toString();

        ArrayList<Specialized> spelist = teacherDao.getListSpecialized();
        for (int i = 0; i < spelist.size(); i++) {
            if (spelist.get(i).ID.equals(SpecializedID)) {
                cbbMajor.setSelectedIndex(i);
                break;
            }
        }
        tMMH2.setText(MMH);

    }

    private void LoadDataToCbbSpecialized(ArrayList<Specialized> Specializedlst) {
        cbbMajor2.addItem("Choose Specialized");
        for (int i = 0; i < Specializedlst.size(); i++) {
            cbbMajor.addItem(Specializedlst.get(i).ID + "-" + Specializedlst.get(i).Name);
            cbbMajor2.addItem(Specializedlst.get(i).ID + "-" + Specializedlst.get(i).Name);
        }
    }

    private void LoadDataToCbbSemester(ArrayList<Semester> semesterdlst) {

        for (int i = 0; i < semesterdlst.size(); i++) {
            cbbSemester.addItem(semesterdlst.get(i).SemesterID + "-" + semesterdlst.get(i).Name);

        }
    }

    private void LoadDataToTable() {
        ArrayList<Subject> subjectlst = new ArrayList<Subject>();
        subjectlst = teacherDao.getListSubject();
        DefaultTableModel model = (DefaultTableModel) tableSubject.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // noti

        String[] cols = {"SubjectID", "SubjectName", "NumberOfCredits", "SpecializedID"};

        for (int i = 0; i < subjectlst.size(); i++) {
            Subject a = subjectlst.get(i);

            Object[] data = {a.MMH, a.Name, a.NumberOfCredits, a.SpecializedID};
            model.addRow(data);
        }
    }

    private void LoadDataToTable(ArrayList<Subject> subjectList) {

        DefaultTableModel model = (DefaultTableModel) tableSubject.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); // noti

        String[] cols = {"SubjectID", "SubjectName", "NumberOfCredits", "SpecializedID"};

        for (int i = 0; i < subjectList.size(); i++) {
            Subject a = subjectList.get(i);

            Object[] data = {a.MMH, a.Name, a.NumberOfCredits, a.SpecializedID};
            model.addRow(data);
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
        btnlogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSubject = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        tbInputSearch = new javax.swing.JTextField();
        cbbMajor2 = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnRegisterSubject = new javax.swing.JButton();
        tMMH2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbbSemester = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        taListMSSV = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tbSubjectID = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tbSubjectName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tbNumberCredit = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbbMajor = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        goback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\admin.png",50,50));
        jLabel2.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel2.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel2.setVerifyInputWhenFocusTarget(false);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\listsubject.png")); // NOI18N

        lbInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbInfo.setText("Welcome, Minh Phát");

        btnlogout.setText("Logout");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        tableSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SubjectID", "SubjectName", "NumberOfCredits", "SpecializedID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableSubject);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        tbInputSearch.setText("Find by name subject...");
        tbInputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbInputSearchActionPerformed(evt);
            }
        });

        btnSearch.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\search.png",40,40));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Register Subject For Student");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("SubjectID");

        btnRegisterSubject.setBackground(new java.awt.Color(102, 153, 255));
        btnRegisterSubject.setText("ADD/UPDATE");
        btnRegisterSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterSubjectActionPerformed(evt);
            }
        });

        tMMH2.setEditable(false);
        tMMH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tMMH2ActionPerformed(evt);
            }
        });

        jLabel16.setText("List MSSV");

        jLabel17.setText("Semester");

        cbbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSemesterActionPerformed(evt);
            }
        });

        taListMSSV.setColumns(20);
        taListMSSV.setRows(5);
        jScrollPane2.setViewportView(taListMSSV);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tMMH2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(218, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRegisterSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(370, 370, 370))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tMMH2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegisterSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Subject Info");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("SubjectID");

        tbSubjectID.setEditable(false);
        tbSubjectID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbSubjectIDActionPerformed(evt);
            }
        });

        jLabel13.setText("SubjectName");

        jLabel14.setText("NumberOfCredits");

        jLabel15.setText("Major");

        btnAdd.setBackground(new java.awt.Color(102, 153, 255));
        btnAdd.setText("ADD/UPDATE");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tbNumberCredit, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(tbSubjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(tbSubjectID, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(cbbMajor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbSubjectID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbNumberCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(696, 696, 696)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        goback.setIcon(load("D:\\Aptech\\LapTrinh_AJ1+2\\AJ2\\ProjectHKII\\content\\image\\back.png",50,50));
        goback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(goback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goback)
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
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnlogout)
                        .addGap(64, 64, 64))
                    .addComponent(lbInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbMajor2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(btnlogout))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbMajor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbInputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String keyword;
        if (tbInputSearch.getText().equals("Find by name subject...")) {
            keyword = "";
        } else {
            keyword = tbInputSearch.getText();
        }

        String Speci;

        if (cbbMajor2.getSelectedItem().toString().equals("Choose Specialized")) {
            Speci = null;

        } else {
            Speci = cbbMajor2.getSelectedItem().toString().split("-")[0];
        }

        ArrayList<Subject> subjectList = teacherDao.SearchSubject(keyword, Speci);

        LoadDataToTable(subjectList);

    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbInputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbInputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbInputSearchActionPerformed

    private void gobackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobackActionPerformed
        Home home = new Home(account);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_gobackActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void tbSubjectIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbSubjectIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSubjectIDActionPerformed

    private void tMMH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tMMH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMMH2ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Subject subject = new Subject();
        if (tbSubjectName.getText() == null || tbSubjectName.getText().toString().length() < 2) {
            JOptionPane.showMessageDialog(this, "SubjectName is not valid");
            return;
        } else {
            subject.Name = tbSubjectName.getText();
        }

        if (tbNumberCredit.getText() == null || tbNumberCredit.getText().equals("") || tbNumberCredit.getText().toString().matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(this, "NumberCredits is not valid");
            return;
        } else {
            subject.NumberOfCredits = Integer.parseInt(tbNumberCredit.getText());
        }

        subject.SpecializedID = cbbMajor.getSelectedItem().toString().split("-")[0];

        boolean result = false;

        if (tbSubjectID.getText() == null || tbSubjectID.getText().equals("")) {
            result = teacherDao.AddSubject(subject);
            clearBoxField();

        } else {
            subject.MMH = tbSubjectID.getText();
            result = teacherDao.UpdateSubject(subject);
        }
        if (result) {
            JOptionPane.showMessageDialog(this, "Operation was success");

            LoadDataToTable();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Something wrong. Try again");
            return;
        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void cbbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSemesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSemesterActionPerformed

    private void btnRegisterSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterSubjectActionPerformed
        // TODO add your handling code here:

        String MMH = tMMH2.getText();
        if (MMH == null || MMH.equals("")) {
            JOptionPane.showMessageDialog(this, "Please choose Subject");
            return;
        }
        String lstSV = taListMSSV.getText();
        if (lstSV == null || lstSV.equals("")) {
            JOptionPane.showMessageDialog(this, "Please add at least one Student");
            return;
        }
        String[] array = lstSV.split("\\,",-1); 

        String SemesterID=cbbSemester.getSelectedItem().toString().split("-")[0];
        
        boolean result=teacherDao.RegisterSubjectForStudent(MMH,array,Integer.parseInt(SemesterID));
        
        if (result) {
            JOptionPane.showMessageDialog(this, "Register Subject For Student success");

            LoadDataToTable();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Something wrong. Try again");
            return;
        }
        
        

    }//GEN-LAST:event_btnRegisterSubjectActionPerformed
    public void clearBoxField() {
        tbSubjectName.setText(null);
        tbNumberCredit.setText(null);
        cbbMajor.setSelectedIndex(0);

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
            java.util.logging.Logger.getLogger(ListSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListSubject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListSubject().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRegisterSubject;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnlogout;
    private javax.swing.JComboBox cbbMajor;
    private javax.swing.JComboBox cbbMajor2;
    private javax.swing.JComboBox cbbSemester;
    private javax.swing.JButton goback;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JTextField tMMH2;
    private javax.swing.JTextArea taListMSSV;
    private javax.swing.JTable tableSubject;
    private javax.swing.JTextField tbInputSearch;
    private javax.swing.JTextField tbNumberCredit;
    private javax.swing.JTextField tbSubjectID;
    private javax.swing.JTextField tbSubjectName;
    // End of variables declaration//GEN-END:variables

}
