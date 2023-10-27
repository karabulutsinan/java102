package com.patikadev.Wiew;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Course;
import com.patikadev.Model.Content;
import com.patikadev.Model.User;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContentGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_courseName;
    private JTextField fld_contentName;
    private JButton addButton;
    private JButton deleteButton;
    private JButton searchButton;
    private DefaultTableModel mdl_myContentList;
    private Object[] row_myContentList;
    private JTable tbl_contentList;
    private JScrollPane scrl_contentList;
    private JTextField fld_hiddenDelete;
    private Course course;
    private JPopupMenu quizMenu;

    public ContentGUI(Course course) {
        this.course = course;
        add(wrapper);
        setSize(1000, 500);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        fld_hiddenDelete.setVisible(false);
        lbl_courseName.setText(course.getName());

        mdl_myContentList = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 4) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };


        quizMenu = new JPopupMenu();
        JMenuItem addQuiz = new JMenuItem("Add/Update Quiz");
        quizMenu.add(addQuiz);

        addQuiz.addActionListener((ActionListener) e -> {
            Content c = Content.getFetch(Integer.parseInt(fld_hiddenDelete.getText()));
            QuizGUI qGUI = new QuizGUI(c);

        });


        Object[] col_myContentList = {"Content Id", "Content Name", "Description", "Youtube Link", "Quiz"};
        mdl_myContentList.setColumnIdentifiers(col_myContentList);
        row_myContentList = new Object[col_myContentList.length];
        loadContentModel(course.getId());
        tbl_contentList.setModel(mdl_myContentList);
        tbl_contentList.setComponentPopupMenu(quizMenu);
        tbl_contentList.getTableHeader().setReorderingAllowed(false);
        tbl_contentList.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_contentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selectedRow = tbl_contentList.rowAtPoint(point);
                tbl_contentList.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        searchButton.addActionListener(e -> {
            String searchKeyLow = fld_contentName.getText().toLowerCase();
            String searchKeyUp = fld_contentName.getText().toUpperCase();

            DefaultTableModel clearModel = (DefaultTableModel) tbl_contentList.getModel();
            clearModel.setRowCount(0);
            int i = 0;
            for (Content obj : Content.getListByCourse(course.getId())) {
                if (obj.getName().toLowerCase().contains(searchKeyLow) || obj.getName().toUpperCase().contains(searchKeyUp)) {
                    i = 0;
                    row_myContentList[i++] = obj.getId();
                    row_myContentList[i++] = obj.getName();
                    row_myContentList[i++] = obj.getDescription();
                    row_myContentList[i++] = obj.getYoutubeLink();
                    row_myContentList[i] = obj.getQuizId();
                    mdl_myContentList.addRow(row_myContentList);
                }
            }
        });
        addButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_contentName)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_contentName.getText();

                if (Content.add(name)) {
                    Helper.showMsg("done");
                    loadContentModel(course.getId());
                    fld_contentName.setText(null);
                }
            }
        });
        deleteButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hiddenDelete)) {
                Helper.showMsg("fill");
            } else {
                if(Helper.confirm("sure")){
                    int content_id = Integer.parseInt(fld_hiddenDelete.getText());
                    if (Content.delete(content_id)) {
                        Helper.showMsg("done");
                        loadContentModel(course.getId());
                        fld_contentName.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        tbl_contentList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_content_id = tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(), 0).toString();
                fld_hiddenDelete.setText(select_content_id);
                String select_content_name = tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(), 1).toString();
                fld_contentName.setText(select_content_name);
            } catch (Exception exception) {

            }
        });

        tbl_contentList.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType()==TableModelEvent.UPDATE){
                    int contentId = Integer.parseInt(tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(),0).toString());
                    String contentName = tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(),1).toString();
                    String description = tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(),2).toString();
                    String youtubeLink = tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(),3).toString();
                    int quizId = Integer.parseInt(tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(),4).toString());

                    if(Content.update(contentId,contentName,description,youtubeLink,course.getId())){
                        Helper.showMsg("done");
                    }
                    loadContentModel(course.getId());
                }
            }
        });
    }


    private void loadContentModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_contentList.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Content obj : Content.getListByCourse(id)) {
            i = 0;
            row_myContentList[i++] = obj.getId();
            row_myContentList[i++] = obj.getName();
            row_myContentList[i++] = obj.getDescription();
            row_myContentList[i++] = obj.getYoutubeLink();
            row_myContentList[i] = obj.getQuizId();
            mdl_myContentList.addRow(row_myContentList);

        }
    }
}
