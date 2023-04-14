/**
 * 
 */
var id = document.querySelector('#id');

var pw1 = document.querySelector('#pswd1');

var pwMsg = document.querySelector('#alertTxt');

var pwImg1 = document.querySelector('#pswd1_Img');

var pw2 = document.querySelector('#pswd2');

var pwImg2 = document.querySelector('#pswd2_Img1');

var pwMsgArea = document.querySelector('.int_pass');

var userName = document.querySelector('#name');

var nickName = document.querySelector('#nick');

var yy = document.querySelector('#yy');
var mm = document.querySelector('#mm');
var dd = document.querySelector('#dd');

var email = document.querySelector('#email');

var mobile = document.querySelector('#mobile');

var error = document.querySelectorAll('.error_next_box');

var addr = document.querySelector('#addr2');

/*pattern */
var idPattern = /[a-zA-Z0-9_-]{5,20}/;

var pwPattern =/[a-zA-Z0-9~!@#$%^&*()_+|<>:{}]{8,16}/;

var namePattern = /[a-zA-Z가-힣]/;

var nickPattern = /[a-zA-Z0-9가-힣]/;

var yearPattern = /[0-9]{4}/;

var emailPattern = /[a-z0-9]{2,}@[a-z0-9]{2,}\.[a-z0-9]{2,}/;

var isPhoneNum = /(([01]{2})([01679]{1}))([0-9]{3,4})([0-9]{4})/;

var addrPattern = /[0-9가-힣]/;
/*이벤트 핸들러 연결 */

id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
yy.addEventListener("focusout", isBirthCompleted);
mm.addEventListener("focusout", isBirthCompleted);
dd.addEventListener("focusout", isBirthCompleted);
nickName.addEventListener("focusout", checkNick);
email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);
addr.addEventListener("focusout", checkAddr);
/*콜백 함수 */

function Errorbox(element, message) {
  element.innerHTML = message;
  element.style.display = "block";
  element.style.color = "#08A600";
}
function checkId() {
	
	if(id.value ===""){
		 if (error.length > 0) {
			Errorbox(error[0],"필수정보입니다.");
		}	
	} else if(!idPattern.test(id.value)){
		if (error.length > 0) {
				Errorbox(error[0],"5~20자 영문 소문자, 숫자와 특수기호(_),(-)만 사용가능합니다.");
				id.value = "";
			}	
	} else {
		if (error.length > 0) {
			Errorbox(error[0],"O.K")
		}
	}
}
	
function checkPw() {
		
		if(pw1.value === "") {
				Errorbox(error[1],"필수 정보입니다.");
		} else if(!pwPattern.test(pw1.value)) {
			error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
			pwMsg.innerHTML = "사용불가";
			pwMsg.style.color = "red";
			pwMsgArea.style.paddingRight = "93px";
			error[1].style.display = "block";
			error[1].style.color = "#08A600";
			pw1.value = "";
			pwMsg.style.display = "block";
			pwImg1.src = "/imgs/m_icon_not_use.png"
		} else {
			error[1].style.display= "none";
			pwMsg.innerHTML = "안전";
			pwMsg.style.color ="blue";
			pwMsg.style.display = "block";
			pwMsg.style.color = "#03c75a";
			pwMsg.src = "/imgs/m_icon_safe.png";
		}
	}

function comparePw() {
		if(pw2.value === pw1.value && pw2.value !="") {
			pwImg2.src = "/imgs/m_icon_check_enable.png";
			error[2].style.display = "block";
			error[2].style.color = "#08A600";
		} else if(pw2.value !== pw1.value) {
			pwImg2.src = "/imgs/m_icon_check_disable.png";
			error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
			error[2].style.display = "block";
			error[2].style.color = "#08A600";
			pw2.value="";
		}
}

function checkName() {
	
	if(userName.value==="") {
		error[3].innerHTML = "필수 정보입니다.";
		error[3].style.display ="block";
		error[3].style.color = "#08A600";
		userName.value = "";
	} else if(!namePattern.test(userName.value)|| userName.value.indexOf(" ")> -1) {
		error[3].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
		error[3].style.display = "block";
		error[3].style.color = "#08A600";
		userName.value = "";
	} else {
		error[3].style.display = "none";
	}
}

