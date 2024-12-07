package com.ghw.maplrpc.POJO.entries.protocolMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author ghost
 * @version 1.0.0
 * 消息实体类
 */
public class RPCProtocol<T> implements Serializable {

    private static final long serialVersionUID = 7231860119797749311L;

    private RPCHeader rpcHeader;

    private T body;
}