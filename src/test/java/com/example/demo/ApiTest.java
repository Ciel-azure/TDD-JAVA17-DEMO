package com.example.demo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    @Autowired
    private DatabaseClean databaseClean;
    //이렇게하지 않으면 데이터를 삭제해도 시퀀스가 계속 증가하기 때문에 테스트에 문제가 있을 수 있다.

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        if(RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseClean.afterPropertiesSet();
        }
        databaseClean.execute();
    }
}
