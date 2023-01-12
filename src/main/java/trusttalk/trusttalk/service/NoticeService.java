package trusttalk.trusttalk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import trusttalk.trusttalk.domain.Notice;
import trusttalk.trusttalk.repository.NoticeRepository;

@Service
@Transactional
@AllArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;

	// 글 작성
	public void noticeWrite(Notice notice) {
		noticeRepository.save(notice);
	}

	// 글 목록
	public Page<Notice> noticeList(Pageable pageable) {
			return noticeRepository.findAll(pageable);			
		
	}

	// 글 상세
	public Notice noticeDetail(Long id) {
		return noticeRepository.findById(id).get();
	}
	
	// 글 삭제
	public void noticeDelete(Long id) {
		noticeRepository.deleteById(id);
	}
	
	// 조회수
	@Transactional
	public void noticeHits(Long id) {
		Notice notice = noticeRepository.findById(id).get();
		notice.setHits(notice.getHits() + 1);
	}
	
}
