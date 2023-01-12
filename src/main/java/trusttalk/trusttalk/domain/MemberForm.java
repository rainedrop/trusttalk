package trusttalk.trusttalk.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
	
	private String account;	// 아이디
	
	private String password; // 비밀번호
	
	private String name; // 이름
	
	private String birth; // 생년월일
	
	private String gender; // 성별
	
	private String email; // 이메일
	
	private String yy; // 생년월일 - 년
	private String mm; // 생년월일 - 월
	private String dd; // 생년월일 - 일
	
	private String postcode;	// 우편 번호
	
	private String roadAddr;	// 도로명 주소
	private String detailAddr;	// 상세 주소
}
