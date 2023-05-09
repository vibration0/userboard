package board.service;

import board.dto.UserDto;
import java.util.*;

public interface UserService {
	
	//ID 중복체크
	public UserDto selectUser(String UserId) throws Exception; 
	
	//닉네임 중복체크
	public UserDto selectNick(String NickName) throws Exception;
	
	//회원가입
	public void insertUser(UserDto users) throws Exception;
	
	//회원정보수정
	public void updateUser(UserDto users) throws Exception;
	
	//로그인
	public UserDto loginUser(Map map) throws Exception;
	
	
	
	
	
	
}
