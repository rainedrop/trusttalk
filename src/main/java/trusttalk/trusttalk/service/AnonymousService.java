package trusttalk.trusttalk.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import trusttalk.trusttalk.domain.Anonymous;
import trusttalk.trusttalk.repository.AnonymousRepository;

@Service
@AllArgsConstructor
public class AnonymousService {

	@Autowired
	private final AnonymousRepository anonymousRepository;

	// 글 작성
	public void anonymousWrite(Anonymous anonymous) {
		anonymousRepository.save(anonymous);
	}

	// 글 목록
	public Page<Anonymous> anonymousList(Pageable pageable) {
		return anonymousRepository.findAll(pageable);
	}

	// 글 상세
	public Anonymous anonymousDetail(Long id) {
		return anonymousRepository.findById(id).get();
	}

	// 글 삭제
	public void anonymousDelete(Long id) {
		anonymousRepository.deleteById(id);
	}

	// 조회수
	public void anonymousHits(Long id) {
		Anonymous anonymous = anonymousRepository.findById(id).get();
		anonymous.setHits(anonymous.getHits() + 1);
	}
}
