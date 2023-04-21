package com.example.Springboot_project;

import com.example.Springboot_project.Repositories.UserRepository;
import com.example.Springboot_project.Repositories.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Springboot_project.Entities.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class SpringbootProjectApplicationTests {
@Autowired
	UserRepository userRepository;
	@Test
	public void TestFindUserById(){
		boolean exists= userRepository.existsById(3L);
		assertEquals(exists,true);
	}
	@Test
	public void testcreateuser(){
		User user=new User("salah","salah","salah@gmail.com","salah", UserRole.USER,Boolean.TRUE,Boolean.FALSE );
		userRepository.save(user);
	}
	@Test
	public void TestUpdateUser(){
		User user=userRepository.findById(1L).get();
		user.setNom("salah");
		userRepository.save(user);
	}

	@Test
	public void TestFindAllUser() {
		List<User> ListUser = userRepository.findAll();
		ListUser.forEach(System.out::println);
	}
	@Test
	public void DeleteUserById(){
		userRepository.deleteById(1L);
	}
	@Test
	public void DeleteAllUser(){
		userRepository.deleteAll();
	}
}
