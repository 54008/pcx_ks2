package com.pcx.gui;

import com.pcx.client.handle.testController;

import javax.swing.*;

public class FindRoomGui {

    static JFrame j = new JFrame();
    public FindRoomGui() throws Exception {
        JPanel jPanel = new JPanel();
        j.add(jPanel.add(new testController(j,1)));
        j.setVisible(true);
        j.setDefaultCloseOperation(2);
    }
}
