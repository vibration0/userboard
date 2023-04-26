package board.mapper;

import org.apache.ibatis.annotations.Mapper;

import board.dto.UserDto;

@Mapper
public interface UserMapper {
	
	public UserDto selectUser(String UserId) throws Exception;//id체크
	
	public UserDto selectNick(String NickName) throws Exception;
	
	public void insertUser(UserDto users) throws Exception;
	
//	public void updateUser(UserDto users) throws Exception;

	public void updateUser(UserDto users) throws Exception;
	
}
