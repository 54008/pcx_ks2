package com.pcx.client.handle;

import com.pcx.message.RegisterResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.swing.*;

public class RegHandler extends SimpleChannelInboundHandler<RegisterResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterResponseMessage registerResponseMessage) throws Exception {
        if (registerResponseMessage.isSuccess()){
            JOptionPane.showMessageDialog(null,"注册成功","系统提示",JOptionPane.PLAIN_MESSAGE);

        }else {
            JOptionPane.showMessageDialog(null, "账号存在", "系统提示",JOptionPane.PLAIN_MESSAGE);
        }

    }
}
