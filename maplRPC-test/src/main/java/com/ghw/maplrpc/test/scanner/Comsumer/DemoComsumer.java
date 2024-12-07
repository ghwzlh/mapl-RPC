package com.ghw.maplrpc.test.scanner.Comsumer;

import com.ghw.maplrpc.annotation.RPCReference;
import com.ghw.maplrpc.test.scanner.reference.DemoReference;
import com.ghw.maplrpc.test.scanner.service.DemoService;

public class DemoComsumer implements DemoReference {

    @RPCReference(registryType = "zookeeper", group = "ghost", version = "1.0.0", registryAddress = "127.0.0.1:2181")
    private DemoService demoService;
}
