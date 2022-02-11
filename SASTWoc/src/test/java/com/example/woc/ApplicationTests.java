package com.example.woc;

import com.example.woc.controller.AdminController;
import com.example.woc.controller.UserController;
import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.service.AdminService;
import com.example.woc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest

class ApplicationTests {
    @Autowired
	UserService userService;
	@Autowired
	AdminService adminService;

	@Test
	void contextLoads() {
//		Account a = new Account(1,"x","123456","1234fd");
//		Account b = new Account(2,"y","12345","1234fd");
//		userService.uploadUsername(a);
//		userService.uploadUsername(b);
//     	adminService.deleteAccount(a.getUsername());
//		System.out.println(adminService.queryUserList());
//		System.out.println(userService.login("mmmmmmmm", "123456..."));

	}


}
