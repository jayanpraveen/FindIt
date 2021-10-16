package me.realpraveen.user_service.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.user_service.Model.User;
import me.realpraveen.user_service.Service.UserService;

@RestController
@RequestMapping("/user-service")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserInfo(@PathVariable Long userId) {
		User user = userService.getUserInfo(userId);
		return ResponseEntity.ok(user != null ? user : Map.of("error", "Not found! have a 🍕"));
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.saveUser(user));
	}

}
