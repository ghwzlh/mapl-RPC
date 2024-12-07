package com.ghw.maplrpc.codec;

import com.ghw.maplrpc.serialization.api.Serialization;
import com.ghw.maplrpc.serialization.jdk.JDKSerialization;

public interface RPCcodec {

    default Serialization getJDKSerialization(){
        return new JDKSerialization();
    }
}