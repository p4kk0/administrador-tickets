package mx.servis.admonticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import mx.servis.admonticket.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class AdministradorTicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministradorTicketsApplication.class, args);
	}
}
