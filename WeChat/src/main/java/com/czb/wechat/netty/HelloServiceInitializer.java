package com.czb.wechat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @描述 初始化器，channel初始化后会执行里面的相应的初始化方法；
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class HelloServiceInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //通过SocketChannel获取管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        //通过管道，添加headler，
        //HttpServerCodec是netty自己提供的handler，可以理解为拦截器
        //当请求到服务端，我们需要做解码，相应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());


        //自定义的headler，返回"hello netty"
        pipeline.addLast("customHeadler", new customHeadler());
    }
}
