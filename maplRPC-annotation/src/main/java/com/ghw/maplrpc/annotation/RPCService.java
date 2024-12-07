package com.ghw.maplrpc.annotation;

import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ghost
 * @version 1.0.0
 * RPCService 服务提供者注解实现
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RPCService {

    // 接口的类型
    Class<?> interfaceClass() default void.class;


    // 服务名称
    String interfaceClassName() default "";

    // 服务版本号
    String version() default "1.0.0";

    // 服务分组
    String group() default "";
}