package com.code.taletrail;

import com.code.taletrail.config.AppConstants;
import com.code.taletrail.model.Role;
import com.code.taletrail.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TaletrailApplication implements CommandLineRunner {

	@Autowired
	RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(TaletrailApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role role1 = new Role();
			role1.setId(AppConstants.USER);
			role1.setName("ROLE_USER");

			Role role2 = new Role();
			role2.setId(AppConstants.ADMIN);
			role2.setName("ROLE_ADMIN");

			List<Role> roles = List.of(role1, role2);
			roleRepo.saveAll(roles);
		} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
