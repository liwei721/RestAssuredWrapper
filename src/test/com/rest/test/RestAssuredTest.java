package com.rest.test;

import com.rest.api.ExtractResult;
import io.restassured.response.Response;
import org.junit.Test;

import static com.rest.RestAssured.RestAssuredWrapper;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author xuanke(zhou.liwei @ mydreamplus.com)
 * @createTime 2019-11-02-10:34
 * @description 描述信息
 */
public class RestAssuredTest {

    @Test
    public void testRestApi(){
        Response response = with()
                .header("", "")
                .queryParam("", "")
                .get("");
        get("/lotto").then().assertThat().body("lotto.lottoId", equalTo(5));
    }

    /**
     *  验证配置的接口
     */
    @Test
    public void testRestWrapper(){
       ExtractResult result =  RestAssuredWrapper()
                .setHeader("", "")
                .get("https://api.caiyunapp.com/v2/TAkhjf8d1nlSlspN/121.6544,25.1552/realtime.json")
                .assertBody("status").equalsValue("ok")
                .getBodyValue("status").name("status")
                .run();
        System.out.println(result.get("status"));
    }
}
