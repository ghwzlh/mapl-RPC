package com.ghw.maplrpc.provider;

import com.ghw.maplrpc.scanner.service.RPCServiceScanner;
import com.ghw.maplrpc.service.service.BaseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ghost
 * @version 1.0.0
 * Java原生启动 maplRPC 框架
 */
public class RPCSingleServer extends BaseServer {

    private static final Logger logger = LoggerFactory.getLogger(RPCSingleServer.class);

    public RPCSingleServer(String serverAddress, String scanPackage) {
        super(serverAddress);
        try {
            this.handlerMap = RPCServiceScanner.doScannerWithRPCServiceAnnotationFilterAndRegistryService(scanPackage);
        } catch (Exception e) {
            logger.error("RPC Server init error", e);
        }
    }
}