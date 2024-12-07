package com.ghw.maplrpc.codec;

import com.ghw.maplrpc.Constant.RPCConstant;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCHeader;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCProtocol;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCRequest;
import com.ghw.maplrpc.POJO.entries.protocolMessage.RPCResponse;
import com.ghw.maplrpc.POJO.enums.RPCTypeEnums;
import com.ghw.maplrpc.exception.SerializerException;
import com.ghw.maplrpc.serialization.api.Serialization;
import com.ghw.maplrpc.utils.SerializerUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author ghost
 * @version 1.0.0
 * 实现自定义的解码器
 */
public class RPCDecode extends ByteToMessageDecoder implements RPCcodec{

    /**
     * 自定义解码器
     * @param channelHandlerContext
     * @param byteBuf
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        // 如果可以读到的数据长度小于消息头的长度，直接返回，说明数据不全
        if (byteBuf.readableBytes() < RPCConstant.HEADER_TOTAL_LEN) {
            return ;
        }
        // 标记当前读取的索引，方便后续重新回到该位置重新读取
        byteBuf.markReaderIndex();
        // 读取魔数，未来进行校验
        short Magic = byteBuf.readShort();
        if (Magic != RPCConstant.MAGIC) {
            // 校验失败，说明数据不安全
            throw new SerializerException("the data is unsafe: the magic number is wrong");
        }
        // 读取消息的类型
        byte MsgType = byteBuf.readByte();
        // 读取消息状态
        byte Status = byteBuf.readByte();
        // 读取消息ID
        long requestId = byteBuf.readLong();
        // 读取序列化的方法并转化为字符串
        ByteBuf serializationTypeBuf = byteBuf.readBytes(SerializerUtils.MAX_SERIALIZATION_TYPE_COUNR);
        String serializationType = SerializerUtils.subString(serializationTypeBuf.toString(CharsetUtil.UTF_8));
        // 读取消息的长度
        int dataLength = byteBuf.readInt();
        if(dataLength > byteBuf.readableBytes()){
            // 长度不对，重新读取
            byteBuf.resetReaderIndex();
            return ;
        }
        // 读取消息体
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);
        // 判断当前消息的类型
        RPCTypeEnums byType = RPCTypeEnums.findByType(MsgType);
        if (byType == null) {
            return ;
        }
        // 设置消息头
        RPCHeader header = new RPCHeader();
        header.setMagic(Magic);
        header.setStatus(Status);
        header.setRequestId(requestId);
        header.setMsgType(MsgType);
        header.setSerializationType(serializationType);
        header.setMsgLen(dataLength);
        //TODO Serialization是扩展点
        Serialization serialization = getJDKSerialization();
        switch (byType) {
            case REQUEST:
                RPCRequest request = serialization.deserializa(data, RPCRequest.class);
                if (request != null) {
                    RPCProtocol<RPCRequest> protocol = new RPCProtocol<>();
                    protocol.setRpcHeader(header);
                    protocol.setBody(request);
                    out.add(protocol);
                }
                break;
            case RESPONSE:
                RPCResponse response = serialization.deserializa(data,
                        RPCResponse.class);
                if (response != null) {
                    RPCProtocol<RPCResponse> protocol = new RPCProtocol<>();
                    protocol.setRpcHeader(header);
                    protocol.setBody(response);
                    out.add(protocol);
                }
                break;
            case HEARTBEAT:
                // TODO
                break;
        }
    }
}
