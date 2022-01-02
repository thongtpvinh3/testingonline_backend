package backend.testingonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"backend.testingonline"})
@ComponentScan(basePackages = {"backend.testingonline"})
public class TestingOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingOnlineApplication.class, args);
	}
}