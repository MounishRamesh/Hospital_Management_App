package Hospital_Mangement.com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("Hospital_Mangement.com.example.demo")
public class HospitalMangementApplication {
	public static void main(String[] args) {
		SpringApplication.run(HospitalMangementApplication.class, args);
	}
}
