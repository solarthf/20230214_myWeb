package kh.member.model.vo;

/**
 * 
 * @author user1
 * TEST_MEMBER 테이블을 이용하여
 * 0. 첫화면("/") index.jsp include header.jsp - 로그인(login Get)/로그아웃(logout Get) 버튼 토글, nav에 내정보보기 (myinfo get)버튼 생성
 * 로그인화면 - 로그인(login post) / 회원가입(enroll get) 버튼
 * 1. 회원가입("/enroll") - get enroll.jsp, post 회원가입DB 다녀와서 ("/")으로 이동
 * 회원가입화면 - 회원가입(enroll post) 버튼 
 * 2. 로그인("/login") - Get login.jsp, post 로그인확인DB 다녀와서 session("lgnss")등록(이름, email, id) 후 ("/")이동
 * 3. 로그아웃("/logout") - Get 세션 만료 후 ("/")으로 이동
 * 4. 내정보 보기("/myinfo") - get myinfo.jsp
 * 내정보보기 화면에서 하단에 수정(infoupdate get), 탈퇴 버튼
 * 5. 내정보 수정하기("/infoupdate")
 * 내정보보기 수정 화면에서 수정(infoupdate post) 
 */
public class MemberVo {

//	이름     널?       유형           
//	------ -------- ------------ 
//	ID     NOT NULL VARCHAR2(15) 
//	PASSWD NOT NULL VARCHAR2(15) 
//	NAME   NOT NULL VARCHAR2(20) 
//	EMAIL           VARCHAR2(30) 
			
    private String id;
    private String passwd;
    private String name;
    private String email;
    
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
    
}
