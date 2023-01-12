package trusttalk.trusttalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trusttalk.trusttalk.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByAccount(String account);
	Member findByEmail(String email);
}
