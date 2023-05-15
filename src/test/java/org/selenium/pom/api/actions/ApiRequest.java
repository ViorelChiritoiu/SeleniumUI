package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequest extends SpecBuilder {

    public static Response post(String endPoint, Headers headers, Map<String, Object> formParams, Cookies cookies) {
        return given(getRequestSpec())
                    .headers(headers)
                    .formParams(formParams)
                    .cookies(cookies)
                .when()
                    .post(endPoint)
                .then()
                    .spec(getResponseSpec())
                    .extract()
                    .response();
    }

    public static Response get(String endPoint, Cookies cookies) {
        return given(getRequestSpec())
                    .cookies(cookies)
                .when()
                    .get(endPoint)
                .then()
                    .spec(getResponseSpec())
                    .extract()
                    .response();
    }
}
