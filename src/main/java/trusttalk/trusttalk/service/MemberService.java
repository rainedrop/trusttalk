package trusttalk.trusttalk.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import trusttalk.trusttalk.domain.Member;
import trusttalk.trusttalk.repository.MemberRepository;

@Service
@Transactional
@AllArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}
	
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		Member findByMember = memberRepository.findByAccount(member.getAccount());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		} else if (findByMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}


	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		Member member = memberRepository.findByAccount(account);
		
		if(member == null) {
			throw new UsernameNotFoundException(account);
		}
		
		return User.builder()
				.username(member.getAccount())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}

	
}
