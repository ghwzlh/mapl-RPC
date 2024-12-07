package com.ghw.maplrpc.test.Provider.single;

import com.ghw.maplrpc.provider.RPCSingleServer;

public class RPCSingleServerTest {
    public static void main(String[] args) {
        startRpcSingleServer();
    }

    public static void startRpcSingleServer(){
        RPCSingleServer singleServer = new RPCSingleServer("127.0.0.1:27880", "com.ghw.maplrpc.test");
        singleServer.startNettyServer();
    }
}
