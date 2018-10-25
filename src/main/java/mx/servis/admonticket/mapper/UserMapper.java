package mx.servis.admonticket.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.servis.admonticket.dto.UserDTO;
import mx.servis.admonticket.persistence.model.User;

@Component
public class UserMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User userDTO2User(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}
	
	public UserDTO user2UserDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
}
