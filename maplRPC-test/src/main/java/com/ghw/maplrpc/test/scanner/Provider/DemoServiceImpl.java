package com.ghw.maplrpc.test.scanner.Provider;

import com.ghw.maplrpc.annotation.RPCService;
import com.ghw.maplrpc.test.scanner.service.DemoService;

@RPCService(interfaceClass = DemoService.class,
            interfaceClassName = "com.ghw.maplrpc.test.scanner.service.DemoService",
            version = "1.0.0", group = "ghost")
public class DemoServiceImpl implements DemoService {
}
