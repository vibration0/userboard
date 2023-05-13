/**
 * 
 */
var pw1 = document.querySelector('#pswd1');
 
var pw2 = document.querySelector('#pswd2');

var error = document.querySelectorAll('.error_next_box');
 
function comparePw(){
	if(pw1.value ==""){
		error[0].innerHTML ="비밀번호를 입력해주세요";
		error[0].style.color = "red";
		return false;
	} else if(pw2.value =="") {
		error[1].innerHTML= "비밀번호를 입력해주세요";
		error[1].style.color = "red";
		return false;
	}
	
	if(pw1.value !== pw2.value){
		error[1].innerHTML ="비밀번호가 일치하지 않습니다";
		error[1].style.color = "red";
		pw2.value = "";
		return false;
	}
	
} 
 