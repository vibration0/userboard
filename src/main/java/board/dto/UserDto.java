package board.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class UserDto {
	private String UserId;
	private String PassWord;
	private String Name;
	private String NickName;
	private String PhoneNum;
	private String EmailAddr;
	private String Addr;
	private String FulAddr;
	private Timestamp CreatedAt;
	private Timestamp UpdatedAt;
	private Timestamp DeletedAt;
}
