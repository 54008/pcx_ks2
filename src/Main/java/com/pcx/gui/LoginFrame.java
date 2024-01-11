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

    JLabel l1 = new JLabel("��ӭ������������");
    JLabel l2 = new JLabel("�˺�:");
    JLabel l3 = new JLabel("����:");
    JLabel l4 = new JLabel("�˺Ų����ڣ�");
    JLabel l5 = new JLabel("<HTML><U>" + "ע���˺�" + "</U></HTML>");
    JLabel l6 = new JLabel("<HTML><U>" + "�޸�����" + "</U></HTML>");

    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();

    JButton b1 = new JButton("��¼");
    JButton b2 = new JButton("ע��");


    public LoginFrame() {

        // TODO Auto-generated constructor stub
        //���ñ���
        super("��ӭ������������");//��������Ϊ��ӭ��½ҽԺ��������ϵͳ�Ĵ���
        username = new String();
        password = new String();
        setBounds(300, 100, 910, 500);//���ô�������Ļ�ϵ�λ�ü���С
        String path = "";
        ImageIcon backgroundimage = new ImageIcon(path);//ʹ��ǩ��ͼƬ���ô˴�������ͼƬ
        JLabel jLabel = new JLabel(backgroundimage);//��ǩΪ�û���ʾ��Ϣ
        jLabel.setBounds(0, 0, this.getWidth(), this.getHeight());//��ǩ�ڴ����ϵ�λ�ü���С
        JPanel jPanel1 = (JPanel) this.getContentPane();//��ʼ��һ��������壬�����ſ��԰�����������ó�͸����    jPanel1����������Ҫ��ӵ�ͼƬ�����������ǵ����
        jPanel1.setOpaque(false);//ʹ���������ʾ���е�ĳЩ���أ��������������������ֳ�����������͸��
        jPanel1.setLayout(null);
        this.getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
        setVisible(true);

        // ��½��������
        l1.setBounds(450, 70, 450, 35);
        l1.setFont(new Font("����", Font.BOLD, 30));
        l2.setBounds(470, 140, 70, 25);
        l2.setFont(new Font("����", Font.BOLD, 23));
        l3.setBounds(470, 200, 70, 25);
        l3.setFont(new Font("����", Font.BOLD, 23));
        l4.setBounds(120, 200, 150, 20);
        l4.setFont(new Font("����", Font.BOLD, 23));

        l5.setBounds(730, 140, 70, 25);
        l5.setFont(new Font("΢���ź�", Font.BOLD, 15));
        l5.setForeground(Color.blue);
        l5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l5.addMouseListener(new RegisterMouseListerner());

        l6.setBounds(730, 200, 70, 25);
        l6.setFont(new Font("΢���ź�", Font.BOLD, 15));
        l6.setForeground(Color.blue);
        l6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l6.addMouseListener(new UserUpdataListerner());

        t1.setBounds(540, 140, 160, 25);
        t1.setFont(new Font("����", 0, 18));
        t2.setBounds(540, 200, 160, 25);
        t2.setFont(new Font("����", 0, 18));

        b1.setBounds(540, 260, 70, 30);
        b1.setFont(new Font("����", 0, 15));
        b1.addActionListener(this);
        b2.setBounds(660, 260, 70, 30);
        b2.setFont(new Font("����", 0, 15));
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

        //�������������Ϸ��Ĺر�ͼ��ʱ,����������windowClosing����,����ڸ÷�����ʹ��System.exit(0)�˳����������
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
    //�¼�Դ����ActionEvent�¼��󣬼��������ýӿ��еĴ˷����Է������¼���������
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();//��������¼������Ķ���
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