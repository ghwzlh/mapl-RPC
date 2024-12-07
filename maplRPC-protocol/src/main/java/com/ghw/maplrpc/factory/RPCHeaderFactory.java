package com.ghw.maplrpc.factory;

import com.ghw.maplrpc.Constant.RPCConstant;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCHeader;
import com.ghw.maplrpc.POJO.enums.RPCTypeEnums;
import com.ghw.maplrpc.id.IdFactory;

/**
 * @author ghost
 * @version 1.0.0
 * 根据传入的序列化对象生成一个消息头对象
 */
public class RPCHeaderFactory {
    // 获取请求头对象
    public static RPCHeader getRequestHeader(String serializationType){
        RPCHeader rpcHeader = new RPCHeader();
        rpcHeader.setMagic(RPCConstant.MAGIC);
        rpcHeader.setMsgType((byte) RPCTypeEnums.REQUEST.getType());
        rpcHeader.setStatus((byte) 0x1);
        rpcHeader.setRequestId(IdFactory.getId());
        rpcHeader.setSerializationType(serializationType);
        return rpcHeader;
    }
}