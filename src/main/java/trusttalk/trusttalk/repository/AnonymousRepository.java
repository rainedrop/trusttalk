package trusttalk.trusttalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trusttalk.trusttalk.domain.Anonymous;

@Repository
public interface AnonymousRepository extends JpaRepository<Anonymous, Long> {

}
