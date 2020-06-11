package com.aoeai.spin.accelerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aoeai.spin.accelerator.refining.db.mapper")
public class SpinAcceleratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpinAcceleratorApplication.class, args);
	}

}
