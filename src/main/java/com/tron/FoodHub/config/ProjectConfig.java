package com.tron.FoodHub.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {
	
	@Bean
	public Cloudinary getCloudinary() {
		
		Map config = new HashMap();
		config.put("cloud_name", "dpkkfasyd");
		config.put("api_key", "191158997136567");
		config.put("api_secret", "LvSer8mFBoqRQyAH8BfrBop_3k8");
		config.put("secure", true);
		
		return  new Cloudinary(config);
	}
}
