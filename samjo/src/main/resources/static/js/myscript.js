/**
 * myscript.js
 */

function bbsCheck(){ //게시판 유효성 검사

    //1)작성자 2글자 이상 입력
    var user_name=document.getElementById("user_name").value; //작성자 가져오기
    user_name=user_name.trim(); //좌우 공백제거하기
    if(user_name.length<2){
        alert("작성자 2글자 이상 입력해 주세요");
        document.getElementById("user_name").focus(); //작성자칸에 커서 생성
        return false; //전송하지 않음
    }//if end

    //2)제목 2글자 이상 입력
    var subject=document.getElementById("subject").value; 
    subject=subject.trim(); 
    if(subject.length<2){
        alert("제목 2글자 이상 입력해 주세요");
        document.getElementById("subject").focus(); 
        return false; 
    }//if end

    //3)내용 2글자 이상 입력
    var content=document.getElementById("content").value; 
    content=content.trim(); 
    if(content.length<2){
        alert("내용 2글자 이상 입력해 주세요");
        document.getElementById("content").focus(); 
        return false; 
    }//if end

    //4)비밀번호는 4글자 이상이면서, 숫자형 기호만 입력
    var user_pw=document.getElementById("user_pw").value;
    user_pw=user_pw.trim();
    if(passwd.length<4 || isNaN(user_pw)){
        alert("비밀번호 4글자 이상 숫자로 입력해 주세여");
        document.getElementById("user_pw").focus();
        return false;
    }//if end

	return true; 

}//bbsCheck() end

function pwCheck(){
    var user_pw=document.getElementById("user_pw").value;
    user_pw=user_pw.trim();
    if(passwd.length<4 || isNaN(user_pw)){
        alert("비밀번호 4글자 이상 숫자로 입력해 주세여");
        document.getElementById("user_pw").focus();
        return false;
    }//if end

    var message="진행된 내용은 복구되지 않습니다\n계속 진행할까요?";
    if(confirm(message)){ //확인true, 취소false
        return true;  //서버로 전송
    }else{
        return false;
    }//if end


}//pwCheck() end


function loginCheck(){ //로그인 ㅇ효성검사(아이디, 비번)
    //1)아이디 5~10글자이내인지 검사
    var user_id=document.getElementById("user_id").value;
    user_id=user_id.trim();
    if(!(user_id.length>=5 && user_id.length<=10)){
        alert("아이디 5~20글자이내 입력해 주세요");
        document.getElementById("user_id").focus();
        return false;
    }//if end

    //2)비밀번호 5~10글자이내인지 검사
    var user_pw=document.getElementById("user_pw").value;
    user_pw=user_pw.trim();
    if(!(user_pw.length>=5 && user_pw.length<=10)){
        alert("비밀번호 7~20글자이내 입력해 주세요");
        document.getElementById("user_pw").focus();
        return false;
    }//if end

    return true;    
}//loginCheck() end


function idCheck() { //아이디 중복확인
	
    
	//bootsrap모달창
	//->부모창과 자식창이 한몸으로 구성되어 있음
	//->참조 https://www.w3schools.com/bootstrap/bootstrap_modal.asp
	
	//새창만들기
	//->부모창과 자식창이 별개로 구성되어 있음
	//->모바일에 기반을 둔 frontend단에서는 사용하지 말것!!
	//->참조 https://www.w3schools.com/jsref/met_win_open.asp
	//window.open("파일명","새창이름","다양한 옵션들")
	window.open("idCheckForm.do","idwin","width=400,height=350");
	
	
}//idCheck() end


function emailCheck() { //이메일 중복확인
	
	window.open("emailCheckForm.do", "emailwin", "width=400, hright=350");
	
}//idCheck() end


function memberCheck() { //회원가입 유효성 검사
    //1)아이디 5~10글자이내인지 검사
    var user_id=document.getElementById("user_id").value;
    user_id=user_id.trim();
    if(!(user_id.length>=5 && user_id.length<=10)){
        alert("아이디 5~20글자이내 입력해 주세요");
        document.getElementById("user_id").focus();
        return false;
    }//if end

    //2)비밀번호 5~10글자이내인지 검사
    var user_pw=document.getElementById("user_pw").value;
    user_pw=user_pw.trim();
    if(!(user_pw.length>=5 && user_pw.length<=10)){
        alert("비밀번호 6~20글자이내 입력해 주세요");
        document.getElementById("user_pw").focus();
        return false;
    }//if end
	
    //3)비밀번호와 비밀번호확인이 서로 일치하는지?

    var user_pw = document.getElementById('user_pw').value;
    if(document.getElementById('user_pw').value !='' && document.getElementById('user_repw').value!=''){
                if(document.getElementById('user_pw').value==document.getElementById('user_repw').value){
                    document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
    }
    
    
    //4)이름 두글자 이상 인지?

    //5)이메일 5글자 인지?

    //6)직업을 선택했는지?
	var user_job=document.getElementById("user_job").vlaue;
	if(user_job=="0"){
		alert("직업 선택해 주세요");
		return false;
	}//if end
	
	return true; //서버로 전송
	
}//memberCheck


function findIDCheck(){ //아이디/비번 찾기 유효성 검사

    //1)이름 두 글자 이상인지

    //2)이메일 주소 5글자 이상인지?

}//findIDCheck() 연습


function pdsCheck(){//포토갤러리 유효성 검사

    //1)이름

    //2)제목

    //3)비밀번호

    //4)첨부파일
    //->확장명이 이미지 파일(png, jpg, gif)인지 확인하시오
    var filename=document.getElementById("filename").value; //예sky.png
    filename=filename.trim();
    if(filename.length==0){
        alert("첨부 파일 선택하세요~");
        return false;
    }else{
        //filename변수값에서  마지막 . 의 순서값
        var dot=filename.lastIndexOf(".");
        //확장명 : 마지막 . 이후 문자열 자르기
        var ext=filename.substr(dot+1);
        //확장명을 전부 소문자 치환
        ext=ext.toLowerCase();
        if(ext=="png" || ext=="jpg" || ext=="gif") {
            return true;
        }else{
            alert("이미지 파일만 업로드 가능합니다~")
            return true;
        }//if end

    }//if end


}//pdsCheck() end



function pwCheck2(){
    var user_pw=document.getElementById("user_pw").value;
    user_pw=user_pw.trim();
    if(user_pw.length<4 || isNaN(user_pw)){
        alert("비밀번호 4글자 이상 숫자로 입력해 주세여");
        document.getElementById("user_pw").focus();
        return false;
    }//if end

    var message="첨부파일도 삭제됩니다\n계속 진행할까요?";
    if(confirm(message)){ //확인true, 취소false
        return true;  //서버로 전송
    }else{
        return false;
    }//if end


}//pwCheck2() end




