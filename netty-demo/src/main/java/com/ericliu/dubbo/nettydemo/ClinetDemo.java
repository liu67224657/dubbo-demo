package com.ericliu.dubbo.nettydemo;

import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.SocketChannel;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioSocketChannel;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClinetDemo {

    private  ClientBootstrap bootstrap;
    private int port;

    public ClinetDemo(int port) {
        this.port = port;
    }

    @SuppressWarnings("resource")
    public ChannelFuture init() {
        // 服务类
        bootstrap = new ClientBootstrap();
        // 线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        // socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));
        // 管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("handler", new ClinetDemo.HiHandler());
                return pipeline;
            }
        });

        // 连接服务端
        return bootstrap.connect(new InetSocketAddress(
                "127.0.0.1", port));


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Channel channel = new ClinetDemo(1212).init().getChannel();
        System.out.println("client start");
        while (true) {
            System.out.println("请输入");
            channel.write(scanner.next());
        }
    }

    class HiHandler extends SimpleChannelHandler {

        // 接收消息
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
                throws Exception {
            // 接收消息
            String s = (String) e.getMessage();
            System.out.println(s);
            super.messageReceived(ctx, e);
        }

        // 捕获异常
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
                throws Exception {
            System.out.println("exceptionCaught");
            super.exceptionCaught(ctx, e);
        }

        // 新连接
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
                throws Exception {
            System.out.println("channelConnected");
            super.channelConnected(ctx, e);
        }

        // 必须【连接已经建立】，关闭通道的时候才会触发
        @Override
        public void channelDisconnected(ChannelHandlerContext ctx,
                                        ChannelStateEvent e) throws Exception {
            System.out.println("channelDisconnected");
            super.channelDisconnected(ctx, e);
        }

        // channel关闭的时候触发（比如服务端没有启动，客户端发起连接的话，就会报这个错）
        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
                throws Exception {
            System.out.println("channelClosed");
            super.channelClosed(ctx, e);
        }
    }

}
