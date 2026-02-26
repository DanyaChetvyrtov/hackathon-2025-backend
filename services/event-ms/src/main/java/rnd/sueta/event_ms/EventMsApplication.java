package rnd.sueta.event_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import rnd.sueta.event_ms.properties.BorderProperties;

@SpringBootApplication
@EnableConfigurationProperties(BorderProperties.class)
public class EventMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventMsApplication.class, args);
    }

}
