package com.patikadev.Wiew;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Course;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_educatorWelcome;
    private JTabbedPane tab_educator;
    private JTable tbl_myCourses;
    private JScrollPane scrl_myCourses;
    private JButton selectButton;
    private JTextField fld_courseName;
    private JTextField fld_courseId;
    private JButton btn_Logout;
    private DefaultTableModel mdl_myCourseList;
    private Object[] row_myCourseList;
    private final User user;

    public EducatorGUI(User user){
        this.user=user;
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        fld_courseId.setVisible(false);
        lbl_educatorWelcome.setText("Hoşgeldiniz "+ user.getName());

        mdl_myCourseList = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0|| column==1) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };


        mdl_myCourseList = new DefaultTableModel();
        Object[] col_myCourseList ={"Kurs Id","Kurs Adı"};
        mdl_myCourseList.setColumnIdentifiers(col_myCourseList);
        row_myCourseList = new Object[col_myCourseList.length];
        loadCourseModel();
        tbl_myCourses.setModel(mdl_myCourseList);
        tbl_myCourses.getTableHeader().setReorderingAllowed(false);
        tbl_myCourses.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_myCourses.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_courseName= tbl_myCourses.getValueAt(tbl_myCourses.getSelectedRow(), 1).toString();
                String select_courseId = tbl_myCourses.getValueAt(tbl_myCourses.getSelectedRow(), 0).toString();
                fld_courseName.setText(select_courseName);
                fld_courseId.setText(select_courseId);
            } catch (Exception exception) {

            }


        });


        btn_Logout.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI=new LoginGUI();
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_courseName)){
                    Helper.showMsg("fill");
                }else{
                    Course c = Course.getFetch(Integer.parseInt(fld_courseId.getText()));
                    ContentGUI contentGUI= new ContentGUI(c);
                }
            }
        });
    }
    public static ArrayList<Course> getListByUser(int user_id){
        ArrayList<Course> courseList = new ArrayList<>();

        Course obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = " + user_id);
            while(rs.next()){
                int id = rs.getInt("id");
                int userId= rs.getInt("user_id");
                int pathId= rs.getInt("patika_id");
                String name= rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,userId,pathId,name,lang);
                courseList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;

    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_myCourses.getModel();
        clearModel.setRowCount(0);
        for(Course obj : getListByUser(user.getId())){
            row_myCourseList[0]=obj.getId();
            row_myCourseList[1]=obj.getName();
            mdl_myCourseList.addRow(row_myCourseList);
        }
    }


}
