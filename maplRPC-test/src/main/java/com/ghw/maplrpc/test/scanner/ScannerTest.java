package com.ghw.maplrpc.test.scanner;

import com.ghw.maplrpc.scanner.ClassScanner;
import com.ghw.maplrpc.scanner.reference.RPCReferenceScanner;
import com.ghw.maplrpc.scanner.service.RPCServiceScanner;

import java.util.List;

public class ScannerTest {

    public static void main(String[] args) throws Exception {
//        testScannerClassNameList();
//        testScannerClassNameListByRpcService();
        testScannerClassNameListByRpcReference();
    }

    /**
     * 扫描com.ghw.maplrpc.test.scanner包下所有的类
     */
    public static void testScannerClassNameList() throws Exception {
        List<String> classNameList = ClassScanner.getClassNameList("com.ghw.maplrpc.test.scanner");
        classNameList.forEach(System.out::println);
    }

    /**
     * 扫描com.ghw.maplrpc.test.scanner包下所有标注了@RpcService注解的类
     */
    public static void testScannerClassNameListByRpcService() throws Exception {
        RPCServiceScanner.
                doScannerWithRPCServiceAnnotationFilterAndRegistryService("com.ghw.maplrpc.test.scanner");
    }

    /**
     * 扫描io.binghe.rpc.test.scanner包下所有标注了@RpcReference注解的类
     */
    public static void testScannerClassNameListByRpcReference() throws Exception {
        RPCReferenceScanner.
                doScannerWithRPCReferenceAnnotationFilter("com.ghw.maplrpc.test.scanner");
    }


}
