package trusttalk.trusttalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trusttalk.trusttalk.domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
