package board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import board.dto.UserDto;
import board.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	//메인 화면
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title","zip");
		model.addAttribute("message","welcom to the home page!");
		return "home";
	}
	//회원가입 창
	@RequestMapping("userinsert.do")
	public String userinsert(Model model) {
		return "/users/userinsert";
	}
	
	//id중복체크, 내정보수정
	@RequestMapping(value="checkId.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> idCheck(@RequestParam String id) throws Exception {
	 Map<String, Object> resultMap = new HashMap<String,Object>();
    int check = 0;
    UserDto user = userservice.selectUser(id);
    if(user == null) {
        check = 1;
    } else {
        check = -1;
    }
    resultMap.put("check", check);
    return resultMap;
	}
	
	//닉네임 확인
	@RequestMapping(value="checkNick.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> nickCehck(@RequestParam String NickName) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int nickcheck = 0;
		UserDto user = userservice.selectNick(NickName);
		
		if(user == null) {
			nickcheck = 1;
		} else {
			nickcheck = -1;
		}
		resultMap.put("nickcheck", nickcheck);
		return resultMap;
	}
	
	//회원가입 & 비밀번호 암호화
	@RequestMapping(value="/insertPro.do", method=RequestMethod.POST)
	public String userinsertPro(HttpServletRequest request, UserDto user) throws Exception
	{
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassWord());
		user.setPassWord(encodedPassword);
				
		userservice.insertUser(user);
		return "redirect:/";
	}
	
	@RequestMapping(value="updateUserForm.do",method=RequestMethod.POST)
	public String updateUser(String id,Model model) throws Exception {
		
		UserDto user=userservice.selectUser(id);
		
		model.addAttribute("user",user);
		
		return "/users/updateUser";
	}
	
	
	
}
