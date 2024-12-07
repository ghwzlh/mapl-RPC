package com.ghw.maplrpc.test.Comsumer;

import com.alibaba.fastjson.JSONObject;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCProtocol;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCRequest;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCResponse;
import com.ghw.maplrpc.factory.RPCHeaderFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcTestConsumerHandler extends SimpleChannelInboundHandler<RPCProtocol<RPCResponse>> {
    private final Logger logger = LoggerFactory.getLogger(RpcTestConsumerHandler.class);

    /**
     * 发数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("发送数据开始...");
        //模拟发送数据
        RPCProtocol<RPCRequest> protocol = new RPCProtocol<>();
        protocol.setRpcHeader(RPCHeaderFactory.getRequestHeader("jdk"));
        RPCRequest request = new RPCRequest();
        request.setClassName("com.ghw.maplrpc.test.DemoService");
        request.setGroup("ghost");
        request.setMethodName("hello");
        request.setParameters(new Object[]{"ghost"});
        request.setParameterTypes(new Class[]{String.class});
        request.setVersion("1.0.0");
        request.setAsync(false);
        request.setOneway(false);
        protocol.setBody(request);
        logger.info("服务消费者发送的数据===>>>{}", JSONObject.toJSONString(protocol));
        ctx.writeAndFlush(protocol);
        logger.info("发送数据完毕...");
    }

    /**
     * 读取数据
     * @param channelHandlerContext
     * @param protocol
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                RPCProtocol<RPCResponse> protocol) {
        logger.info("服务消费者接收到的数据===>>>{}", JSONObject.toJSONString(protocol));
    }
}