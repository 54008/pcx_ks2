package com.pcx.gui;

import com.pcx.client.Client;
import com.pcx.message.LoginRequestMessage;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Data
public class LoginFrame extends JFrame implements ActionListener {



    static String username ;
    static  String password ;

    JLabel l1 = new JLabel("欢迎来到网络象棋");
    JLabel l2 = new JLabel("账号:");
    JLabel l3 = new JLabel("密码:");
    JLabel l4 = new JLabel("账号不存在！");
    JLabel l5 = new JLabel("<HTML><U>" + "注册账号" + "</U></HTML>");
    JLabel l6 = new JLabel("<HTML><U>" + "修改密码" + "</U></HTML>");

    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();

    JButton b1 = new JButton("登录");
    JButton b2 = new JButton("注册");


    public LoginFrame() {

        // TODO Auto-generated constructor stub
        //设置标题
        super("欢迎来到网络象棋");//创建标题为欢迎登陆医院病房管理系统的窗口
        username = new String();
        password = new String();
        setBounds(300, 100, 910, 500);//设置窗口在屏幕上的位置及大小
        String path = "";
        ImageIcon backgroundimage = new ImageIcon(path);//使标签有图片，用此创建背景图片
        JLabel jLabel = new JLabel(backgroundimage);//标签为用户提示信息
        jLabel.setBounds(0, 0, this.getWidth(), this.getHeight());//标签在窗口上的位置及大小
        JPanel jPanel1 = (JPanel) this.getContentPane();//初始化一个内容面板，这样才可以把内容面板设置成透明的    jPanel1下面是我们要添加的图片，上面是我们的组件
        jPanel1.setOpaque(false);//使组件不会显示其中的某些像素，允许组件下面的像素显现出来，即设置透明
        jPanel1.setLayout(null);
        this.getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
        setVisible(true);

        // 登陆界面配置
        l1.setBounds(450, 70, 450, 35);
        l1.setFont(new Font("宋体", Font.BOLD, 30));
        l2.setBounds(470, 140, 70, 25);
        l2.setFont(new Font("宋体", Font.BOLD, 23));
        l3.setBounds(470, 200, 70, 25);
        l3.setFont(new Font("宋体", Font.BOLD, 23));
        l4.setBounds(120, 200, 150, 20);
        l4.setFont(new Font("宋体", Font.BOLD, 23));

        l5.setBounds(730, 140, 70, 25);
        l5.setFont(new Font("微软雅黑", Font.BOLD, 15));
        l5.setForeground(Color.blue);
        l5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l5.addMouseListener(new RegisterMouseListerner());

        l6.setBounds(730, 200, 70, 25);
        l6.setFont(new Font("微软雅黑", Font.BOLD, 15));
        l6.setForeground(Color.blue);
        l6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l6.addMouseListener(new UserUpdataListerner());

        t1.setBounds(540, 140, 160, 25);
        t1.setFont(new Font("宋体", 0, 18));
        t2.setBounds(540, 200, 160, 25);
        t2.setFont(new Font("宋体", 0, 18));

        b1.setBounds(540, 260, 70, 30);
        b1.setFont(new Font("宋体", 0, 15));
        b1.addActionListener(this);
        b2.setBounds(660, 260, 70, 30);
        b2.setFont(new Font("宋体", 0, 15));
        b2.addActionListener(this);

        super.add(l1);
        super.add(l2);
        super.add(l3);
        super.add(l5);
        super.add(l6);
        super.add(t1);
        super.add(t2);
        super.add(b1);
        super.add(b2);
        super.setLayout(null);
        super.setVisible(true);

        //当单击窗口右上方的关闭图标时,监视器调用windowClosing方法,如果在该方法中使用System.exit(0)退出程序的运行
        super.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                System.exit(0);
            }

        });
        super.setResizable(false);
    }


    @Override
    //事件源触发ActionEvent事件后，监视器调用接口中的此方法对发生的事件做出处理
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();//返回添加事件监听的对象
        if (source == b1) {
            LoginRequestMessage loginRequestMessage =
                    new LoginRequestMessage(t1.getText(),t2.getText());
            Client.channel.writeAndFlush(loginRequestMessage);
            username = t1.getText();
            password = t2.getText();


            if (source == b2) {
                System.exit(0);
            }
        }
    }

}