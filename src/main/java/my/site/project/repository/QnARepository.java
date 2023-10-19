package my.site.project.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.site.project.entity.QnA;




public interface QnARepository extends JpaRepository<QnA, Long> {

	@Query("SELECT q.text, q.questioner FROM QnA q WHERE q.qno = :qno")
	List<Object[]> getQnAInfo(@Param("qno") Long qno);
	
	@Query("SELECT q FROM QnA q") // JPQL 쿼리 수정: 엔티티 QnA를 반환하도록 변경
	Page<QnA> getQnAPaged(Pageable pageable);

}
