package com.pcx.client.handle;

import com.pcx.message.LoginResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.swing.*;
@ChannelHandler.Sharable
public class LoginHandle extends SimpleChannelInboundHandler<LoginResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponseMessage registerResponseMessage) throws Exception {
        if (registerResponseMessage.isSuccess()){
            JOptionPane.showMessageDialog(null,"success","ϵͳ��ʾ",JOptionPane.PLAIN_MESSAGE);


        }else {
            JOptionPane.showMessageDialog(null,"ʧ��","ϵͳ��ʾ",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
