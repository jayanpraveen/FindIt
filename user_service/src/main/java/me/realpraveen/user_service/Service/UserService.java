package me.realpraveen.user_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import me.realpraveen.user_service.Model.User;
import me.realpraveen.user_service.Repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User saveUser(@RequestBody User user) {
		return userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
