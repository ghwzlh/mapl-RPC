package com.ghw.maplrpc.POJO.entries.protocolMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ghost
 * @version 1.0.0
 * 请求消息体实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RPCRequest extends RPCMessage {
    private static final long serialVersionUID = -1705782722388713335L;
    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型数组
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数数组
     */
    private Object[] parameters;
    /**
     * 版本号
     */
    private String version;
    /**
     * 服务分组
     */
    private String group;
}