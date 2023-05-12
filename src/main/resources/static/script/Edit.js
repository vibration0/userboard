var id = document.querySelector('#id');

var pw1 = document.querySelector('#pswd1');

var pwMsg = document.querySelector('#alertTxt');

var pwImg1 = document.querySelector('#pswd1_Img');

var pw2 = document.querySelector('#pswd2');

var pwImg2 = document.querySelector('#pswd2_Img1');

var pwMsgArea = document.querySelector('.int_pass');

var userName = document.querySelector('#name');

var nickName = document.querySelector('#nick');

var email = document.querySelector('#email');

var mobile = document.querySelector('#mobile');

var error = document.querySelectorAll('.error_next_box');

var addr = document.querySelector('#addr2');

/*pattern */

var nickPattern = /[a-zA-Z0-9가-힣]/;

var emailPattern = /[a-z0-9]{2,}@[a-z0-9]{2,}\.[a-z0-9]{2,}/;

var isPhoneNum = /(([01]{2})([01679]{1}))([0-9]{3,4})([0-9]{4})/;

var addrPattern = /[0-9가-힣]/;

/*이벤트 핸들러*/
userName.addEventListener("focusout", checkName);
nickName.addEventListener("focusout", checkNick);
email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);
addr.addEventListener("focusout", checkAddr);

/*함수*/
function Errorbox(element, message) {
  element.innerHTML = message;
  element.style.display = "block";
  element.style.color = "#08A600";
}
function checkName() {
	
	if(userName.value==="") {
		Errorbox(error[0],"필수 정보입니다.");
		userName.value = "";
	} else if(!namePattern.test(userName.value)|| userName.value.indexOf(" ")> -1) {
		Errorbox(error[0],"한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)");
		userName.value = "";
	} else {
		error[0].style.display = "none";
	}
}

function checkNick() {

	if(nickName.value ==='') {
		Errorbox(error[1],"필수 정보입니다");
		nickName.value = "";
	} else if(!nickPattern.test(nickName.value)||nickName.value.indexOf(" ")>-1) {
		Errorbox(error[1],"한글과 영문 대 소문자를 사용하세요.(특수기호, 공백사용불가)");
		nickName.value = "";
	} else {
		error[1].style.display = "none";
	}
}

function isEmailCorrect() {
		
		
		if(email.value ===""){
			Errorbox(error[2],"이메일을 입력해주세요");
		} else if(!emailPattern.test(email.value)){
			Errorbox(error[2],"정확한 이메일을 입력해주세요");
			email.value="";
		} else {
			error[2].style.display = "none";
		}		
	}
	
 function checkPhoneNum(){
	 
	 
	 if(mobile.value === "") {
		 Errorbox(error[3],"휴대폰 번호를 입력해주세요");
	 } else if(!isPhoneNum.test(mobile.value)) {
		 Errorbox(error[3],"형식에 맞지 않는 번호입니다.");
		 mobile.value ="";
	 } else {
		 error[3].style.display ="none";
	 }
 }
	function checkAddr() {
		
		if(addr.value==="") {
			Errorbox(error[4],"상세주소를 입력해주세요");
		} else if(!addrPattern.test(addr.value)) {
			Errorbox(error[4],"정확하게 다시 입력해주세요");
			addr.value="";
		}
	}
	
		function submitCheck(){
		
		
		if(userName.value==""){
			userName.focus();
			return false;
		}
		
		if(nickName.value==""){
			nickName.focus();
			return false;	
		}
		
		if(email.value==""){
			email.focus();
			return false;
		}
		
		if(mobile.value ==""){
			mobile.focus();
			return false;
		}
		
		if(addr.value==""){
			addr.focus();
			return false;
		}
		
	}