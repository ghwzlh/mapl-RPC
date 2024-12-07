package com.ghw.maplrpc.scanner.service;

import com.ghw.maplrpc.annotation.RPCService;
import com.ghw.maplrpc.scanner.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ghost
 * @version 1.0.0
 * 注解@RPCService扫描器
 */
public class RPCServiceScanner extends ClassScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(RPCServiceScanner.class);

    /**
     * 扫描指定包下的类，并筛选使用@RpcService注解标注的类
     */
    public static Map<String, Object> doScannerWithRPCServiceAnnotationFilterAndRegistryService(/*String host, int port, */ String scanPackage/*, RegistryService registryService*/) throws Exception{
        Map<String, Object> handlerMap = new HashMap<>();
        // 获取指定包下的所有类的信息
        List<String> classNameList = getClassNameList(scanPackage);
        if (classNameList == null || classNameList.isEmpty()){
            return handlerMap;
        }
        classNameList.stream().forEach((className) -> {
            try {
                Class<?> clazz = Class.forName(className);
                RPCService rpcService = clazz.getAnnotation(RPCService.class);
                if (rpcService != null){
                    //优先使用interfaceClass, interfaceClass的name为空，再使用interfaceClassName
                    //TODO 后续逻辑向注册中心注册服务元数据，同时向handlerMap中记录标注了RpcService注解的类实例
                    String serviceName = getServiceName(rpcService);
                    String key = serviceName.concat(rpcService.version()).concat(rpcService.group());
                    handlerMap.put(key, clazz.newInstance());
//                    LOGGER.info("当前标注了@RpcService注解的类实例名称===>>> " + clazz.getName());
//                    LOGGER.info("@RpcService注解上标注的属性信息如下：");
//                    LOGGER.info("interfaceClass===>>> " + rpcService.interfaceClass().getName());
//                    LOGGER.info("interfaceClassName===>>> " + rpcService.interfaceClassName());
//                    LOGGER.info("version===>>> " + rpcService.version());
//                    LOGGER.info("group===>>> " + rpcService.group());
                }
            } catch (Exception e) {
                LOGGER.error("scan classes throws exception: {}", e);
            }
        });
        return handlerMap;
    }

    /**
     * 获取serviceName
     */
    private static String getServiceName(RPCService rpcService){
        //优先使用interfaceClass
        Class clazz = rpcService.interfaceClass();
        if (clazz == void.class){
            return rpcService.interfaceClassName();
        }
        String serviceName = clazz.getName();
        if (serviceName == null || serviceName.trim().isEmpty()){
            serviceName = rpcService.interfaceClassName();
        }
        return serviceName;
    }
}
