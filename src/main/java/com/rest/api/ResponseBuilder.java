package com.rest.api;

import io.restassured.response.Response;


/**
 * @author xuanke(zhou.liwei @ mydreamplus.com)
 * @createTime 2019-11-02-11:55
 * @description 描述信息
 */
public class ResponseBuilder {
    private Response response;
    private ExtractResult extractResult;
    private AssertManager assertManager;

    public ResponseBuilder(Response response){
        this.response = response;
        extractResult = new ExtractResult();
        assertManager = new AssertManager();
    }

    /**
     * 校验状态码
     * @param statusCode
     * @return
     */
    public ResponseBuilder assertStatusCode(int statusCode){
        response.then().statusCode(statusCode);
        return this;
    }


    public AssertManager assertBody(String path){
        Object value = response.jsonPath().get(path);
        assertManager.setValue(String.valueOf(value));
        assertManager.setResponseBuilder(this);
        return assertManager;
    }


    public AssertManager assertHeader(String key){
        Object value = response.getHeader(key);
        assertManager.setValue(String.valueOf(value));
        assertManager.setResponseBuilder(this);
        return assertManager;
    }

    /**
     *  获取body中的值
     * @param path
     * @return
     */
    public ExtractResult getBodyValue(String path){
        Object value = response.jsonPath().get(path);
        extractResult.setResponseBuilder(this);
        extractResult.setTargetValue(value);
        return extractResult;

    }

    /**
     *  获取Header中的值
     * @param headerName
     * @return
     */
    public ExtractResult getHeaderValue(String headerName){
        Object value = response.getHeader(headerName);
        extractResult.setResponseBuilder(this);
        extractResult.setTargetValue(value);
        return extractResult;
    }

    /**
     *  运行接口，主要是为获取运行结果
     * @return
     */
    public ExtractResult run(){
        return extractResult;
    }



}
