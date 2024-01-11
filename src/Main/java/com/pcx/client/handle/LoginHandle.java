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
            JOptionPane.showMessageDialog(null,"success","系统提示",JOptionPane.PLAIN_MESSAGE);


        }else {
            JOptionPane.showMessageDialog(null,"失败","系统提示",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
