package demo.com.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AuthenticationModule {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationModule.class, args);
    }
}
