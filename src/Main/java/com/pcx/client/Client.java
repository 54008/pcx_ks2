package com.pcx.client;

import com.pcx.client.config.Config;
import com.pcx.client.decode.MessageCodecSharable;

import com.pcx.client.handle.*;

import com.pcx.protocol.ProcotolFrameDecode;
import io.netty.bootstrap.Bootstrap;


import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.swing.*;


public class Client {
    public static  Channel channel = null;
    public static  String group ;
    public static  int plagid;

    public static int getPlagid() {
        return plagid;
    }

    public static void setPlagid(int plagid) {
        Client.plagid = plagid;
    }

    public static void setGroup(String group) {
        Client.group = group;
    }

    public static String getGroup() {
        return group;
    }

    public Client() {

        NioEventLoopGroup group = new NioEventLoopGroup();
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        LoginHandle loginHandle = new LoginHandle();
        RegHandler regHandler = new RegHandler();
        Userinfohandle userinfohandle = new Userinfohandle();
        FindHandle findHandle = new FindHandle();
        RecordHandle recordHandle = new RecordHandle();
        StatsHandl statsHandl = new StatsHandl();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ProcotolFrameDecode());
                    socketChannel.pipeline().addLast(messageCodecSharable);
                    socketChannel.pipeline().addLast(loginHandle);
                    socketChannel.pipeline().addLast(regHandler);
                    socketChannel.pipeline().addLast(userinfohandle);
                    socketChannel.pipeline().addLast(findHandle);
                    socketChannel.pipeline().addLast(recordHandle);
                    socketChannel.pipeline().addLast(statsHandl);
                    socketChannel.pipeline().addLast("client handler",new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("active");

                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            int i =1;
                        }
                    });
                }
            });
            channel = bootstrap.connect("127.0.0.1", Config.getServerPort()).sync().channel();
            channel.closeFuture().sync();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }
    }
}

