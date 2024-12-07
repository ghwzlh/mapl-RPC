package com.ghw.maplrpc.POJO.enums;

/**
 * @author ghost
 * @version 1.0.0
 * 消息（协议）的类型
 */
public enum RPCTypeEnums {
    // 请求消息
    REQUEST(1),
    // 响应消息
    RESPONSE(2),
    // 心跳消息
    HEARTBEAT(3);

    private final int type;

    RPCTypeEnums(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static RPCTypeEnums findByType(int type){
        for (RPCTypeEnums rpcTypeEnums : RPCTypeEnums.values()) {
            if (rpcTypeEnums.getType() == type) {
                return rpcTypeEnums;
            }
        }
        return null;
    }
}