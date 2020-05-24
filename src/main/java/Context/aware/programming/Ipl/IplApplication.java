package Context.aware.programming.Ipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class IplApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(IplApplication.class);
		application.run(args);
	}


}
