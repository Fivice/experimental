package top.fivice.springbootsample;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.fivce.logback.LogbackConfig;

import java.io.InputStream;

@SpringBootApplication
public class SpringbootSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSampleApplication.class, args);
    }

}
