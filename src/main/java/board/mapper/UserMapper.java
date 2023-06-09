package board.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.*;
import board.dto.UserDto;

@Mapper
public interface UserMapper {
	
	public UserDto selectUser(String UserId) throws Exception;//id체크
	
	public UserDto selectNick(String NickName) throws Exception;
	
	public void insertUser(UserDto user) throws Exception;

	public void updateUser(UserDto user) throws Exception;
	
	public UserDto loginUser(Map map) throws Exception;
	
	public void editPassWord(UserDto user) throws Exception;
}
