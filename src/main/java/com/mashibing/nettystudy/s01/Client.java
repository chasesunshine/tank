package com.mashibing.nettystudy.s01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture f = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8888);

            // 监听 ChannelFuture 成功不成功
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(!channelFuture.isSuccess()){
                        System.out.println("not connect!");
                    }else {
                        System.out.println("connect!");
                    }
                }
            });

            // 为了防止  f.addListener 不被调用 - 整个main方法结束的情况 ，所以加 f.sync()
            f.sync();

            System.out.println("...");
        } finally {
            group.shutdownGracefully();
        }

    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println(ch + "只是再通道初始化的时候调用");

        // 这里还有一个监听 observer 套 observer
//        ch.pipeline().addLast();
    }
}
