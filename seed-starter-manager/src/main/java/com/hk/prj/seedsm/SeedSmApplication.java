package com.hk.prj.seedsm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hk.prj.seedsm.model.Variety;
import com.hk.prj.seedsm.service.VarietyService;

@SpringBootApplication
public class SeedSmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeedSmApplication.class, args);
	}

	@Autowired VarietyService varietyService;
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	List<Variety> users = getInitialUsers();
	    	varietyService.save(users);
	      };
	   }

	private List<Variety> getInitialUsers() {
		List<Variety> varieties = new ArrayList<>();
		Variety variety1 = new Variety();
		variety1.setId(1);
    	variety1.setName("Variety 1");

    	Variety variety2 = new Variety();
    	variety2.setId(2);
    	variety2.setName("Variety 2");
    	
    	varieties.add(variety1);
    	varieties.add(variety2);
    	
		return varieties;
	}
}
