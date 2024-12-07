package com.ghw.maplrpc.POJO.entries.protocolMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ghost
 * @version 1.0.0
 * 响应消息体实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RPCResponse extends RPCMessage {

    private static final long serialVersionUID = -7541349403880621904L;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 返回结果
     */
    private Object result;
}