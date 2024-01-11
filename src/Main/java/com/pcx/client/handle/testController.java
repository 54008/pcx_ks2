package com.pcx.client.handle;



import com.pcx.Game.GamePanel;
import com.pcx.client.Client;
import com.pcx.message.CreateRoomMessage;
import com.pcx.message.FindRoomRequestMessage;
import com.pcx.message.GoRoomRequestMessage;
import com.pcx.message.OutRoomRequestMessage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class testController extends Box {


    final int WIDTH = 400;
    final int HEIGHT = 500;
    static  JTable jTable;
    Box topBox = Box.createHorizontalBox();

    static Vector<String> title = new Vector<>();
    static Vector<Vector> data = new Vector<>();
    static DefaultTableModel tableModel;

    public testController(JFrame f, int axis) throws Exception {

        super(axis);
        f.setSize(WIDTH,HEIGHT);
        JButton insertCourse = new JButton("加入");
        JButton create = new JButton("创建");
        JButton refresh = new JButton("刷新");
        JButton deroom = new JButton("关闭房间");
        deroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OutRoomRequestMessage outRoomRequestMessage = new OutRoomRequestMessage();
                outRoomRequestMessage.setUsername(Userinfo.register.getUserid());
                Client.channel.writeAndFlush(outRoomRequestMessage);
                GamePanel.setIslocke(true);
            }
        });
        insertCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getRowCount() == 0)
                    return;
                String valueAt = (String) jTable.getValueAt(jTable.getSelectedRow(), 0);
                if (Userinfo.register.getUserid().equals(valueAt )){
                    JOptionPane.showMessageDialog(null,"不要选择自己房间","系统提示",JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                Client.setGroup(valueAt);
                System.out.println(valueAt);
                Client.channel.writeAndFlush(new GoRoomRequestMessage(Userinfo.register.getUserid(),valueAt));
                Client.setPlagid(2);

            }
        });

        insertCourse.setBorderPainted(false);
        topBox.add(Box.createHorizontalStrut(80));
        topBox.add(insertCourse);
        topBox.add(create);
        create.setBorderPainted(false);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreateRoomMessage createRoomMessage = new CreateRoomMessage();
                createRoomMessage.setUsername(Userinfo.register.getUserid());
                Client.setPlagid(1);
                Client.setGroup(Userinfo.register.getUserid());
                System.out.println(Client.getGroup());

                Client.channel.writeAndFlush(createRoomMessage);
                GamePanel.setIslocke(false);
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindRoomRequestMessage findRoomRequestMessage = new FindRoomRequestMessage(Userinfo.register.getUserid());

                Client.channel.writeAndFlush(findRoomRequestMessage);
            }
        });
        refresh.setBorderPainted(false);
        topBox.add(refresh);
        topBox.add(deroom);
        topBox.add(Box.createHorizontalStrut(40));
        topBox.add(Box.createHorizontalStrut(40));

        JPanel centerPanel = new JPanel();
        centerPanel.setMaximumSize(new Dimension(500, 150));
        title.add("玩家");
        title.add("房间人数");
        tableModel = new DefaultTableModel(data, title);
        jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);





        JScrollPane bottomPane = new JScrollPane(jTable);

        bottomPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        topBox.setMaximumSize(new Dimension(WIDTH, 60));

        this.add(Box.createVerticalStrut(10));
        this.add(topBox);
        this.add(Box.createVerticalStrut(5));
        this.add(centerPanel);
        this.add(bottomPane);

    }
}
