package com.pcx.gui;

import com.pcx.client.Client;
import com.pcx.message.RegisterRequestMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends JFrame implements ActionListener {

    private JFrame frame;
    JSpinner birthdateSpinner = new JSpinner(new SpinnerDateModel());
    private JTextField text_No;
    private JTextField text_Password;
    private JTextField text_Name;
    private JComboBox comboBox_Sex;
    private JComboBox comboBox_Title;
    private JTextField text_Age;
    private JTextField text_Tel;
    private JComboBox comboBox_Dept;
    private JButton button1 = new JButton("注册");
    private JButton button2 = new JButton("取消");
    private String sql;

    public Register() {
        // TODO Auto-generated constructor stub
        frame = new JFrame("注册账号");
        frame.setBounds(380,100,600,600);
        frame.getContentPane().setLayout(null);

        JLabel label_No = new JLabel("账   号");
        label_No.setFont(new Font("宋体", Font.PLAIN, 25));
        label_No.setBounds(120, 50, 100, 30);
        frame.getContentPane().add(label_No);
        text_No = new JTextField();
        text_No.setFont(new Font("宋体", Font.PLAIN, 20));
        text_No.setBounds(240, 50, 130, 30);
        frame.getContentPane().add(text_No);;
        text_No.setColumns(10);

        JLabel label_Password = new JLabel("密    码");
        label_Password.setFont(new Font("宋体", Font.PLAIN, 25));
        label_Password.setBounds(120,100,120,30);
        frame.getContentPane().add(label_Password);
        text_Password = new JTextField();
        text_Password.setFont(new Font("宋体", Font.PLAIN, 20));
        text_Password.setBounds(240,100,130,30);
        frame.getContentPane().add(text_Password);
        text_Password.setColumns(10);

        JLabel label_Name = new JLabel("名    称");
        label_Name.setFont(new Font("宋体", Font.PLAIN, 25));
        label_Name.setBounds(120,150,120,30);
        frame.getContentPane().add(label_Name);
        text_Name = new JTextField();
        text_Name.setFont(new Font("宋体", Font.PLAIN, 20));
        text_Name.setBounds(240,150,130,30);
        frame.getContentPane().add(text_Name);
        text_Name.setColumns(10);

        JLabel label_Sex = new JLabel("性    别");
        label_Sex.setFont(new Font("宋体", Font.PLAIN, 25));
        label_Sex.setBounds(120,200,120,30);
        frame.getContentPane().add(label_Sex);
        comboBox_Sex = new JComboBox();
        comboBox_Sex.setFont(new Font("宋体", Font.PLAIN, 20));
        comboBox_Sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
        comboBox_Sex.setToolTipText("男");
        comboBox_Sex.setBounds(240,200,130,30);
        frame.getContentPane().add(comboBox_Sex);


        JLabel label_Bri = new JLabel("生  日");
        label_Bri.setFont(new Font("宋体", Font.PLAIN, 25));
        label_Bri.setBounds(120,250,120,30);
        frame.getContentPane().add(label_Bri);


        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthdateSpinner, "yyyy-MM-dd");
        birthdateSpinner.setEditor(dateEditor);
        birthdateSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
        birthdateSpinner.setBounds(240,250,130,30);
        frame.getContentPane().add(birthdateSpinner);

        JLabel label_prov = new JLabel("省份");
        label_prov.setFont(new Font("宋体", Font.PLAIN, 25));
        label_prov.setBounds(120,300,120,30);
        frame.getContentPane().add(label_prov);
        comboBox_Dept = new JComboBox();
        comboBox_Dept.setFont(new Font("宋体", Font.PLAIN, 20));
        comboBox_Dept.setModel(new DefaultComboBoxModel(new String[] {"未知"}));
        comboBox_Dept.setToolTipText("未知");
        comboBox_Dept.setBounds(240,300,130,30);
        frame.getContentPane().add(comboBox_Dept);

        JLabel label_repass = new JLabel("重复密码");
        label_repass.setFont(new Font("宋体", Font.PLAIN, 25));
        label_repass.setBounds(120,350,120,30);
        frame.getContentPane().add(label_repass);
        text_Age = new JTextField();
        text_Age.setFont(new Font("宋体", Font.PLAIN, 20));
        text_Age.setBounds(240,350,130,30);
        frame.getContentPane().add(text_Age);
        text_Age.setColumns(10);

        button1.setBounds(90, 470, 120, 40);
        frame.getContentPane().add(button1);
        button1.addActionListener(this);

        button2.setBounds(280, 470, 120, 40);
        frame.getContentPane().add(button2);
        button2.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();

            String id = text_No.getText().trim();
            String password = text_Password.getText().trim();
            String name = text_Name.getText().trim();
            String repass = text_Age.getText().trim();
            String sex = (String) comboBox_Sex.getSelectedItem();
        Date s = (Date) birthdateSpinner.getValue();
        // 使用 SimpleDateFormat 将 Date 格式化为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(s);
        String deptname =(String) comboBox_Dept.getSelectedItem();
        System.out.println(id);
        System.out.println(password);
        System.out.println(name);
        System.out.println(repass);
        System.out.println(formattedDate);
        System.out.println(sex);

        System.out.println(deptname);
        Client.channel.writeAndFlush(new RegisterRequestMessage(id,password,name,sex,formattedDate,0));
        if(source == button2) {
            frame.dispose();
        }
    }
}
