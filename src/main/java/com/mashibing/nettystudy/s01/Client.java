package com.mashibing.nettystudy.s01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class Client {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup(1);

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

            // 不加这句话 client就结束了，客户端关闭掉了，它们之间的channel断了
            f.channel().closeFuture().sync();
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
        ch.pipeline().addLast(new ClientHandler());
    }
}
class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(),bytes);
            System.out.println(new String(bytes));

//            System.out.println(buf);
//            System.out.println(buf.refCnt());
        }finally {
            if(buf != null){
                ReferenceCountUtil.release(buf);
//                System.out.println(buf.refCnt());
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // channel第一次连上可用，写出一个字符串
        ByteBuf buf = Unpooled.copiedBuffer("hello".getBytes());
        ctx.writeAndFlush(buf);
    }
}