package com.czb.wechat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @描述 实现客户端发送一个消息，服务端返回hello netty！
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
@Component
public class NettyService {
    private static Log log = LogFactory.getLog(NettyService.class);

    private static class SingletionNetty {
        static final NettyService Instance = new NettyService();
    }

    public static NettyService getInstance() {
        return SingletionNetty.Instance;
    }


    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    public NettyService() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)                               // 设置主从线程
                .channel(NioServerSocketChannel.class)                               //设置nio的双向通道
                .childHandler(new HelloServiceInitializer());
    }

    public void start() {
        channelFuture = serverBootstrap.bind(8098);
        log.info("Netty 启动成功");
    }


//    public static void main(String[] args) {
//        //定义一对线程组
//        //主线程组，用于接受客户端连接，但是不做任何处理，跟老板一样，不做事
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        //从线程组，老板会把任务丢给他，让手下线程去处理任务
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            //netty服务器的创建，ServiceBootstrap是一个启动类
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            serverBootstrap.group(bossGroup, workerGroup)                               // 设置主从线程
//                    .channel(NioServerSocketChannel.class)                               //设置nio的双向通道
//                    .childHandler(new HelloServiceInitializer());                        //子处理器，用于处理workerGroup
//            //启动service ， 并且设置8088为启动的端口号，并且设置启动方式为同步
//            ChannelFuture channelFuture = serverBootstrap.bind(8098).sync();
//            //关闭service，获取channel进行关闭，并且设置为同步关闭
//            channelFuture.channel().closeFuture().sync();
//        } catch (Exception e) {
//        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//
//
//    }

}
