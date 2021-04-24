package com.shopme.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = service.listAll();
		return "users";
	}

}
