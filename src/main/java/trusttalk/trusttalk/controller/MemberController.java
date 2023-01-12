package trusttalk.trusttalk.controller;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import trusttalk.trusttalk.domain.Member;
import trusttalk.trusttalk.domain.MemberForm;
import trusttalk.trusttalk.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	private final PooledPBEStringEncryptor jasypt;
	
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "join";
	}
	
	@PostMapping("/join")
	public String joinProcess(MemberForm memberForm) {
		String name = jasypt.encrypt(memberForm.getName());
		memberForm.setName(name);
		
		Member member = Member.createMember(memberForm, passwordEncoder);
		memberService.saveMember(member);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "login";
	}
	
	
}
