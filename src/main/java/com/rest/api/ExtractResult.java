package com.rest.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuanke(zhou.liwei @ mydreamplus.com)
 * @createTime 2019-11-02-23:49
 * @description 提取结果的实体类
 */
public class ExtractResult {
    private Map<String,Object> extractResult = new HashMap<String, Object>();
    private Object targetValue;
    private ResponseBuilder responseBuilder;

    public ExtractResult(){

    }

    public ExtractResult(Object value){
        targetValue = value;
    }

    public void setTargetValue(Object value){
        this.targetValue = value;
    }

    public void setResponseBuilder(ResponseBuilder builder){
        this.responseBuilder = builder;
    }

    /**
     *  提取值的key
     * @param key
     * @return
     */
    public ResponseBuilder name(String key){
        extractResult.put(key, targetValue);
        return this.responseBuilder;
    }


    public Map<String, Object> getExtractResult(){
        return extractResult;
    }

    public Object get(String key){
        return extractResult.get(key);
    }
}
