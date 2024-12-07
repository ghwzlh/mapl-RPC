package com.ghw.maplrpc.POJO.entries.protocolMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ghost
 * @version 1.0.0
 * 消息头实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RPCHeader implements Serializable {

    private static final long serialVersionUID = -2482746684297809863L;

    /**
     * 魔数 2字节
     */
    private short magic;
    /**
     * 报文类型 1字节
     */
    private byte msgType;
    /**
     * 状态 1字节
     */
    private byte status;

    /**
     * 消息 ID 8字节
     */
    private long requestId;

    /**
     * 序列化类型16字节，不足16字节后面补0，约定序列化类型长度最多不能超过16
     */
    private String serializationType;

    /**
     * 消息长度 4字节
     */
    private int msgLen;
}