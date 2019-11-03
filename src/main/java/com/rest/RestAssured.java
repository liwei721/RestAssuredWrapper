package com.rest;

import com.rest.api.RequestBuilder;

import static io.restassured.RestAssured.given;

/**
 * @author : xuanke
 * @version V1.0
 * @Email: zhouliwei1989@126.com
 * @date Date : 2019年11月01日 10:41 下午
 * @Description: rest-assured-wrapper
 */

public class RestAssured {
    /**
     *  entrance
     * @return
     */
    public static RequestBuilder RestAssuredWrapper(){
        return new RequestBuilder(given());
    }


}
