package com.pcx.client.handle;

import com.pcx.message.CreateRoomResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.swing.*;

public class CreateRoomHandle extends SimpleChannelInboundHandler<CreateRoomResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateRoomResponse createRoomResponse) throws Exception {
        if (createRoomResponse.isSuccess()) {
            JOptionPane.showMessageDialog(null,createRoomResponse.getReason(),"系统提示",JOptionPane.PLAIN_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null,createRoomResponse.getReason(),"系统提示",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
