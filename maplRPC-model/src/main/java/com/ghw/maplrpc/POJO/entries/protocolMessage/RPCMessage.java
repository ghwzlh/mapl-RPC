package com.ghw.maplrpc.POJO.entries.protocolMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ghost
 * @version 1.0.0
 * 消息体基础类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RPCMessage implements Serializable {

    // 是否单向调用
    private boolean oneway;

    // 是否异步调用
    private boolean async;

    public boolean getAsync() {
        return async;
    }

    public boolean getOneway() {
        return oneway;
    }
}