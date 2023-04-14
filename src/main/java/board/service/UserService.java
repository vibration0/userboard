package board.service;

import board.dto.UserDto;

public interface UserService {
	
	public UserDto selectUser(String UserId) throws Exception; 
	
	public UserDto selectNick(String NickName) throws Exception;
	
	public void insertUser(UserDto users) throws Exception;

	
	
	
	
	
	
}
