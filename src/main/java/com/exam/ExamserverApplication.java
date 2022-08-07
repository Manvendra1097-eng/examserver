package com.exam;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExamserverApplication{

//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}


//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Starting code");
//
//		User user = new User();
//
//		user.setFirstName("Manvendra");
//		user.setLastName("Singh");
//		user.setUsername("manvendra1097");
//		user.setPassword(passwordEncoder.encode("abc"));
//		user.setEmail("abc@gmail.com");
//
//		Role role = new Role();
//		role.setRoleId(44L);
//		role.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoleSet);
//        if(user1 != null){
//			System.out.println("User Created");
//		}
//	}
}
