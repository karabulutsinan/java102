package com.patikadev.Wiew;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;

public class SignUpGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wTop;
    private JPanel wBottom;
    private JTextField fld_signup_name;
    private JTextField fld_signup_uname;
    private JTextField fld_signup_pass;
    private JButton kayıtOlButton;

    public SignUpGUI(){
        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        kayıtOlButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_signup_name)||Helper.isFieldEmpty(fld_signup_uname)||Helper.isFieldEmpty(fld_signup_pass)){
                Helper.showMsg("fill");
            }else{
                if (User.add(fld_signup_name.getText(),fld_signup_uname.getText(),fld_signup_pass.getText(),"student")){
                    Helper.showMsg("done");
                    dispose();
                }
            }
        });
    }
}

