package com.pcx.client.handle;

import com.pcx.message.StatsMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
@ChannelHandler.Sharable
public class StatsHandl extends SimpleChannelInboundHandler<StatsMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StatsMessage statsMessage) throws Exception {
        Userinfo.los = statsMessage.getLos();
        Userinfo.score = statsMessage.getScore();
        Userinfo.vic = statsMessage.getVic();
        Userinfo.win_rate = statsMessage.getWin_rate();
    }
}
