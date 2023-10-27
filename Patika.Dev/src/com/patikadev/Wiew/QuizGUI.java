package com.patikadev.Wiew;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Content;
import com.patikadev.Model.Quiz;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_contentName;
    private JButton btn_select;
    private JComboBox cmb_quizList;
    private JTextField fld_quizName;
    private JTextArea txt_question;
    private JButton btn_delete;
    private JButton btn_add;
    private JButton btn_updates;
    private JTable tbl_quizList;
    private JButton yeniQuizButton;
    private JTextField fld_hiddenId;

    private static Content content;
    private DefaultTableModel mdl_myQuizList;
    private Object[] row_myQuizList;

    public QuizGUI(Content content) {
        this.content = content;
        add(wrapper);
        setSize(600, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        lbl_contentName.setText(content.getName());
        fld_hiddenId.setVisible(false);

        mdl_myQuizList=new DefaultTableModel();

        Object[] col_myQuizList = {"Quiz Id","Quiz Name"};
        mdl_myQuizList.setColumnIdentifiers(col_myQuizList);
        row_myQuizList = new Object[col_myQuizList.length];
        //loadContentModel(course.getId());
        tbl_quizList.setModel(mdl_myQuizList);
        tbl_quizList.getTableHeader().setReorderingAllowed(false);
        tbl_quizList.getColumnModel().getColumn(0).setMaxWidth(30);
        loadQuizModel();

        tbl_quizList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try{

                    String select_quizId=tbl_quizList.getValueAt(tbl_quizList.getSelectedRow(),0).toString();


                    fld_hiddenId.setText(select_quizId);
                }catch (Exception exception){

                }
            }
        });
        txt_question.setSize(25, 25);
        txt_question.setLineWrap(true);


        btn_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_quizName) || Helper.isFieldEmpty(txt_question)) {
                Helper.showMsg("fill");
            } else {
                String quizName = fld_quizName.getText();
                String quizText = txt_question.getText();

                if (Quiz.add(quizName,quizText,content.getId())) {
                    Helper.showMsg("done");
                    loadQuizModel();

                    fld_quizName.setText(null);
                    txt_question.setText(null);
                }
            }
        });

        btn_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(fld_hiddenId.getText());
                String name=Quiz.getFetchName(id).getQuiz_name();
                fld_quizName.setText(name);
                String text=Quiz.getFetchText(id).getQuiz_text();
                txt_question.setText(text);
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_hiddenId)) {
                    Helper.showMsg("Pick a quiz to delete");
                } else {
                    if(Helper.confirm("sure")){
                        int quizId = Integer.parseInt(fld_hiddenId.getText());
                        if (Quiz.delete(quizId)) {
                            Helper.showMsg("done");
                            loadQuizModel();
                            fld_hiddenId.setText(null);
                        } else {
                            Helper.showMsg("error");
                        }
                    }
                }
            }
        });
        yeniQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fld_quizName.setText(null);
                txt_question.setText(null);
            }
        });
        btn_updates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(fld_hiddenId.getText());
                String quizName = fld_quizName.getText();
                String quizText = txt_question.getText();
                if(Quiz.update(id,quizName,quizText)){
                    Helper.showMsg("done");
                    loadQuizModel();
                    fld_quizName.setText(null);
                    txt_question.setText(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }
    private void loadQuizModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quizList.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for(Quiz obj : Quiz.getListByContent(content.getId())){
            i=0;
            row_myQuizList[i++] = obj.getId();
            row_myQuizList[i++] = obj.getQuiz_name();

            mdl_myQuizList.addRow(row_myQuizList);

        }
    }
}

