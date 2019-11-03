package com.rest.api;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author xuanke(zhou.liwei @ mydreamplus.com)
 * @createTime 2019-11-02-13:41
 * @description 对结果进行校验
 */
public class AssertManager {
    private ResponseBuilder responseBuilder;
    private String value;

    public AssertManager(){

    }

    public AssertManager(ResponseBuilder response, String value){
        this.responseBuilder = response;
        this.value = value;
    }

    public void setResponseBuilder(ResponseBuilder builder){
        this.responseBuilder = builder;
    }

    public void setValue(String value1){
        this.value = value1;
    }

    /**
     *  对值进行校验
     * @param targetValue
     * @return
     */
    public ResponseBuilder equalsValue(Object targetValue){
        assertThat(this.value, equalTo(targetValue));
        return this.responseBuilder;
    }

    /**
     *  是否包含某个值
     * @param targetValue
     * @return
     */
    public ResponseBuilder containsValue(Object targetValue){
        assertThat(this.value, containsString(String.valueOf(targetValue)));
        return this.responseBuilder;
    }

    /**
     *  不等于某个值
     * @param targetValue
     * @return
     */
    public ResponseBuilder notEqualValue(Object targetValue){
        assertThat(this.value, not(targetValue));
        return this.responseBuilder;
    }

    /**
     *  不包含某个值
     * @param targetValue
     * @return
     */
    public ResponseBuilder notContainsValue(Object targetValue){
        assertThat(this.value, not(containsString(String.valueOf(targetValue))));
        return this.responseBuilder;
    }
}
