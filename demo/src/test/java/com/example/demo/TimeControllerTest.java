package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TimeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/time?distance=1000&speed=10",
                String.class)).contains("Distance \" 1000 \" divide on speed \"10\" is 100");
    }
    @Test
    public void ReturnEmpty() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/time?distance=&speed=",
                String.class)).contains("Empty param sended");
    }
    @Test
    public void ReturnBadWord() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/time?distances=&speed=",
                String.class)).contains("Bad name");
    }
}
