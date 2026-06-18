package com.app;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.RolesEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);	
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args ->{
			/*Create Permission */
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
					
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
					
					
			/*Create ROLES */
			RolesEntity roleAdmin = RolesEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
					.build();
					
			RolesEntity roleUser = RolesEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission,readPermission))
					.build();
					
			RolesEntity roleInvited = RolesEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();
					
			RolesEntity roleDevelopper = RolesEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
					.build();
					
			/*Create User */
			
			UserEntity userAlejandro = UserEntity.builder()
					.username("alejandro")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userGonzalo = UserEntity.builder()
					.username("gonzalo")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
					
			userRepository.saveAll(List.of(userAlejandro, userGonzalo));		
		};
	}
	
	

}
