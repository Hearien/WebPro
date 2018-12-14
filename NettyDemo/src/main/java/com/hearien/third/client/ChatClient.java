package com.hearien.third.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName ChatClient
 * @Author WangHaiyang
 * @Date 2018/11/29 14:09
 **/
public class ChatClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap client = new Bootstrap();
            client.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChatClientInitializer());

            Channel channel = client.connect("localhost",8890).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;
                 ) {
                channel.writeAndFlush(br.readLine()+"\r\n");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
