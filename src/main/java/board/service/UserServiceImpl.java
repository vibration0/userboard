package board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import board.mapper.UserMapper;
import board.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public UserDto selectUser(String UserId) throws Exception {
		
		return usermapper.selectUser(UserId);
	}

	@Override
	public void insertUser(UserDto users) throws Exception {
		
		usermapper.insertUser(users);
	}

	public UserDto selectNick(String NickName) throws Exception {
		
		return usermapper.selectNick(NickName);
	}
	
	

}
