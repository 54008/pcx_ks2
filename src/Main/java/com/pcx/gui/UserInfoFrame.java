package com.pcx.gui;

import com.pcx.client.Client;
import com.pcx.client.handle.Userinfo;
import com.pcx.message.FindRoomRequestMessage;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoFrame extends JFrame {

    private String userid;
    private String nickname;
    private String sex;
    private String data;
    private int province_id;

    public UserInfoFrame(String userid, String nickname, String sex, String data, int province_id) {
        this.userid = userid;
        this.nickname = nickname;
        this.sex = sex;
        this.data = data;
        this.province_id = province_id;

        initializeUI();
    }

    /*private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("User Information");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("UserID:"));
        panel.add(new JLabel(userid));

        panel.add(new JLabel("Nickname:"));
        panel.add(new JLabel(nickname));

        panel.add(new JLabel("Sex:"));
        panel.add(new JLabel(sex));

        panel.add(new JLabel("Data:"));
        panel.add(new JLabel(data));

        panel.add(new JLabel("Province ID:"));
        panel.add(new JLabel(String.valueOf(province_id)));

        panel.add(new JLabel("score:"));
        panel.add(new JLabel(String.valueOf(Userinfo.getScore())));

        panel.add(new JLabel("vic :"));
        panel.add(new JLabel(String.valueOf(Userinfo.getVic())));

        panel.add(new JLabel("loss :"));
        panel.add(new JLabel(String.valueOf(Userinfo.getLos())));

        panel.add(new JLabel("vic rate:"));
        panel.add(new JLabel(String.valueOf(Userinfo.getWin_rate())));


        JButton button1 = new JButton("room");
        JButton button2 = new JButton("exit");

        button1.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.channel.writeAndFlush(new FindRoomRequestMessage(userid));
                new FindRoomGui();

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(UserInfoFrame.this, "Button 2 clicked!");
            }
        });

        panel.add(button1);
        panel.add(button2);

        add(panel);
        panel.setSize(500,600);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);*/

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("User Information");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        addRow(panel, "UserID:", userid);

        addRow(panel, "Nickname:", nickname);
        addRow(panel, "Sex:", sex);
        addRow(panel, "Data:", data);
        addRow(panel, "Province ID:", String.valueOf(province_id));
        addRow(panel, "Score:", String.valueOf(Userinfo.getScore()));
        addRow(panel, "Vic:", String.valueOf(Userinfo.getVic()));
        addRow(panel, "Loss:", String.valueOf(Userinfo.getLos()));
        addRow(panel, "Vic Rate:", String.valueOf(Userinfo.getWin_rate()));

        JButton button1 = new JButton("Room");
        JButton button2 = new JButton("修改密码");

        button1.addActionListener(e -> {
            Client.channel.writeAndFlush(new FindRoomRequestMessage(userid));
            try {
                new FindRoomGui();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        button2.addActionListener(e ->
                new Register()
                );


        panel.add(button1);
        panel.add(button2);

        add(panel);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addRow(JPanel panel, String label, String value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(value));
    }


}
