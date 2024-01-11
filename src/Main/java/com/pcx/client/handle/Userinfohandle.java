package com.pcx.client.handle;

import com.pcx.gui.UserInfoFrame;
import com.pcx.message.RegisterRequestMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
@ChannelHandler.Sharable
public class Userinfohandle extends SimpleChannelInboundHandler<RegisterRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterRequestMessage registerRequestMessage) throws Exception {

        Userinfo.MaInit(new UserInfoFrame(
                registerRequestMessage.getUserid(),
                registerRequestMessage.getNickname(),
                registerRequestMessage.getSex(),
                registerRequestMessage.getData(),
                registerRequestMessage.getProvince_id()
        ));
        Userinfo.reInit(registerRequestMessage);
        Userinfo.loginjFrame.dispose();
    }
}
