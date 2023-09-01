package com.mashibing.tank.net;

import com.mashibing.tank.Tank;
import com.mashibing.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class Client {
	public static final Client INSTANCE = new Client();
	private Channel channel = null;

	private Client() {}
	public void connect() {
		EventLoopGroup group = new NioEventLoopGroup(1);

		Bootstrap b = new Bootstrap();

		try {
			ChannelFuture f = b.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
					.connect("localhost", 8888);

			f.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (!future.isSuccess()) {
						System.out.println("not connected!");
					} else {
						System.out.println("connected!");
						// initialize the channel
						channel = future.channel();
					}
				}
			});

			f.sync();
			// wait until close
			f.channel().closeFuture().sync();
			System.out.println("connection closed!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public void send(TankJoinMsg msg) {
		channel.writeAndFlush(msg);
	}

	public static void main(String[] args) throws Exception {
		Client c = new Client();
		c.connect();
	}

	public void closeConnect() {
//		this.send("_bye_");
		//channel.close();
	}
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
				.addLast(new TankJoinMsgEncoder())
				.addLast(new TankJoinMsgDecoder())
				.addLast(new ClientHandler());
	}

}

class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
		msg.handle();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
	}

}
