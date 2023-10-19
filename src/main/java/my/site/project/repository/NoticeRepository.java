package my.site.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.site.project.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	@Query("SELECT n.title, n.admin, n.content FROM Notice n WHERE n.nno = :nno")
	List<Object[]> getNoticeInfo(@Param("nno") Long nno);
	
	@Query("SELECT n FROM Notice n")
	Page<Notice> getNoticePaged(Pageable pageable);
}
