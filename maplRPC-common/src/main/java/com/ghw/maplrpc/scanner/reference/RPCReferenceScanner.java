package com.ghw.maplrpc.scanner.reference;

import com.ghw.maplrpc.annotation.RPCReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.ghw.maplrpc.scanner.ClassScanner.getClassNameList;

/**
 * @author ghost
 * @version 1.0.0
 * 注解@RPCReference扫描器
 */
public class RPCReferenceScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCReferenceScanner.class);

    /**
     * 扫描指定包下的类，并筛选使用@RpcService注解标注的类
     */
    public static Map<String, Object> doScannerWithRPCReferenceAnnotationFilter(/*String host, int port, */ String scanPackage/*, RegistryService registryService*/) throws Exception{
        Map<String, Object> handlerMap = new HashMap<>();
        // 获取指定包下的所有类的信息
        List<String> classNameList = getClassNameList(scanPackage);
        if (classNameList == null || classNameList.isEmpty()){
            return handlerMap;
        }
        classNameList.stream().forEach((className) -> {
            try {
                // 通过类名获取class类型实例
                Class<?> clazz = Class.forName(className);
                // 获取该类中的所有变量
                Field[] declaredFields = clazz.getDeclaredFields();
                // 判断是否加上了注解
                Stream.of(declaredFields).forEach((field) -> {
                    RPCReference rpcReference = field.getAnnotation(RPCReference.class);
                    if (rpcReference != null){
                        //TODO 处理后续逻辑，将@RpcReference注解标注的接口引用代理对象，放入全局缓存中
                        LOGGER.info("当前标注了@RpcReference注解的字段名称===>>> " + field.getName());
                        LOGGER.info("@RpcReference注解上标注的属性信息如下：");
                        LOGGER.info("version===>>> " + rpcReference.version());
                        LOGGER.info("group===>>> " + rpcReference.group());
                        LOGGER.info("registryType===>>> " + rpcReference.registryType());
                        LOGGER.info("registryAddress===>>> " + rpcReference.registryAddress());
                    }
                });
            } catch (Exception e) {
                LOGGER.error("scan classes throws exception: {}", e);
            }
        });
        return handlerMap;
    }
}