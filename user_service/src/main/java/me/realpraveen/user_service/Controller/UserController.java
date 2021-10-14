package me.realpraveen.user_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.user_service.Model.User;
import me.realpraveen.user_service.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<String> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok("ALl users List goes here");
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.saveUser(user));
	}

}
