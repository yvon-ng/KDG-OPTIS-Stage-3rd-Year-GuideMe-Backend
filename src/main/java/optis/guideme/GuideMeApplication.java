package optis.guideme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GuideMeApplication {

    static void main(String[] args) {
		SpringApplication.run(GuideMeApplication.class, args);
	}

}
