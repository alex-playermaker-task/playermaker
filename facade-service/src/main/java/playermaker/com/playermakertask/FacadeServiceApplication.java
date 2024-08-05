package playermaker.com.playermakertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:/application.properties")
public class FacadeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacadeServiceApplication.class, args);
	}

}
