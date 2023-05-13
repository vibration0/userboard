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
		model.addAttribute("title","main");
		model.addAttribute("message","welcom to the home page!");
		return "home";
	}
	//회원가입 창
	@RequestMapping("userinsert.do")	
	public String userinsert(Model model) {
		return "users/userinsert";
	}
	
	//id중복체크
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
	
	//로그인 창
	@RequestMapping("loginForm.do")
	public String loginForm() {
		
		return "/users/loginUser";
	}
	//로그인 기능
	@RequestMapping(value="loginPro.do", method=RequestMethod.POST)
	public String loginDetail(Model model,HttpServletRequest request) throws Exception {
		
		
		String UserId = request.getParameter("UserId");
		String PassWord =request.getParameter("PassWord");
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("UserId", UserId);
		map.put("PassWord", PassWord);
		
		UserDto user=userservice.loginUser(map);
		
		if(user == null) {
			model.addAttribute("msg","존재하지 않는 ID입니다.");
			return "users/loginUser";
		} else {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassWord = user.getPassWord();
		boolean matchPw=passwordEncoder.matches(PassWord, encodedPassWord);
		
		if(!matchPw) {
			model.addAttribute("msg","비밀번호가 일치하지 않습니다.");
			return "users/loginUser";
			}
		}
		
		
		HttpSession loginSession=request.getSession();
		
		loginSession.setAttribute("UserId", user.getUserId());
		/* model.addAttribute("id", user.getUserId()); */
		  
		return "redirect:/";
	}
	
	//로그아웃 기능
	@RequestMapping("logOut.do")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//회원가입 수정폼
	@RequestMapping(value="updateUserForm.do")
	public String updateUser(@RequestParam("UserId")String UserId, Model model) throws Exception {
		
		UserDto user=userservice.selectUser(UserId);
		
		model.addAttribute("user",user);
		
		return "users/updateUser";
	}
	
	@RequestMapping(value="updateUserPro.do", method=RequestMethod.POST)
	public String updatePro(UserDto user) throws Exception {
		
		userservice.updateUser(user);
		return "redirect:/";
	}
	//비밀번호 확인 창
	@RequestMapping("checkPassWordform.do")
	public String PassWordform() {
		
		return "users/checkPassWord";
	}
	
	@RequestMapping(value="checkPassWordPro.do", method=RequestMethod.POST)
	public String editPassWordPro(@RequestParam("UserId")String UserId,Model model,HttpServletRequest request) throws Exception {
		UserDto user = userservice.selectUser(UserId);
		String PassWord = request.getParameter("PassWord");
		
		//비밀번호 암호화 시켜 비교
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassWord = user.getPassWord();
		boolean matchPw=passwordEncoder.matches(PassWord, encodedPassWord);
		
		if(!matchPw) {
			model.addAttribute("msg","비밀번호가 일치하지 않습니다.");
			return "users/checkPassWord";
			}
		return "users/editPassWord";
	}
}
