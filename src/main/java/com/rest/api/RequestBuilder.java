package com.rest.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * @author xuanke(zhou.liwei @ mydreamplus.com)
 * @createTime 2019-11-02-11:55
 * @description 描述信息
 */
public class RequestBuilder {
    private RequestSpecification specification;

    public RequestBuilder(RequestSpecification specification){
        this.specification = specification;
    }


    /**
     *  set request header
     * @param key
     * @param value
     * @param additionalHeaderValues
     * @return
     */
    public  RequestBuilder setHeader(String key, Object value, Object... additionalHeaderValues){
        specification.header(key, value, additionalHeaderValues);
        return this;
    }

    /**
     *  set request header
     * @param firstHeaderName
     * @param firstHeaderValue
     * @param headerNameValuePairs
     * @return
     */
    public RequestBuilder setHeaders(String firstHeaderName, Object firstHeaderValue, Object... headerNameValuePairs){
        specification.headers(firstHeaderName, firstHeaderValue, headerNameValuePairs);
        return this;
    }

    /**
     *  set request header
     * @param headers
     * @return
     */
    public  RequestBuilder setHeaders(Map<String, ?> headers){
        specification.headers(headers);
        return this;
    }

    /**
     *  set request cookies
     * @param firstCookieName
     * @param firstCookieValue
     * @param cookieNameValuePairs
     * @return
     */
    public RequestBuilder setCookies(String firstCookieName, Object firstCookieValue, Object... cookieNameValuePairs){
        specification.cookies(firstCookieName, firstCookieValue, cookieNameValuePairs);
        return this;
    }

    /**
     *  set request cookie
     * @param cookieName
     * @param value
     * @param additionalValues
     * @return
     */
    public RequestBuilder setCookie(String cookieName, Object value, Object... additionalValues){
        specification.cookie(cookieName, value, additionalValues);
        return this;
    }

    /**
     *
     * @param cookies
     * @return
     */
    public RequestBuilder setCookies(Map<String, ?> cookies){
        specification.cookies(cookies);
        return this;
    }

    public RequestBuilder setQueryParam(String key, String value){
        specification.queryParam(key, value);
        return this;
    }

    public RequestBuilder setPostParam(String key, String value){
        specification.param(key, value);
        return this;
    }

    public RequestBuilder setPostBody(String postBody){
        specification.body(postBody);
        return this;
    }
    /**
     *  get request
     * @param url
     * @return
     */
    public ResponseBuilder get(String url){
        Response response = specification.get(url);
        return new ResponseBuilder(response);
    }

    /**
     *  post request
     * @param url
     * @return
     */
    public ResponseBuilder post(String url){
        Response response = specification.post(url);
        return new ResponseBuilder(response);
    }


}
