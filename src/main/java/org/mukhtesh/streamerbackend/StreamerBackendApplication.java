package org.mukhtesh.streamerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(value = "org.mukhtesh.streamerbackend.entity")
@SpringBootApplication
public class StreamerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamerBackendApplication.class, args);
    }

}
