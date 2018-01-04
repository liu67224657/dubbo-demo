package com.ericliu.dubbo.nettydemo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {

    private ServerBootstrap bootstrap;
    private int port;

    public ServerDemo(int port) {
        this.port = port;


    }

    public Channel bind() {
        ExecutorService boss = Executors.newCachedThreadPool(new NamedThreadFactory("NettyServerBoss", true));
        ExecutorService worker = Executors.newCachedThreadPool(new NamedThreadFactory("NettyServerWorker", true));
        //work工作线程数是区间[Runtime.getRuntime().availableProcessors()+1---32]
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(boss, worker, Runtime.getRuntime().availableProcessors() + 1);
        bootstrap = new ServerBootstrap(channelFactory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("handler", new ServerDemo.HelloHandler());
                return pipeline;
            }
        });
        // bind
//        channel = bootstrap.bind(getBindAddress());
        return bootstrap.bind(new InetSocketAddress("0.0.0.0", port));
    }

    public static void main(String[] args) {
        ServerDemo serverDemo = new ServerDemo(1212);
        serverDemo.bind();
        while (true) {
        }
    }

    class HelloHandler extends SimpleChannelHandler {

        // 接收消息
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
                throws Exception {

            String messageReceived = (String) e.getMessage();
            System.out.println(messageReceived);
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

        // 必须是链接已经建立，关闭通道的时候才会触发
        @Override
        public void channelDisconnected(ChannelHandlerContext ctx,
                                        ChannelStateEvent e) throws Exception {
            System.out.println("channelDisconnected");
            super.channelDisconnected(ctx, e);
        }

        // channel关闭的时候触发
        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
                throws Exception {
            System.out.println("channelClosed");
            super.channelClosed(ctx, e);
        }
    }


}

