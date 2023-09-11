package br.com.math012;

import br.com.math012.utils.encoder.EncodePassword;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiVideosplatformV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiVideosplatformV1Application.class, args);
		//System.out.println(EncodePassword.enconde("admin123"));
	}

}
