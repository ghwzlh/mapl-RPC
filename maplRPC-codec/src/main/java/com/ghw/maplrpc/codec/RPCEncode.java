package com.ghw.maplrpc.codec;

import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCHeader;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCProtocol;
import com.ghw.maplrpc.serialization.api.Serialization;
import com.ghw.maplrpc.utils.SerializerUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RPCEncode extends MessageToByteEncoder<RPCProtocol<Object>> implements RPCcodec{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RPCProtocol<Object> objectRPCProtocol, ByteBuf byteBuf) throws Exception {
        RPCHeader rpcHeader =  objectRPCProtocol.getRpcHeader();
        byteBuf.writeShort(rpcHeader.getMagic());
        byteBuf.writeByte(rpcHeader.getMsgType());
        byteBuf.writeByte(rpcHeader.getStatus());
        byteBuf.writeLong(rpcHeader.getRequestId());
        String serializationType = rpcHeader.getSerializationType();
        // todo 扩展不同序列化方法
        Serialization serialization = getJDKSerialization();
        byteBuf.writeBytes(SerializerUtils.paddingString(serializationType).getBytes("UTF-8"));
        byte[] serialize = serialization.serialize(objectRPCProtocol.getBody());
        byteBuf.writeInt(serialize.length);
        byteBuf.writeBytes(serialize);
    }
}