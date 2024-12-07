package com.ghw.maplrpc.test.Comsumer;

import com.ghw.maplrpc.codec.RPCDecode;
import com.ghw.maplrpc.codec.RPCEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class RpcTestConsumerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new RPCEncode());
        cp.addLast(new RPCDecode());
        cp.addLast(new RpcTestConsumerHandler());
    }
}