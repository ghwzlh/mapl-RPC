package com.ghw.maplrpc.test.Provider.service.impl;

import com.ghw.maplrpc.annotation.RPCService;
import com.ghw.maplrpc.test.Provider.service.DemoService;
@RPCService(interfaceClass = DemoService.class,
        interfaceClassName = "com.ghw.maplrpc.test.Provider.service.DemoService",
        version = "1.0.0",
        group = "ghost")
public class DemoServiceImpl implements DemoService {
}
