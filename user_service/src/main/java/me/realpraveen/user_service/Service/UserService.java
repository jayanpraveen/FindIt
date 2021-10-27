package me.realpraveen.user_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.realpraveen.user_service.DTO.UserInfo;
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

		if (doesUserExist(user.getUserId())) {
			return null;
		}

		return userRepo.save(user);
	}

	public UserInfo getUserInfo(Long userId) {
		User user = userRepo.findById(userId).orElse(null);

		if (user == null)
			return null;


		return new UserInfo(user.getUserId(), 
							user.getName(), 
							user.getEmail(), 
							user.getINSTITUTE(),
							user.getInstituteName());

	}

	private boolean doesUserExist(Long id) {
		return userRepo.existsByUserId(id);
	}

}
