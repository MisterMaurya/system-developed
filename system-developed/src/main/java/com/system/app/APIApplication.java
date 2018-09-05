/**
* @author  Pawan Maurya
* @version 1.0
*/
package com.system.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.system.dao.impl.DeviceDAOImpl;
import com.system.entity.Device;

@SpringBootApplication
@ComponentScan(basePackages = { "com.system.controller" })
public class APIApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(APIApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(APIApplication.class, args);
		DeviceDAOImpl impl = new DeviceDAOImpl();
		Device de = impl.getDevice(3);
		System.out.println(de.getDescription());

	}

}
