package me.realpraveen.user_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.realpraveen.user_service.Model.Institution;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

	private Long userId;
	private String name;
	private String email;
	private Institution INSTITUTE;
	private String instituteName;

}
