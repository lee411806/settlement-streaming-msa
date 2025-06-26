package com.jaeyong.streamingmsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamingMsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamingMsaApplication.class, args);
    }

}
