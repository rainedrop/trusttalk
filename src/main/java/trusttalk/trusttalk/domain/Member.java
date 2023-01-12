package trusttalk.trusttalk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "member")
@Data
@ToString
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id; // 회원 번호

	@Column(unique = true)
	private String account; // 아이디

	private String password; // 비밀번호

	private String name; // 이름

	private String birth; // 생년월일

	private String gender; // 성별

	@Column(unique = true)
	private String email; // 이메일

	private String postcode; // 우편주소

	private String addr; // 주소

	@Enumerated(EnumType.STRING)
	private Role role;

	public static Member createMember(MemberForm memberForm, PasswordEncoder passwordEncoder) {
		
		Member member = new Member();
		member.setAccount(memberForm.getAccount());
		String password = (memberForm.getPassword());
		member.setPassword(passwordEncoder.encode(password));
		member.setName(memberForm.getName());
		member.setEmail(memberForm.getEmail());
		member.setGender(memberForm.getGender());
		member.setBirth(memberForm.getYy() + memberForm.getMm() + memberForm.getDd());
		member.setPostcode(memberForm.getPostcode());
		member.setAddr(memberForm.getRoadAddr() + memberForm.getDetailAddr());

		member.setRole(Role.USER);

		return member;
	}
}
