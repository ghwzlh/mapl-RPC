package com.ghw.maplrpc.handler;

import com.alibaba.fastjson.JSONObject;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCHeader;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCProtocol;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCRequest;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCResponse;
import com.ghw.maplrpc.POJO.enums.RPCTypeEnums;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author ghost
 * @version 1.0.0
 * RPC服务提供者的Handler处理类
 */
public class RPCProviderHandler extends SimpleChannelInboundHandler<RPCProtocol<RPCRequest>> {

    private static final Logger logger = LoggerFactory.getLogger(RPCProviderHandler.class);

    private final Map<String, Object> handlerMap;

    public RPCProviderHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCProtocol<RPCRequest> rpcRequestRPCProtocol) throws Exception {
        logger.info("RPC提供者收到的数据为====>>> " + JSONObject.toJSONString(rpcRequestRPCProtocol));
        logger.info("handlerMap中存放的数据如下所示：");
        for(Map.Entry<String, Object> entry : handlerMap.entrySet()){
            logger.info(entry.getKey() + " === " + entry.getValue());
        }
        RPCHeader header = rpcRequestRPCProtocol.getRpcHeader();
        RPCRequest request = rpcRequestRPCProtocol.getBody();
        //将header中的消息类型设置为响应类型的消息
        header.setMsgType((byte) RPCTypeEnums.RESPONSE.getType());
        //构建响应协议数据
        RPCProtocol<RPCResponse> responseRpcProtocol = new RPCProtocol<>();
        RPCResponse response = new RPCResponse();
        response.setResult("数据交互成功");
        response.setAsync(request.getAsync());
        response.setOneway(request.getOneway());
        responseRpcProtocol.setRpcHeader(header);
        responseRpcProtocol.setBody(response);
        channelHandlerContext.writeAndFlush(responseRpcProtocol);
    }
}