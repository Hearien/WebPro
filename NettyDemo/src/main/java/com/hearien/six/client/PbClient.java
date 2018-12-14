package com.hearien.six.client;

import com.hearien.seco.client.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName PbClient
 * @Author WangHaiyang
 * @Date 2018/12/13 15:44
 **/
public class PbClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap client = new Bootstrap();
            client.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new PbClientInitializer());

            ChannelFuture future = client.connect("localhost",8890).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