function checkNick() {

	if(nickName.value ==='') {
		error[4].innerHTML = "필수 정보입니다";
		error[4].style.display ="block";
		error[4].style.color = "#08A600";
		nickName.value = "";
	} else if(!nickPattern.test(nickName.value)||nickName.value.indexOf(" ")>-1) {
		error[4].innerHTML = "한글과 영문 대 소문자를 사용하세요.(특수기호, 공백사용불가)";
		error[4].style.display ="block";
		error[4].style.color = "#08A600";
		nickName.value = "";
	} else {
		error[4].style.display = "none";
	}
}

function isBirthCompleted() {

	
	if(!yearPattern.test(yy.value)){
		error[5].innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
		error[5].style.display = "block";
		error[5].style.color = "#08A600";
		yy.value = "";
	} else {
		isMonthSelected();
	}
	
	function isMonthSelected() {
		if(mm.value === "월") {
			error[5].innerHTML = "태어난 월을 선택하세요";
			error[5].style.color = "#08A600";
			mm.value == "월";
		} else {
			isDateCompleted();
		}
	}	
	
	function isDateCompleted() {
		if(dd.value === "") {
			error[5].innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
			error[5].style.color = "#08A600";
		} else {
			isBirthRight();
		}
		
	}
}
	function isBirthRight() {
		var datePattern = /\d{1,2}/;
		if(!datePattern.test(dd.value)||Number(dd.value)<1 ||Number(dd.value)>31){
			error[5].innerHTML = "생년월일을 다시 확인해주세요.";
			error[5].style.color = "#08A600";
			dd.value= "";
		} else {
			checkAge();
		}
	}
	
	function checkAge() {
		if(Number(yy.value)< 1920) {
			error[5].innerHTML = "정말이세요?";
			error[5].style.display ="block";
			error[5].style.color = "#08A600";
		} else if(Number(yy.value)>2023) {
			error[5].innerHTML = "미래에서 오셨네요";
			error[5].style.display = "block";
			error[5].style.color = "#08A600";
		} else if(Number(yy.value)>2008) {
			error[5].innerHTML = "만 14세 미만의 어린이는 보호자 동의가 필요합니다.";
			error[5].style.display = "block";
			error[5].style.color = "#08A600";
		} else {
			error[5].style.display = "none";
		}
	}
	
	function isEmailCorrect() {
		
		
		if(email.value ===""){
			error[6].innerHTML = "이메일을 입력해주세요";
			error[6].style.display = "block";
			error[6].style.color = "#08A600";
		} else if(!emailPattern.test(email.value)){
			error[6].style.display = "block";
			email.value="";
		} else {
			error[6].style.display = "none";
		}		
	}
	
 function checkPhoneNum(){
	 
	 
	 if(mobile.value === "") {
		 error[7].innerHTML = "휴대폰 번호를 입력해주세요";
		 error[7].style.display = "block";
		 error[7].style.color = "#08A600";
	 } else if(!isPhoneNum.test(mobile.value)) {
		 error[7].innerHTML ="형식에 맞지 않는 번호입니다.";
		 error[7].style.display = "block";
		 error[7].style.color = "#08A600";
		 mobile.value ="";
	 } else {
		 error[7].style.display ="none";
	 }
 }
	function checkAddr() {
		
		if(addr.value==="") {
			error[8].innerHTML = "상세주소를 입력해주세요";
			error[8].style.color = "#08A600";
			error[8].style.display = "block";
		} else if(!addrPattern.test(addr.value)) {
			error[8].innerHTML = "한글로 입력해주세요";
			error[8].style.display = "block";
			error[8].style.color = "#08A600";
			addr.value="";
			
		} 
	}
	function submitCheck(){
		if(id.value ===""){
		id.focus();
		return false;
		}
		
		if(pw1.value ==="" && pw2.value===""){
			pw1.focus();
			return false
		} else if(pw1.value===""){
			pw1.focus();
			return false;
		} else if(pw2.value===""){
			pw2.focus();
			return false;
		}
		
		if(userName.value==""){
			userName.focus();
			return false;
		}
		
		if(nickName.value==""){
			nickName.focus();
			return false;	
		}
		
		if(yy.value=="" && mm.vlaue=="월" && dd.value=="" ){
			yy.focus();
			return false;
		} else if(mm.value=="월"){
			mm.focus();
			return false;
		} else if(dd.value =="" ){
			dd.focus();
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





