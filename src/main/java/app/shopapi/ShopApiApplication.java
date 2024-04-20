package app.shopapi;

import app.shopapi.Configurations.AppConstants;
import app.shopapi.Models.Role;
import app.shopapi.Repositories.IRoleRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@SecurityScheme(name = "Spring Boot Web Api", scheme = "arka", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ShopApiApplication implements CommandLineRunner {


	@Autowired
	private IRoleRepository roleRepo;


	public static void main(String[] args) {
		SpringApplication.run(ShopApiApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();

			adminRole.setRoleId(AppConstants.ADMIN_ID);
			adminRole.setRoleName("ADMIN");

			Role userRole = new Role();
			userRole.setRoleId(AppConstants.USER_ID);
			userRole.setRoleName("USER");

			List<Role> roles = List.of(adminRole, userRole);

			List<Role> savedRoles = roleRepo.saveAll(roles);

			savedRoles.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
