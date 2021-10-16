package me.realpraveen.user_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.realpraveen.user_service.Model.User;
import me.realpraveen.user_service.Repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User saveUser(User user) {

		if (doesUserExist(user.getEmail())) {
			return null;
		}

		return userRepo.save(user);
	}

	public User getUserInfo(Long userId) {
		return userRepo.findById(userId).orElse(null);
	}

	private boolean doesUserExist(String email) {
		return userRepo.existsByEmail(email);
	}

}
