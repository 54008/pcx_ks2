package com.pcx.gui;

import com.pcx.client.Client;
import com.pcx.message.LoginRequestMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


    public class UserUpdata extends JFrame implements ActionListener {

        String sql;

        JLabel l1 = new JLabel("账    号:");
        JLabel l2 = new JLabel("密    码:");
        JLabel l3 = new JLabel("新 密 码:");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JButton b1 = new JButton("修改");
        JButton b2 = new JButton("取消");

        public UserUpdata() {
            // TODO Auto-generated constructor stub
            super("修改密码");
            setBounds(500,200,530,350);

            l1.setBounds(70, 70, 160, 30);
            l1.setFont(new Font("宋体",Font.PLAIN,25));
            l2.setBounds(70, 120, 160, 30);
            l2.setFont(new Font("宋体",Font.PLAIN,25));
            l3.setBounds(70, 170, 160, 30);
            l3.setFont(new Font("宋体",Font.PLAIN,25));

            t1.setBounds(230, 70, 200, 30);
            t1.setFont(new Font("宋体",0, 20));
            t2.setBounds(230, 120, 200, 30);
            t2.setFont(new Font("宋体",0, 20));
            t3.setBounds(230,170,200,30);
            t3.setFont(new Font("宋体",0, 20));

            b1.setBounds(115, 240, 115, 30);
            b1.setFont(new Font("宋体",0,15));
            b1.addActionListener(this);
            b2.setBounds(280, 240, 115, 30);
            b2.setFont(new Font("宋体",0,15));
            b2.addActionListener(this);

            super.add(l1);
            super.add(l2);
            super.add(l3);
            super.add(t1);
            super.add(t2);
            super.add(t3);
            super.add(b1);
            super.add(b2);

            super.setLayout(null);
            super.setVisible(true);
            super.setResizable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Object source = e.getSource();

            if(source == b1) {
                String no = t1.getText().trim();
                String pass = t2.getText().trim();
                String newpass = t3.getText().trim();

                LoginRequestMessage loginRequestMessage = new LoginRequestMessage(no,pass,newpass);
                Client.channel.writeAndFlush(loginRequestMessage);

            }
            if(source == b2) {
                this.dispose();
            }
        }
    }

